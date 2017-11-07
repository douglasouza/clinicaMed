package br.com.clinicamed.api.modules.recepcionista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long>, QueryDslPredicateExecutor<Recepcionista> {

    Recepcionista findByCpf(String cpf);
}