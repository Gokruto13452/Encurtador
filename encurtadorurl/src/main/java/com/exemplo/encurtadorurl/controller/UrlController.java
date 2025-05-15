package com.exemplo.encurtadorurl.controller;

import com.exemplo.encurtadorurl.Service.UsuarioService;
import com.exemplo.encurtadorurl.dto.UrlRequestDTO;
import com.exemplo.encurtadorurl.model.Usuario;
import com.exemplo.encurtadorurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/")
@Tag(name = "Encurtador de URLs", description = "Endpoints para encurtar e redirecionar URLs")
public class UrlController {

    @Autowired
    private UrlService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/encurtar")
    @Operation(
            summary = "Encurtar uma URL",
            description = "Recebe uma URL e os dados de login do usuário e retorna uma URL encurtada."
    )
    public String encurtar(@RequestBody UrlRequestDTO request) {
        Usuario usuario = usuarioService.login(request.getUsername(), request.getSenha());
        if (usuario == null) {
            return "Usuário ou senha Inválidos.";
        }

        String code = service.shortenUrl(request.getUrl(), usuario);
        return "https://localhost:8080/" + code;
    }

    @GetMapping("/{code}")
    @Operation(
            summary = "Redirecionar URL",
            description = "Recebe um código encurtado e redireciona para a URL original."
    )
    public RedirectView redirecionar(@PathVariable String code) {
        String originalUrl = service.getOriginalUrl(code);
        return new RedirectView(originalUrl);
    }
}
