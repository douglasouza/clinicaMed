package br.com.clinicamed.api.modules.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>, QueryDslPredicateExecutor<Paciente> {

    Paciente findByCpf(String cpf);
}