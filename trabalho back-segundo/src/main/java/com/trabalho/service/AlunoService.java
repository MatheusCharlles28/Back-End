package service;

import model.*;
import repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final AlunoDisciplinaRepository alunoDisciplinaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public AlunoService(AlunoRepository alunoRepository,
                        AlunoDisciplinaRepository alunoDisciplinaRepository,
                        DisciplinaRepository disciplinaRepository) {
        this.alunoRepository = alunoRepository;
        this.alunoDisciplinaRepository = alunoDisciplinaRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public Aluno save(Aluno a) {
        return alunoRepository.save(a);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    @Transactional
    public AlunoDisciplina update1Bim(Long alunoId, Long disciplinaId, Double nota1, Integer faltas1) {
        AlunoDisciplina ad = alunoDisciplinaRepository.findByAlunoIdAndDisciplinaId(alunoId, disciplinaId)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));
        ad.setNota1Bim(nota1);
        ad.setFaltas1Bim(faltas1);
        if (ad.getMatriculado() == null) ad.setMatriculado(true);
        ad.setSituacao(Situacao.EM_CURSO.name());
        return alunoDisciplinaRepository.save(ad);
    }

    @Transactional
    public AlunoDisciplina update2BimAndCalculateSituacao(Long alunoId, Long disciplinaId, Double nota2, Integer faltas2) {
        AlunoDisciplina ad = alunoDisciplinaRepository.findByAlunoIdAndDisciplinaId(alunoId, disciplinaId)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));
        ad.setNota2Bim(nota2);
        ad.setFaltas2Bim(faltas2);

        double n1 = ad.getNota1Bim() == null ? 0.0 : ad.getNota1Bim();
        double n2 = ad.getNota2Bim() == null ? 0.0 : ad.getNota2Bim();
        double media = (n1 + n2) / 2.0;

        int f1 = ad.getFaltas1Bim() == null ? 0 : ad.getFaltas1Bim();
        int f2 = ad.getFaltas2Bim() == null ? 0 : ad.getFaltas2Bim();
        int totalFaltas = f1 + f2;

        int maxFaltasPermitidas = 75;

        if (media >= 6.0 && totalFaltas <= maxFaltasPermitidas) {
            ad.setSituacao(Situacao.APROVADO.name());
            ad.setMatriculado(false);
        } else if (media < 6.0) {
            ad.setSituacao(Situacao.REPROVADO.name());
            if (ad.getMatriculado() == null) ad.setMatriculado(true);
        } else {
            ad.setSituacao(Situacao.REPROVADO.name());
            if (ad.getMatriculado() == null) ad.setMatriculado(true);
        }

        return alunoDisciplinaRepository.save(ad);
    }

    public List<AlunoDisciplina> boletim(Long alunoId) {
        return alunoDisciplinaRepository.findByAlunoId(alunoId);
    }
}
