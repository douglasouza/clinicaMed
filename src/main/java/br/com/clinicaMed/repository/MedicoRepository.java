package br.com.clinicaMed.repository;

import br.com.clinicaMed.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MedicoRepository extends JpaRepository<Medico, Long>, QueryDslPredicateExecutor<Medico> {

}