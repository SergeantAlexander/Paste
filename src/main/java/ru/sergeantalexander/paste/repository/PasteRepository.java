package ru.sergeantalexander.paste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergeantalexander.paste.entity.Paste;
import ru.sergeantalexander.paste.enumiration.Exposure;

import java.time.LocalDateTime;
import java.util.List;

public interface PasteRepository extends JpaRepository<Paste, Long> {

    Paste findFirstByOrderByKeyDesc();

    List<Paste> findFirst10ByExposureEqualsAndPasteExpiredTimeAfterOrderByPastePlacementTimeDesc
            (Exposure exposure, LocalDateTime now);

    Paste getOneByHash(String hash);

    List<Paste> findAllByPasteExpiredTimeBefore(LocalDateTime now);

}
