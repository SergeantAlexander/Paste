package ru.sergeantalexander.paste.entity;

import ru.sergeantalexander.paste.enumiration.Expiration;
import ru.sergeantalexander.paste.enumiration.Exposure;
import ru.sergeantalexander.paste.enumiration.Syntax;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PASTE")
public class Paste {

    @Id
    @GeneratedValue
    private Long key;
    private String hash;
    private String title;
    private Syntax syntax;
    private Expiration expiration;
    private Exposure exposure;
    private boolean burn;
    private String password;
    private String paste;
    private LocalDateTime pastePlacementTime;
    private LocalDateTime pasteExpiredTime;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Syntax getSyntax() {
        return syntax;
    }

    public void setSyntax(Syntax syntax) {
        this.syntax = syntax;
    }

    public Expiration getExpiration() {
        return expiration;
    }

    public void setExpiration(Expiration expiration) {
        this.expiration = expiration;
    }

    public Exposure getExposure() {
        return exposure;
    }

    public void setExposure(Exposure exposure) {
        this.exposure = exposure;
    }

    public boolean isBurn() {
        return burn;
    }

    public void setBurn(boolean burn) {
        this.burn = burn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaste() {
        return paste;
    }

    public void setPaste(String paste) {
        this.paste = paste;
    }

    public LocalDateTime getPastePlacementTime() {
        return pastePlacementTime;
    }

    public void setPastePlacementTime(LocalDateTime pastePlacementTime) {
        this.pastePlacementTime = pastePlacementTime;
    }

    public LocalDateTime getPasteExpiredTime() {
        return pasteExpiredTime;
    }

    public void setPasteExpiredTime(LocalDateTime pasteExpiredTime) {
        this.pasteExpiredTime = pasteExpiredTime;
    }
}
