package controller;

import model.*;
import service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/aluno")
@CrossOrigin(origins = "*")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> criar(@Valid @RequestBody Aluno aluno) {
        Aluno saved = alunoService.save(aluno);
        return ResponseEntity.ok(saved);
    }

    @PutMapping
    public ResponseEntity<Aluno> atualizar(@Valid @RequestBody Aluno aluno) {
        Aluno saved = alunoService.save(aluno);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        return alunoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Aluno>> todos() {
        return ResponseEntity.ok(alunoService.findAll());
    }

    @PutMapping("/{idAluno}/disciplina/{idDisciplina}/1bim")
    public ResponseEntity<?> atualizar1Bim(@PathVariable Long idAluno,
                                          @PathVariable Long idDisciplina,
                                          @RequestBody java.util.Map<String,Object> body) {
        Double nota = body.get("nota1Bim") == null ? null : Double.valueOf(body.get("nota1Bim").toString());
        Integer faltas = body.get("faltas1Bim") == null ? null : Integer.valueOf(body.get("faltas1Bim").toString());
        try {
            AlunoDisciplina ad = alunoService.update1Bim(idAluno, idDisciplina, nota, faltas);
            return ResponseEntity.ok(ad);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{idAluno}/disciplina/{idDisciplina}/2bim")
    public ResponseEntity<?> atualizar2Bim(@PathVariable Long idAluno,
                                          @PathVariable Long idDisciplina,
                                          @RequestBody java.util.Map<String,Object> body) {
        Double nota = body.get("nota2Bim") == null ? null : Double.valueOf(body.get("nota2Bim").toString());
        Integer faltas = body.get("faltas2Bim") == null ? null : Integer.valueOf(body.get("faltas2Bim").toString());
        try {
            AlunoDisciplina ad = alunoService.update2BimAndCalculateSituacao(idAluno, idDisciplina, nota, faltas);
            return ResponseEntity.ok(ad);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}/boletim")
    public ResponseEntity<List<AlunoDisciplina>> boletim(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.boletim(id));
    }
}
