package br.com.clinicaMed.service;

import br.com.clinicaMed.business.RecepcionistaBO;
import br.com.clinicaMed.entity.Recepcionista;
import br.com.clinicaMed.repository.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recepcionista")
public class RecepcionistaService {

    @Autowired
    private RecepcionistaRepository repo;

    @Autowired
    private RecepcionistaBO bo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(String nomeLogin) {
        return bo.pesquisarRecepcionista(nomeLogin);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Recepcionista findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Recepcionista insert(@RequestBody Recepcionista recepcionista) throws Exception {
        return bo.inserirRecepcionista(recepcionista);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Recepcionista update(@RequestBody Recepcionista updatedRecepcionista, @PathVariable Long id) {
        return bo.atualizarRecepcionista(updatedRecepcionista, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        repo.delete(id);
    }
}