package model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "aulas_dadas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AulaDada {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    private LocalDate data;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
