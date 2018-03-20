package br.com.clinicamed.api.modules.solicitacaoexame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoExameRepository extends JpaRepository<SolicitacaoExame, Long>, QueryDslPredicateExecutor<SolicitacaoExame> {

    @Query("SELECT se FROM SolicitacaoExame se WHERE se.medico.id = ?1")
    List<SolicitacaoExame> buscarSolicitacoesExamePorMedico(Long idMedico);

    @Query("SELECT se FROM SolicitacaoExame se WHERE se.paciente.id = ?1")
    List<SolicitacaoExame> buscarSolicitacoesExamePorPaciente(Long idPaciente);

}