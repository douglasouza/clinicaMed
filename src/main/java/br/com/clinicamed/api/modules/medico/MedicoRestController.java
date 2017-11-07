package br.com.clinicamed.api.modules.medico;

import br.com.clinicamed.api.common.enumeration.EspecialidadeMedica;
import br.com.clinicamed.api.modules.consulta.horarioconsulta.Horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoRestController {

    @Autowired
    private MedicoRepository repo;

    @Autowired
    private MedicoBO bo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(String nomeCrmLogin, EspecialidadeMedica especialidade) {
        return bo.pesquisarMedico(nomeCrmLogin, especialidade);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Medico findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Medico insert(@RequestBody Medico medico) throws Exception {
        return bo.inserirMedico(medico);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Medico update(@RequestBody Medico updatedMedico, @PathVariable Long id) {
        return bo.atualizarMedico(updatedMedico, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        bo.removerMedico(id);
    }

    @RequestMapping(value = "/{id}/horariosDisponiveis", method = RequestMethod.GET)
    public List<Horario> getHorariosDisponiveis(@PathVariable Long id, Date data) {
        return bo.getHorariosDisponiveis(id, data);
    }
}