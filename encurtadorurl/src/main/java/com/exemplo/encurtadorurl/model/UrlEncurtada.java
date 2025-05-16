package com.exemplo.encurtadorurl.model;

import jakarta.persistence.*;

@Entity
public class UrlEncurtada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;
    private String shortUrl;

    @ManyToOne
    private Usuario usuario;

    public UrlEncurtada() {}

    public UrlEncurtada(String originalUrl, String shortUrl, Usuario usuario) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
