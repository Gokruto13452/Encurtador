package com.exemplo.encurtadorurl.service;

import com.exemplo.encurtadorurl.exception.CodigoNaoEncontradoException;
import com.exemplo.encurtadorurl.model.UrlEncurtada;
import com.exemplo.encurtadorurl.model.Usuario;
import com.exemplo.encurtadorurl.repository.UrlRepository;
import com.exemplo.encurtadorurl.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UrlEncurtada criarUrl(String originalUrl, Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        String codigo = UUID.randomUUID().toString().substring(0, 6);
        String encurtada = "https://short.ly/" + codigo;
        UrlEncurtada nova = new UrlEncurtada(originalUrl, encurtada, usuario);
        return urlRepository.save(nova);
    }

    public UrlEncurtada buscarPorId(Long id) {
        return urlRepository.findById(id)
                .orElseThrow(() -> new CodigoNaoEncontradoException("URL com ID " + id + " não encontrada"));
    }

    public UrlEncurtada atualizarUrl(Long id, String novaOriginalUrl) {
        UrlEncurtada existente = buscarPorId(id);
        existente.setOriginalUrl(novaOriginalUrl);
        return urlRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!urlRepository.existsById(id)) {
            throw new CodigoNaoEncontradoException("ID não encontrado: " + id);
        }
        urlRepository.deleteById(id);
    }

    public List<UrlEncurtada> listarTodas() {
        return urlRepository.findAll();
    }

    public String shortenUrl(String originalUrl, Usuario usuario) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        String shortUrl = "https://short.ly/" + code;
        UrlEncurtada url = new UrlEncurtada(originalUrl, shortUrl, usuario);
        urlRepository.save(url);
        return code;
    }

    public String getOriginalUrl(String codigo) {
        return urlRepository.findAll().stream()
                .filter(url -> url.getShortUrl().endsWith(codigo))
                .map(UrlEncurtada::getOriginalUrl)
                .findFirst()
                .orElseThrow(() -> new CodigoNaoEncontradoException("Código " + codigo + " não encontrado"));
    }
}
