package br.com.clinicaMed.business;

import br.com.clinicaMed.dto.PacienteDTO;
import br.com.clinicaMed.entity.Paciente;
import br.com.clinicaMed.entity.QPaciente;
import br.com.clinicaMed.exception.CpfInvalidoException;
import br.com.clinicaMed.exception.CpfNaoUnicoException;
import br.com.clinicaMed.repository.PacienteRepository;
import br.com.clinicaMed.utils.CpfUtils;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PacienteBO {

    @Autowired
    private PacienteRepository repo;

    public Object pesquisarPaciente(String nome) {
        Iterable<Paciente> pacientes;
        if (StringUtils.isEmpty(nome))
            pacientes = repo.findAll();
        else
            pacientes = repo.findAll(getBooleanExpression(nome));

        return getPacientesDTO(pacientes);
    }

    private BooleanExpression getBooleanExpression(String nome) {
        return QPaciente.paciente.nome.containsIgnoreCase(nome);
    }

    private List<PacienteDTO> getPacientesDTO(Iterable<Paciente> pacientes) {
        List<PacienteDTO> pacientesDTO = new ArrayList<PacienteDTO>();
        for (Paciente paciente : pacientes)
            pacientesDTO.add(new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getSexo().toString()));

        return pacientesDTO;
    }

    public Paciente inserirPaciente(Paciente paciente) {
        validarCPF(paciente);
        paciente.setId(null);
        return repo.saveAndFlush(paciente);
    }

    public Paciente atualizarPaciente(Paciente updatedPaciente, Long id) {
        validarCPF(updatedPaciente);
        updatedPaciente.setId(id);
        return repo.saveAndFlush(updatedPaciente);
    }

    private void validarCPF(Paciente paciente) {
        if (existePacienteCadastradoComCpf(paciente))
            throw new CpfNaoUnicoException();

        if (!CpfUtils.ehCPFValido(paciente.getCpf()))
            throw new CpfInvalidoException();
    }

    private Boolean existePacienteCadastradoComCpf(Paciente paciente) {
        Paciente pacienteComMesmoLogin = repo.findByCpf(paciente.getCpf());
        return pacienteComMesmoLogin != null && (pacienteComMesmoLogin.getId() != paciente.getId());
    }

}
