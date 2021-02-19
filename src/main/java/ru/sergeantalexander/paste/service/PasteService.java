package ru.sergeantalexander.paste.service;

import ru.sergeantalexander.paste.entity.Paste;

import java.util.List;

public interface PasteService {

    List<Paste> getLastTen();

    List<Paste> getAllOfPasts();

    Paste getPasteByHash(String hash);

    Paste storePaste(Paste paste);

}
