package com.exemplo.encurtadorurl.Service;

import com.exemplo.encurtadorurl.model.Usuario;
import com.exemplo.encurtadorurl.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean cadastrar(Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            return false; // Usuário já existe
        }
        usuarioRepository.save(usuario);
        return true;
    }

    public Usuario login(String username, String senha) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }
}
