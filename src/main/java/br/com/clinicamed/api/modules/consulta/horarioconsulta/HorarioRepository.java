package br.com.clinicamed.api.modules.consulta.horarioconsulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query("SELECT ho FROM Horario ho" +
            " WHERE ho.id NOT IN" +
            " (SELECT horarioConsulta.id FROM Consulta co" +
            "  WHERE (co.medico.id = ?1 OR co.paciente.id = ?2) AND co.dataConsulta = ?3)")
    List<Horario> getHorariosDisponiveisPorMedicoEPaciente(Long idMedico, Long idPaciente, Date date);
}