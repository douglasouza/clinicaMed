package br.com.clinicamed.api.modules.prescricao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescricaoRepository extends JpaRepository<Prescricao, Long>, QueryDslPredicateExecutor<Prescricao> {

    @Query("SELECT pr FROM Prescricao pr WHERE pr.paciente.id = ?1")
    List<Prescricao> buscarPrescricoesPorPaciente(Long idPaciente);

    @Query("SELECT pr FROM Prescricao pr WHERE pr.medicamento.id = ?1")
    List<Prescricao> buscarPrescricoesPorMedicamento(Long idMedicamento);

}