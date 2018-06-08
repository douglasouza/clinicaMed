package br.com.clinicamed.api.modules.medicamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>, QueryDslPredicateExecutor<Medicamento> {

    @Query("SELECT me FROM Medicamento me WHERE me.nomeFabrica = ?1")
    Medicamento buscarMedicamentoPorNomeFabrica(String nomeFabrica);
}