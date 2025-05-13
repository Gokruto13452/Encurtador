package com.exemplo.encurtadorurl.controller;

import com.exemplo.encurtadorurl.Service.UsuarioService;
import com.exemplo.encurtadorurl.dto.UrlRequestDTO;
import com.exemplo.encurtadorurl.model.Usuario;
import com.exemplo.encurtadorurl.service.UrlService; // corrigido
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {

    @Autowired
    private UrlService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/encurtar")
    public String encurtar(@RequestBody UrlRequestDTO request) {
        Usuario usuario = usuarioService.login(request.getUsername(), request.getSenha());
        if (usuario == null) {
            return "Usuário ou senha Inválidos.";
        }

        String code = service.shortenUrl(request.getUrl(), usuario);
        return "https://localhost:8080/" + code;
    }

    @GetMapping("/{code}")
    public RedirectView redirecionar(@PathVariable String code) {
        String originalUrl = service.getOriginalUrl(code);
        return new RedirectView(originalUrl);
    }
}
