package com.example.microblog.controller;

import com.example.microblog.dto.UsuarioDto;
import com.example.microblog.model.Usuario;
import com.example.microblog.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody UsuarioDto dto) {
        Usuario u = Usuario.builder().nome(dto.getNome()).username(dto.getUsername()).build();
        Usuario saved = service.criar(u);
        return ResponseEntity.created(URI.create("/usuarios/" + saved.getId())).body(saved);
    }

    @PutMapping
    public ResponseEntity<Usuario> editar(@RequestBody UsuarioDto dto) {
        Usuario u = Usuario.builder().id(dto.getId()).nome(dto.getNome()).username(dto.getUsername()).build();
        Usuario updated = service.editar(u);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
