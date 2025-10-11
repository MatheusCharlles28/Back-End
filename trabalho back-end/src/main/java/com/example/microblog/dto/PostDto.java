package com.example.microblog.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String conteudo;
    private Long autorId;
}
