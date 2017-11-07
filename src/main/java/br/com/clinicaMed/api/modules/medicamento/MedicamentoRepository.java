package br.com.clinicaMed.api.modules.medicamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>, QueryDslPredicateExecutor<Medicamento> {

}