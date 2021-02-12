package ru.sergeantalexander.paste.service;

import ru.sergeantalexander.paste.entity.Paste;

public interface PasteService {

    Paste getPasteByHash (Long hash);

    void storePaste (Paste paste);

}
