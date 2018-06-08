package br.com.clinicamed.api.modules.medico;

import br.com.clinicamed.api.common.enumeration.EspecialidadeMedica;
import br.com.clinicamed.api.common.exception.CrmInvalidoException;
import br.com.clinicamed.api.common.exception.CrmNaoUnicoException;
import br.com.clinicamed.api.common.exception.LoginNaoUnicoException;
import br.com.clinicamed.api.common.exception.MedicoPossuiConsultaMarcadaOuRealizada;
import br.com.clinicamed.api.common.exception.MedicoPossuiSolicitacaoExameCadastrada;
import br.com.clinicamed.api.common.utils.CrmUtils;
import br.com.clinicamed.api.modules.consulta.Consulta;
import br.com.clinicamed.api.modules.consulta.ConsultaRepository;
import br.com.clinicamed.api.modules.paciente.Paciente;
import br.com.clinicamed.api.modules.paciente.PacienteRepository;
import br.com.clinicamed.api.modules.solicitacaoexame.SolicitacaoExame;
import br.com.clinicamed.api.modules.solicitacaoexame.SolicitacaoExameRepository;
import br.com.clinicamed.security.usuario.Usuario;
import br.com.clinicamed.security.usuario.UsuarioBO;
import br.com.clinicamed.security.usuario.UsuarioRepository;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MedicoBO {

    @Autowired
    private MedicoRepository repo;

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioBO usuarioBO;

    @Autowired
    private ConsultaRepository consultaRepo;

    @Autowired
    private SolicitacaoExameRepository solicitacaoExameRepo;

    @Autowired
    private CrmUtils crmUtils;

    public Object pesquisarMedico(String nomeCrmLogin, EspecialidadeMedica especialidade) {
        Iterable<Medico> medicos;
        if (StringUtils.isEmpty(nomeCrmLogin) && StringUtils.isEmpty(especialidade))
            medicos = repo.findAll();
        else
            medicos = repo.findAll(getBooleanExpression(nomeCrmLogin, especialidade));

        return getMedicosDTO(medicos);
    }

    private BooleanExpression getBooleanExpression(String nomeCrmLogin, EspecialidadeMedica especialidade) {
        BooleanExpression booleanExpression = null;
        if (!StringUtils.isEmpty(nomeCrmLogin))
            booleanExpression = QMedico.medico.nome.containsIgnoreCase(nomeCrmLogin)
                    .or(QMedico.medico.crm.containsIgnoreCase(nomeCrmLogin))
                    .or(QMedico.medico.usuario.login.containsIgnoreCase(nomeCrmLogin));

        if (!StringUtils.isEmpty(especialidade)) {
            if (booleanExpression == null)
                booleanExpression = QMedico.medico.especialidade.eq(especialidade);
            else
                booleanExpression = booleanExpression.and(QMedico.medico.especialidade.eq(especialidade));
        }

        return booleanExpression;
    }

    private List<MedicoDTO> getMedicosDTO(Iterable<Medico> medicos) {
        List<MedicoDTO> medicosDTO = new ArrayList<>();
        for (Medico medico : medicos) {
            MedicoDTO medicoDTO = new MedicoDTO();
            medicoDTO.setId(medico.getId());
            medicoDTO.setNome(medico.getNome());
            medicoDTO.setEspecialidade(medico.getEspecialidade().toString());
            medicoDTO.setCrm(medico.getCrm());
            medicoDTO.setLogin(medico.getUsuario().getLogin());
            medicosDTO.add(medicoDTO);
        }

        return medicosDTO;
    }

    public Medico inserirMedico(Medico medico) {
        if (existeUsuarioCadastradoComMesmoLogin(medico))
            throw new LoginNaoUnicoException();

        if (!crmUtils.ehCrmValido(medico.getCrm()))
            throw new CrmInvalidoException();

        if (crmUtils.existeOutroCadastradoComMesmoCrm(medico))
            throw new CrmNaoUnicoException();

        Usuario usuario = usuarioBO.salvarUsuario(medico.getUsuario());
        medico.setId(null);
        medico.setUsuario(usuario);
        return repo.saveAndFlush(medico);
    }

    public Medico atualizarMedico(Medico updatedMedico, Long id) {
        if (existeUsuarioCadastradoComMesmoLogin(updatedMedico))
            throw new LoginNaoUnicoException();

        if (!crmUtils.ehCrmValido(updatedMedico.getCrm()))
            throw new CrmInvalidoException();

        if (crmUtils.existeOutroCadastradoComMesmoCrm(updatedMedico))
            throw new CrmNaoUnicoException();

        Usuario usuario = usuarioBO.salvarUsuario(updatedMedico.getUsuario());
        updatedMedico.setId(id);
        updatedMedico.setUsuario(usuario);
        return repo.saveAndFlush(updatedMedico);
    }

    public void removerMedico(Long idMedico) {
        List<Consulta> consultasMedico = consultaRepo.buscarConsultasPorMedico(idMedico);
        if (!CollectionUtils.isEmpty(consultasMedico))
            throw new MedicoPossuiConsultaMarcadaOuRealizada();

        List<SolicitacaoExame> solicitacoesExamePaciente = solicitacaoExameRepo.buscarSolicitacoesExamePorPaciente(idMedico);
        if (!CollectionUtils.isEmpty(solicitacoesExamePaciente))
            throw new MedicoPossuiSolicitacaoExameCadastrada();

        Medico medico = repo.findOne(idMedico);
        repo.delete(medico.getId());
        usuarioRepo.delete(medico.getUsuario().getId());
    }

    private Boolean existeUsuarioCadastradoComMesmoLogin(Medico medico) {
        Usuario usuarioComMesmoLogin = usuarioRepo.findByLogin(medico.getUsuario().getLogin());
        return usuarioComMesmoLogin != null && (usuarioComMesmoLogin.getId() != medico.getUsuario().getId());
    }

    public List<Paciente> buscarPacientesAtendidosPorMedico(Long idMedico) {
        return pacienteRepo.buscarPacientesAtendidosPorMedico(idMedico, new Date());
    }
}
