package br.com.clinicamed.api.modules.paciente;

import br.com.clinicamed.api.common.exception.CpfInvalidoException;
import br.com.clinicamed.api.common.exception.CpfNaoUnicoException;
import br.com.clinicamed.api.common.exception.PacientePossuiConsultaMarcadaOuRealizada;
import br.com.clinicamed.api.common.exception.PacientePossuiPrescricaoCadastrada;
import br.com.clinicamed.api.common.exception.PacientePossuiSolicitacaoExameCadastrada;
import br.com.clinicamed.api.common.utils.CpfUtils;
import br.com.clinicamed.api.modules.consulta.Consulta;
import br.com.clinicamed.api.modules.consulta.ConsultaRepository;
import br.com.clinicamed.api.modules.prescricao.Prescricao;
import br.com.clinicamed.api.modules.prescricao.PrescricaoRepository;
import br.com.clinicamed.api.modules.solicitacaoexame.SolicitacaoExame;
import br.com.clinicamed.api.modules.solicitacaoexame.SolicitacaoExameRepository;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Component
public class PacienteBO {

    @Autowired
    private PacienteRepository repo;

    @Autowired
    private ConsultaRepository consultaRepo;

    @Autowired
    private PrescricaoRepository prescricaoRepo;

    @Autowired
    private SolicitacaoExameRepository solicitacaoExameRepo;

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
        paciente.setDataHoraCadastro(new Date());
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

    public void removerPaciente(Long idPaciente) {
        List<Consulta> consultasPaciente = consultaRepo.buscarConsultasPorMedico(idPaciente);
        if (!CollectionUtils.isEmpty(consultasPaciente))
            throw new PacientePossuiConsultaMarcadaOuRealizada();

        List<Prescricao> prescricoesPaciente = prescricaoRepo.buscarPrescricoesPorPaciente(idPaciente);
        if (!CollectionUtils.isEmpty(prescricoesPaciente))
            throw new PacientePossuiPrescricaoCadastrada();

        List<SolicitacaoExame> solicitacoesExamePaciente = solicitacaoExameRepo.buscarSolicitacoesExamePorPaciente(idPaciente);
        if (!CollectionUtils.isEmpty(solicitacoesExamePaciente))
            throw new PacientePossuiSolicitacaoExameCadastrada();

        repo.delete(idPaciente);
    }
}
