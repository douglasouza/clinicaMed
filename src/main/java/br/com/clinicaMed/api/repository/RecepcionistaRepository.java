package br.com.clinicaMed.api.repository;

import br.com.clinicaMed.api.entity.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long>, QueryDslPredicateExecutor<Recepcionista> {

    Recepcionista findByCpf(String cpf);
}