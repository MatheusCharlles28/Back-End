package com.example.microblog.controller;

import com.example.microblog.service.CurtidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class CurtidaController {
    private final CurtidaService service;

    public CurtidaController(CurtidaService service) { this.service = service; }

    @PostMapping("/{postId}/curtir/{usuarioId}")
    public ResponseEntity<Void> curtir(@PathVariable Long postId, @PathVariable Long usuarioId) {
        service.curtir(postId, usuarioId);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{postId}/curtir/{usuarioId}")
    public ResponseEntity<Void> descurtir(@PathVariable Long postId, @PathVariable Long usuarioId) {
        service.descurtir(postId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
