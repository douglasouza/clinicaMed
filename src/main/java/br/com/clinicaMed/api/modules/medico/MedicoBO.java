package br.com.clinicaMed.api.modules.medico;

import br.com.clinicaMed.api.common.enumeration.EspecialidadeMedica;
import br.com.clinicaMed.api.common.exception.CrmNaoUnicoException;
import br.com.clinicaMed.api.common.exception.LoginNaoUnicoException;
import br.com.clinicaMed.security.usuario.Usuario;
import br.com.clinicaMed.security.usuario.UsuarioRepository;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedicoBO {

    @Autowired
    private MedicoRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepo;

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
        List<MedicoDTO> medicosDTO = new ArrayList<MedicoDTO>();
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

        if (existeMedicoCadastradoComMesmoCrm(medico))
            throw new CrmNaoUnicoException();

        Usuario usuario = usuarioRepo.save(medico.getUsuario());
        medico.setId(null);
        medico.setUsuario(usuario);
        return repo.saveAndFlush(medico);
    }

    public Medico atualizarMedico(Medico updatedMedico, Long id) {
        if (existeUsuarioCadastradoComMesmoLogin(updatedMedico))
            throw new LoginNaoUnicoException();

        if (existeMedicoCadastradoComMesmoCrm(updatedMedico))
            throw new CrmNaoUnicoException();

        Usuario usuario = usuarioRepo.save(updatedMedico.getUsuario());
        updatedMedico.setId(id);
        updatedMedico.setUsuario(usuario);
        return repo.saveAndFlush(updatedMedico);
    }

    private Boolean existeMedicoCadastradoComMesmoCrm(Medico medico) {
        Medico medicoComMesmoCrm = repo.findByCrm(medico.getCrm());
        return medicoComMesmoCrm != null && (medicoComMesmoCrm.getId() != medico.getId());
    }

    private Boolean existeUsuarioCadastradoComMesmoLogin(Medico medico) {
        Usuario usuarioComMesmoLogin = usuarioRepo.findByLogin(medico.getUsuario().getLogin());
        return usuarioComMesmoLogin != null && (usuarioComMesmoLogin.getId() != medico.getUsuario().getId());
    }
}
