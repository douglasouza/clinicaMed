package br.com.clinicaMed.service;

import br.com.clinicaMed.business.PacienteBO;
import br.com.clinicaMed.entity.Paciente;
import br.com.clinicaMed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteService {

    @Autowired
    private PacienteRepository repo;

    @Autowired
    private PacienteBO bo;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Object findAll() {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Paciente findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Paciente insert(@RequestBody Paciente recepcionista) throws Exception {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Paciente update(@RequestBody Paciente updatedPaciente, @PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        repo.delete(id);
    }
}