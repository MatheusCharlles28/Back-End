package com.example.microblog.service;

import com.example.microblog.model.Usuario;
import com.example.microblog.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario criar(Usuario u){
        if (u.getUsername() == null || u.getUsername().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username obrigatório");
        }
        if (repo.findByUsername(u.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username já existe");
        }
        return repo.save(u);
    }

    public Usuario editar(Usuario u) {
        if (u.getId() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id obrigatório");
        Usuario existent = repo.findById(u.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        existent.setNome(u.getNome());
        existent.setUsername(u.getUsername());
        return repo.save(existent);
    }

    public List<Usuario> listar(){
        return repo.findAll();
    }

    public Usuario findById(Long id){
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
}
