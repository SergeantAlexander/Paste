package ru.sergeantalexander.paste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergeantalexander.paste.entity.Paste;

public interface PasteRepository extends JpaRepository<Paste, Long> {
}
