package br.com.clinicamed.api.modules.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>, QueryDslPredicateExecutor<Consulta> {

    @Query("SELECT co FROM Consulta co WHERE co.medico.id = ?1")
    List<Consulta> buscarConsultasPorMedico(Long idMedico);

    @Query("SELECT co FROM Consulta co WHERE co.paciente.id = ?1")
    List<Consulta> buscarConsultasPorPaciente(Long idPaciente);
}