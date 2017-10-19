package br.com.clinicaMed.service;

import br.com.clinicaMed.entity.Medico;
import br.com.clinicaMed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoService {

    @Autowired
    private MedicoRepository repo;

    @RequestMapping(method = RequestMethod.GET)
    public List findAll() {
        return repo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Medico findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Medico insert(@RequestBody Medico usuario) throws Exception {
        usuario.setId(null);
        return repo.saveAndFlush(usuario);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Medico update(@RequestBody Medico updatedMedico, @PathVariable Long id) {
        updatedMedico.setId(id);
        return repo.saveAndFlush(updatedMedico);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        repo.delete(id);
    }
}