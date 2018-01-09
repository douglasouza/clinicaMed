package br.com.clinicamed.api.modules.solicitacaoexame.exame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exame")
public class ExameRestController {

    @Autowired
    private ExameRepository repo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(String filtro) {
        return repo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Exame findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }
}