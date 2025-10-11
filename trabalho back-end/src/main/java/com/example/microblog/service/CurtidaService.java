package com.example.microblog.service;

import com.example.microblog.model.Curtida;
import com.example.microblog.model.Post;
import com.example.microblog.model.Usuario;
import com.example.microblog.repository.CurtidaRepository;
import com.example.microblog.repository.PostRepository;
import com.example.microblog.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class CurtidaService {
    private final CurtidaRepository curtidaRepo;
    private final PostRepository postRepo;
    private final UsuarioRepository usuarioRepo;

    public CurtidaService(CurtidaRepository curtidaRepo, PostRepository postRepo, UsuarioRepository usuarioRepo) {
        this.curtidaRepo = curtidaRepo;
        this.postRepo = postRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Transactional
    public void curtir(Long postId, Long usuarioId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
        Usuario usuario = usuarioRepo.findById(usuarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (curtidaRepo.existsByPostAndUsuario(post, usuario)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já curtiu esse post");
        }

        Curtida c = Curtida.builder().post(post).usuario(usuario).build();
        curtidaRepo.save(c);

        post.setLikesCount(post.getLikesCount() + 1);
        postRepo.save(post);
    }

    @Transactional
    public void descurtir(Long postId, Long usuarioId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
        Usuario usuario = usuarioRepo.findById(usuarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Curtida curtida = curtidaRepo.findByPostAndUsuario(post, usuario).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curtida não existe"));
        curtidaRepo.delete(curtida);

        post.setLikesCount(Math.max(0, post.getLikesCount() - 1));
        postRepo.save(post);
    }
}
