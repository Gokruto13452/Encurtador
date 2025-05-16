package com.exemplo.encurtadorurl.repository;

import java.util.List;
import java.util.Optional;
import com.exemplo.encurtadorurl.model.UrlEncurtada;
import com.exemplo.encurtadorurl.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEncurtada, Long> {
    Optional<UrlEncurtada> findByShortUrlEndingWith(String code);
    List<UrlEncurtada> findByUsuario(Usuario usuario);
}
