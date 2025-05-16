package com.exemplo.encurtadorurl.controller;

import com.exemplo.encurtadorurl.Service.UsuarioService;
import com.exemplo.encurtadorurl.dto.UrlRequestDTO;
import com.exemplo.encurtadorurl.model.UrlEncurtada;
import com.exemplo.encurtadorurl.model.Usuario;
import com.exemplo.encurtadorurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/urls")
public class UrlController {

    @Autowired
    private UrlService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    public UrlEncurtada criar(@RequestParam String originalUrl, @RequestParam Long idUsuario) {
        return service.criarUrl(originalUrl, idUsuario);
    }

    @GetMapping("/{id}")
    public UrlEncurtada buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public UrlEncurtada atualizar(@PathVariable Long id, @RequestBody String novaOriginalUrl) {
        return service.atualizarUrl(id, novaOriginalUrl);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "URL deletada com sucesso.";
    }

    @PostMapping("/encurtar")
    public String encurtar(@RequestBody UrlRequestDTO request) {
        Usuario usuario = usuarioService.login(request.getUsername(), request.getSenha());
        if (usuario == null) {
            return "Usuário ou senha inválidos.";
        }
        String code = service.shortenUrl(request.getUrl(), usuario);
        return "https://localhost:8080/urls/r/" + code;
    }

    @GetMapping("/r/{code}")
    public RedirectView redirecionar(@PathVariable String code) {
        String originalUrl = service.getOriginalUrl(code);
        return new RedirectView(originalUrl);
    }

    @GetMapping
    public List<UrlEncurtada> listar() {
        return service.listarTodas();
    }
}
