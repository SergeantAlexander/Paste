package ru.sergeantalexander.paste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import ru.sergeantalexander.paste.entity.Paste;
import ru.sergeantalexander.paste.enumiration.Expiration;
import ru.sergeantalexander.paste.enumiration.Exposure;
import ru.sergeantalexander.paste.repository.PasteRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PasteServiceImpl implements PasteService {

    @Autowired
    PasteRepository repository;

    private final Map<Integer, Character> alphabet = new HashMap<>();
    Integer count = -1;
    private String lastHash = null;
    private Pair<Integer, Character> pair;
    private Integer numberForChange;

    {
        String stuff = "1234567890AaBbCcDdEeFfGgHhIiJjKkLlMmOoPpQqRrSsTtUuVvWwXxYyZz";
        char[] chars = stuff.toCharArray();
        for (Character character : chars) {
            count++;
            alphabet.put(count, character);
        }
    }

    @Override
    public List<Paste> getLastTen() {
        return repository.findFirst10ByExposureEqualsAndPasteExpiredTimeAfterOrderByPastePlacementTimeDesc
                (Exposure.PUBLIC, LocalDateTime.now());
    }

    @Override
    public List<Paste> getAllOfPasts() {
        return repository.findAll();
    }

    @Override
    public Paste getPasteByHash(String hash) {
        Paste paste = repository.getOneByHash(hash);
        if (paste != null) {
            if (paste.isBurn()) {
                repository.delete(paste);
            }
            return paste.getPasteExpiredTime().isAfter(LocalDateTime.now()) ? paste : null;
        }
        return null;
    }

    @Override
    public Paste storePaste(Paste paste) {
        //хэши имеют значения от 11111111 до zzzzzzzz и вертятся по кругу, однако некоторые записи будут
        //жить вечно, поэтому некоторые хэши будут перманентно заняты. Поэтому делаем (пока примитивно) проверку
        //на занятость хэша.
        String newHash;
        do {
            newHash = makeHash(lastHash);
            lastHash = newHash;
        } while (repository.getOneByHash(newHash) != null);
        paste.setHash(newHash);
        paste.setPastePlacementTime(LocalDateTime.now());
        paste.setPasteExpiredTime(Expiration.getDateForExpiring(paste.getExpiration()));
        if (paste.isBurn() || paste.getExposure() == Exposure.PUBLIC) {
            paste.setExposure(Exposure.UNLISTED);
        }
        repository.save(paste);
        return repository.getOneByHash(paste.getHash());
    }

    private String makeHash(String lastHash) {
        //при перезагрузке приложения запрашивает последний хэш
        if (lastHash == null) {
            if (repository.findFirstByOrderByKeyDesc() == null) {
                return "11111111";
            }
            lastHash = repository.findFirstByOrderByKeyDesc().getHash();
        }

        if (lastHash.isEmpty() || lastHash.equals("zzzzzzzz")) {
            return "11111111";
        }
        char[] chars = lastHash.toCharArray();

        //В этом цикле создаём пару <номер в хешкоде, имвол для замены> для замены
        for (int i = 7; i >= 0; i--) {
            if (chars[i] == 'z') {
                continue;
            }
            pair = Pair.of(i, chars[i]);
            break;
        }

        //В этом цикле ищем номер нужного нам символа в алфавите
        for (Map.Entry<Integer, Character> entry : alphabet.entrySet()) {
            if (entry.getValue() == pair.getSecond()) {
                numberForChange = entry.getKey() + 1;
                break;
            }
        }

        //Тут собираем новый хэш
        StringBuilder stringBuilder = new StringBuilder();
        chars[pair.getFirst()] = alphabet.get(numberForChange);
        for (Character character : chars) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

}
