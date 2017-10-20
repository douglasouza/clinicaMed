package br.com.clinicaMed.business;

import br.com.clinicaMed.entity.Medico;
import br.com.clinicaMed.entity.QMedico;
import br.com.clinicaMed.entity.Usuario;
import br.com.clinicaMed.enumeration.EspecialidadeMedica;
import br.com.clinicaMed.exception.CrmNaoUnicoException;
import br.com.clinicaMed.exception.LoginNaoUnicoException;
import br.com.clinicaMed.repository.MedicoRepository;
import br.com.clinicaMed.repository.UsuarioRepository;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MedicoBO {

    @Autowired
    private MedicoRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Object pesquisarMedico(String nomeCrmLogin, EspecialidadeMedica especialidade) {
        if (StringUtils.isEmpty(nomeCrmLogin) && StringUtils.isEmpty(especialidade))
            return repo.findAll();
        else
            return repo.findAll(getBooleanExpression(nomeCrmLogin, especialidade));
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

    private Boolean existeMedicoCadastradoComMesmoCrm(Medico medico) {
        Medico medicoComMesmoCrm = repo.findByCrm(medico.getCrm());
        return medicoComMesmoCrm != null && (medicoComMesmoCrm.getId() != medico.getId());
    }

    private Boolean existeUsuarioCadastradoComMesmoLogin(Medico medico) {
        Usuario usuarioComMesmoLogin = usuarioRepo.findByLogin(medico.getUsuario().getLogin());
        return usuarioComMesmoLogin != null && (usuarioComMesmoLogin.getId() != medico.getUsuario().getId());
    }
}
