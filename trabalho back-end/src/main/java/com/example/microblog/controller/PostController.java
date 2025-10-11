package com.example.microblog.controller;

import com.example.microblog.dto.PostDto;
import com.example.microblog.model.Post;
import com.example.microblog.model.Usuario;
import com.example.microblog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Post> criar(@RequestBody PostDto dto) {
        Usuario autor = Usuario.builder().id(dto.getAutorId()).build();
        Post p = Post.builder().conteudo(dto.getConteudo()).autor(autor).build();
        Post saved = service.criar(p);
        return ResponseEntity.created(URI.create("/posts/" + saved.getId())).body(saved);
    }

    @GetMapping("/cronologico")
    public ResponseEntity<Page<Post>> cronologico(Pageable pageable) {
        return ResponseEntity.ok(service.feedCronologico(pageable));
    }

    @GetMapping("/relevancia")
    public ResponseEntity<Page<Post>> relevancia(Pageable pageable) {
        return ResponseEntity.ok(service.feedRelevancia(pageable));
    }
}
