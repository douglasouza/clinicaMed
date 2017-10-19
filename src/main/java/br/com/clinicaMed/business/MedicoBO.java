package br.com.clinicaMed.business;

import br.com.clinicaMed.entity.QMedico;
import br.com.clinicaMed.enumeration.EspecialidadeMedica;
import br.com.clinicaMed.repository.MedicoRepository;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MedicoBO {

    @Autowired
    private MedicoRepository repository;

    public Object pesquisarMedico(String nomeCrmLogin, EspecialidadeMedica especialidade) {
        if (StringUtils.isEmpty(nomeCrmLogin) && StringUtils.isEmpty(especialidade))
            return repository.findAll();
        else
            return repository.findAll(getBooleanExpression(nomeCrmLogin, especialidade));
    }

    private BooleanExpression getBooleanExpression(String nomeCrmLogin, EspecialidadeMedica especialidade) {
        BooleanExpression booleanExpression = null;
        if (!StringUtils.isEmpty(nomeCrmLogin))
            booleanExpression = QMedico.medico.nome.containsIgnoreCase(nomeCrmLogin)
                    .or(QMedico.medico.crm.containsIgnoreCase(nomeCrmLogin))
                    .or(QMedico.medico._super.login.containsIgnoreCase(nomeCrmLogin));

        if (!StringUtils.isEmpty(especialidade)) {
            if (booleanExpression == null)
                booleanExpression = QMedico.medico.especialidade.eq(especialidade);
            else
                booleanExpression = booleanExpression.and(QMedico.medico.especialidade.eq(especialidade));
        }

        return booleanExpression;
    }
}
