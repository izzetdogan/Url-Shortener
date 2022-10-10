package com.shortener.repository;

import com.shortener.model.UrlShort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlShortRepository extends JpaRepository<UrlShort, Long> {
    Optional<UrlShort> findAllByShortUrl(String shortUrl);
}
