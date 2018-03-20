package br.com.clinicamed.api.modules.medicamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoRestController {

    @Autowired
    private MedicamentoRepository repo;

    @Autowired
    private MedicamentoBO bo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(String filtro) {
        return bo.pesquisarMedicamento(filtro);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Medicamento findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Medicamento insert(@RequestBody Medicamento medicamento) throws Exception {
        return bo.inserirMedicamento(medicamento);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Medicamento update(@RequestBody Medicamento updatedMedicamento, @PathVariable Long id) {
        return bo.atualizarMedicamento(updatedMedicamento, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        bo.removerMedicamento(id);
    }
}