package br.com.clinicaMed.business;

import br.com.clinicaMed.entity.Medico;
import br.com.clinicaMed.entity.QMedico;
import br.com.clinicaMed.entity.Usuario;
import br.com.clinicaMed.enumeration.EspecialidadeMedica;
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
        Usuario usuario = usuarioRepo.save(medico.getUsuario());
        medico.setId(null);
        medico.setUsuario(usuario);
        return repo.saveAndFlush(medico);
    }

    public Medico atualizarMedico(Medico updatedMedico, Long id) {
        Usuario usuario = usuarioRepo.save(updatedMedico.getUsuario());
        updatedMedico.setId(id);
        updatedMedico.setUsuario(usuario);
        return repo.saveAndFlush(updatedMedico);
    }

    private BooleanExpression getBooleanExpression(String nomeCrmLogin, EspecialidadeMedica especialidade) {
        BooleanExpression booleanExpression = null;
        if (!StringUtils.isEmpty(nomeCrmLogin))
            booleanExpression = QMedico.medico.nome.containsIgnoreCase(nomeCrmLogin)
                    .or(QMedico.medico.crm.like("%" + nomeCrmLogin + "%"))
                    .or(QMedico.medico.usuario.login.containsIgnoreCase(nomeCrmLogin));

        if (!StringUtils.isEmpty(especialidade)) {
            if (booleanExpression == null)
                booleanExpression = QMedico.medico.especialidade.eq(especialidade);
            else
                booleanExpression = booleanExpression.and(QMedico.medico.especialidade.eq(especialidade));
        }

        return booleanExpression;
    }
}
