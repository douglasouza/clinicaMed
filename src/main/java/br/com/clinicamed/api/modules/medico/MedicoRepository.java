package br.com.clinicamed.api.modules.medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>, QueryDslPredicateExecutor<Medico> {

    Medico findByCrm(String crm);
}