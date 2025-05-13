package com.exemplo.encurtadorurl.repository;

import com.exemplo.encurtadorurl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, String> {
    Optional<Url> findByShortCode(String code);
}
