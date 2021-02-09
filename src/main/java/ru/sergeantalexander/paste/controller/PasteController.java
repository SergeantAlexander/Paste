package ru.sergeantalexander.paste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sergeantalexander.paste.JsonRequestMapping;
import ru.sergeantalexander.paste.entity.Paste;
import ru.sergeantalexander.paste.service.PasteService;

@RestController
@RequestMapping("/my-awesome-pastebin.tld")
public class PasteController {

    @Autowired
    PasteService service;

    @JsonRequestMapping(value = "/{hash}", method = RequestMethod.GET)
    public Paste getPasteByHash(@PathVariable(name = "hash") Long hash) {
        return service.getPasteByHash(hash);

    }

    @JsonRequestMapping(value = "/store", method = RequestMethod.POST)
    public ResponseEntity<?> storePaste(@RequestBody Paste paste) {
        service.storePaste(paste);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
