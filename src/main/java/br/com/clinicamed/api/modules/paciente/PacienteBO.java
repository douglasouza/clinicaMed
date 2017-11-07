package br.com.clinicamed.api.modules.paciente;

import br.com.clinicamed.api.common.exception.CpfInvalidoException;
import br.com.clinicamed.api.common.exception.CpfNaoUnicoException;
import br.com.clinicamed.api.common.utils.CpfUtils;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PacienteBO {

    @Autowired
    private PacienteRepository repo;

    @Autowired
    private CpfUtils cpfUtils;

    public Object pesquisarPaciente(String nome) {
        if (StringUtils.isEmpty(nome))
            return repo.findAll();
        else
            return repo.findAll(getBooleanExpression(nome));
    }

    private BooleanExpression getBooleanExpression(String nome) {
        return QPaciente.paciente.nome.containsIgnoreCase(nome);
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
        if (cpfUtils.existeOutroCadastradoComMesmoCpf(paciente))
            throw new CpfNaoUnicoException();

        if (!cpfUtils.ehCpfValido(paciente.getCpf()))
            throw new CpfInvalidoException();
    }
}
