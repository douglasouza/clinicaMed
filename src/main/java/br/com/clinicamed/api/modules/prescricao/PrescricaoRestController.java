package br.com.clinicamed.api.modules.prescricao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prescricao")
public class PrescricaoRestController {

    @Autowired
    private PrescricaoRepository repo;

    @Autowired
    private PrescricaoBO bo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(String filtro) {
        return bo.pesquisarPrescricao(filtro);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Prescricao findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Prescricao insert(@RequestBody Prescricao prescricao) throws Exception {
        return bo.inserirPrescricao(prescricao);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Prescricao update(@RequestBody Prescricao updatedPrescricao, @PathVariable Long id) {
        return bo.atualizarPrescricao(updatedPrescricao, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        bo.removerPrescricao(id);
    }
}