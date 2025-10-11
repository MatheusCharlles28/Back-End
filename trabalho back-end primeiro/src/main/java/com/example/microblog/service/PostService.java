package com.example.microblog.service;

import com.example.microblog.model.Post;
import com.example.microblog.model.Usuario;
import com.example.microblog.repository.PostRepository;
import com.example.microblog.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class PostService {
    private final PostRepository postRepo;
    private final UsuarioRepository usuarioRepo;

    public PostService(PostRepository postRepo, UsuarioRepository usuarioRepo) {
        this.postRepo = postRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public Post criar(Post p) {
        if (p.getAutor() == null || p.getAutor().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autor obrigatório");
        }
        Usuario autor = usuarioRepo.findById(p.getAutor().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
        p.setAutor(autor);
        return postRepo.save(p);
    }

    public Page<Post> feedCronologico(Pageable pageable){
        return postRepo.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<Post> feedRelevancia(Pageable pageable){
        return postRepo.findAllByOrderByLikesCountDesc(pageable);
    }

    public Post findById(Long id){
        return postRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
    }
}
