# Microblog API (Spring Boot)

Pequena API REST para microblog com funcionalidades:
- Criar usuários
- Criar posts
- Curtir e remover curtida
- Feed cronológico e por relevância

Como rodar:
1. Java 17 e Maven instalados.
2. `mvn spring-boot:run`
3. A API ficará disponível em `http://localhost:8080`.
4. H2 Console: `http://localhost:8080/h2-console` (jdbc url: jdbc:h2:mem:microblogdb)

Endpoints:
- POST /usuarios
- PUT /usuarios
- GET /usuarios
- POST /posts
- GET /posts/cronologico
- GET /posts/relevancia
- POST /posts/{postId}/curtir/{usuarioId}
- DELETE /posts/{postId}/curtir/{usuarioId}

Observações:
- Projeto usa H2 em memória por padrão para desenvolvimento.
- Use DTOs para requests.
