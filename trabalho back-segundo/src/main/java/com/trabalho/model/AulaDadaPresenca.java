package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aulas_dadas_presencas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AulaDadaPresenca {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aula_dada_id")
    private AulaDada aulaDada;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    private Boolean falta;
}
