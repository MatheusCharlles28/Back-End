package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "disciplina", uniqueConstraints = {
    @UniqueConstraint(columnNames = "codigo")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Disciplina {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @NotBlank
    private String codigo;

    @NotBlank
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String ementa;
}
