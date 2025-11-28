package repository;

import model.AlunoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface AlunoDisciplinaRepository extends JpaRepository<AlunoDisciplina, Long> {
    Optional<AlunoDisciplina> findByAlunoIdAndDisciplinaId(Long alunoId, Long disciplinaId);
    List<AlunoDisciplina> findByDisciplinaIdAndMatriculadoTrue(Long disciplinaId);
    List<AlunoDisciplina> findByAlunoId(Long alunoId);
}
