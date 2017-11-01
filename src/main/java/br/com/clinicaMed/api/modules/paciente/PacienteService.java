package br.com.clinicaMed.api.modules.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteService {

    @Autowired
    private PacienteRepository repo;

    @Autowired
    private PacienteBO bo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(String nome) {
        return bo.pesquisarPaciente(nome);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Paciente findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Paciente insert(@RequestBody Paciente paciente) throws Exception {
        return bo.inserirPaciente(paciente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Paciente update(@RequestBody Paciente updatedPaciente, @PathVariable Long id) {
        return bo.atualizarPaciente(updatedPaciente, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        repo.delete(id);
    }
}