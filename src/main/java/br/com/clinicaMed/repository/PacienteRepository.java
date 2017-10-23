package br.com.clinicaMed.repository;

import br.com.clinicaMed.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PacienteRepository extends JpaRepository<Paciente, Long>, QueryDslPredicateExecutor<Paciente> {

}