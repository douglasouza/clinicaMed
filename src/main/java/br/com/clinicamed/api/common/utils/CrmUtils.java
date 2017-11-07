package br.com.clinicamed.api.common.utils;

import br.com.clinicamed.api.modules.medico.Medico;
import br.com.clinicamed.api.modules.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrmUtils {

    @Autowired
    private MedicoRepository repo;

    public Boolean existeOutroCadastradoComMesmoCrm(Medico medico) {
        Medico medicoComMesmoCrm = repo.findByCrm(medico.getCrm());
        return medicoComMesmoCrm != null && (medicoComMesmoCrm.getId() != medico.getId());
    }

    public boolean ehCrmValido(String crm) {
        return contemSomenteNumeros(crm);
    }

    private static boolean contemSomenteNumeros(String crm) {
        return crm.matches("[0-9]+") && (crm.length() >= 4 && crm.length() <= 5);
    }
}
