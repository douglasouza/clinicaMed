package br.com.clinicaMed.service;

import br.com.clinicaMed.business.MedicoBO;
import br.com.clinicaMed.entity.Medico;
import br.com.clinicaMed.enumeration.EspecialidadeMedica;
import br.com.clinicaMed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
public class MedicoService {

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
        repo.delete(id);
    }
}