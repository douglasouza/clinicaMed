package br.com.clinicamed.api.modules.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>, QueryDslPredicateExecutor<Paciente> {

    Paciente findByCpf(String cpf);

    @Query("SELECT co.paciente FROM Consulta co" +
            " WHERE co.medico.id = ?1 AND co.dataConsulta < ?2")
    List<Paciente> buscarPacientesAtendidosPorMedico(Long idMedico, Date dataAtual);
}