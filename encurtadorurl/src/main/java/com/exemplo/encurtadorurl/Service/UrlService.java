package com.exemplo.encurtadorurl.service;

import com.exemplo.encurtadorurl.model.Url;
import com.exemplo.encurtadorurl.repository.UrlRepository;
import com.exemplo.encurtadorurl.exception.CodigoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository repo;

    public String shortenUrl(String originalUrl) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(code);
        repo.save(url);
        return code;
    }

    public String getOriginalUrl(String codigo) {
        return repo.findByShortCode(codigo)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new CodigoNaoEncontradoException("Código " + codigo + " não encontrado"));
    }
}
