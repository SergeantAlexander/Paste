package ru.sergeantalexander.paste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergeantalexander.paste.JsonRequestMapping;
import ru.sergeantalexander.paste.entity.Paste;
import ru.sergeantalexander.paste.service.PasteService;

import java.util.List;

@RestController
@RequestMapping("/my-awesome-pastebin.tld")
public class PasteController {

    @Autowired
    PasteService service;

    @JsonRequestMapping(value = "/{hash}", method = RequestMethod.GET)
    public Paste getPasteByHash(@PathVariable(name = "hash") String hash) {
        return service.getPasteByHash(hash);

    }

    @JsonRequestMapping(value = "/store", method = RequestMethod.POST)
    public Paste storePaste(@RequestBody Paste paste) {
        return service.storePaste(paste);
    }

    //тестовый
    @JsonRequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Paste> getAllOfPasts() {
        return service.getAllOfPasts();
    }

    @JsonRequestMapping(value = "/last", method = RequestMethod.GET)
    public List<Paste> getLastTen() {
        return service.getLastTen();
    }

}
