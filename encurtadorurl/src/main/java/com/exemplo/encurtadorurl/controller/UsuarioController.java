package com.exemplo.encurtadorurl.controller;

import com.exemplo.encurtadorurl.model.Usuario;
import com.exemplo.encurtadorurl.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public String cadastrar(@RequestBody Usuario usuario) {
        boolean sucesso = usuarioService.cadastrar(usuario);
        return sucesso ? "Usuário cadastrado com sucesso!" : "Nome de usuário já existe.";
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        Usuario logado = usuarioService.login(usuario.getUsername(), usuario.getSenha());
        return (logado != null) ? "Login bem-sucedido!" : "Usuário ou senha inválidos.";
    }
}
