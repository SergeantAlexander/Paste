package ru.sergeantalexander.paste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import ru.sergeantalexander.paste.entity.Paste;
import ru.sergeantalexander.paste.repository.PasteRepository;

import java.util.HashMap;
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
        String stuff = "1234567890AaBbCcDdEeFfGgHhIiGgKkLlMmOoPpQqRrSsTtUuVvWwXxYyZz";
        char[] chars = stuff.toCharArray();
        for (Character character : chars) {
            count++;
            alphabet.put(count, character);
        }
    }

    @Override
    public Paste getPasteByHash(Long hash) {
        return repository.getOne(hash);
    }

    @Override
    public void storePaste(Paste paste) {
        //хэши имеют значения от 11111111 до zzzzzzzz и вертятся по кругу, однако некоторые записи будут
        //жить вечно, поэтому некоторые хэши будут перманентно заняты. Поэтому делаем (пока примитивно) проверку
        //на занятость хэша.
        String newHash;
        do {
            newHash = makeHash(lastHash);
            lastHash = newHash;
        } while (repository.getOneByHash(newHash)!=null);
        paste.setHash(newHash);
        repository.save(paste);
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
