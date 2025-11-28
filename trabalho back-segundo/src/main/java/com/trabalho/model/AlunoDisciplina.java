package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alunos_disciplina")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AlunoDisciplina {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    private Double nota1Bim;
    private Double nota2Bim;
    private Integer faltas1Bim;
    private Integer faltas2Bim;
    private Boolean matriculado;
    private String situacao;
}
