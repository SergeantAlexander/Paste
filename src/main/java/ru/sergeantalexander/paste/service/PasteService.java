package ru.sergeantalexander.paste.service;

import ru.sergeantalexander.paste.client.GrpcServ;
import ru.sergeantalexander.paste.entity.Paste;

import java.io.IOException;
import java.util.List;

public interface PasteService {

    List<Paste> getLastTen() throws IOException;

    List<Paste> getAllOfPasts();

    Paste getPasteByHash(String hash);

    Paste storePaste(Paste paste);

    String getPosition ();

}
