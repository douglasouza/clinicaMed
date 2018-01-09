package br.com.clinicamed.api.modules.solicitacaoexame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoExameRepository extends JpaRepository<SolicitacaoExame, Long>, QueryDslPredicateExecutor<SolicitacaoExame> {

}