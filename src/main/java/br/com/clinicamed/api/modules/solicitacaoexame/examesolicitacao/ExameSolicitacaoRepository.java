package br.com.clinicamed.api.modules.solicitacaoexame.examesolicitacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameSolicitacaoRepository extends JpaRepository<ExameSolicitacao, Long> {

    @Query("SELECT es.exame FROM ExameSolicitacao es WHERE es.solicitacaoExame.id = ?1")
    List<ExameSolicitacao> getExames(Long idSolicitacao);

    @Modifying
    @Query(value="DELETE FROM ExameSolicitacao es WHERE es.solicitacaoExame.id = ?1")
    void removerExames(Long idSolicitacao);

}