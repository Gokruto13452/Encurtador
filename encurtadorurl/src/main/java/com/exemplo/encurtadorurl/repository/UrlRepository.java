package com.exemplo.encurtadorurl.repository;

import java.util.List;
import com.exemplo.encurtadorurl.model.Url;
import com.exemplo.encurtadorurl.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, String> {
    Optional<Url> findByShortCode(String code);
    List<Url> findByUsuario(Usuario usuario);

}
