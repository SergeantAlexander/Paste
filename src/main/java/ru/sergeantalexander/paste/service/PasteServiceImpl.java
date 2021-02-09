package ru.sergeantalexander.paste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergeantalexander.paste.entity.Paste;
import ru.sergeantalexander.paste.repository.PasteRepository;

@Service
public class PasteServiceImpl implements PasteService {

    @Autowired
    PasteRepository repository;

    @Override
    public Paste getPasteByHash(Long hash) {
        return repository.getOne(hash);
    }

    @Override
    public void storePaste(Paste paste) {
        repository.save(paste);
    }
}
