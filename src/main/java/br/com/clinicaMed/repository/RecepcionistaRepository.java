package br.com.clinicaMed.repository;

import br.com.clinicaMed.entity.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long>, QueryDslPredicateExecutor<Recepcionista> {

}