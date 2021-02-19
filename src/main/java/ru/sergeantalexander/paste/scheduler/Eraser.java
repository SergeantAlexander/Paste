package ru.sergeantalexander.paste.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sergeantalexander.paste.repository.PasteRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
public class Eraser {

    @Autowired
    PasteRepository repository;

    @Transactional
    @Scheduled(fixedRate = 60 * 60 * 1000)
    private void erase() {
        repository.deleteAll(repository.findAllByPasteExpiredTimeBefore(LocalDateTime.now()));
    }

}
