package br.com.clinicamed.api.modules.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/consulta")
public class ConsultaRestController {

    @Autowired
    private ConsultaRepository repo;

    @Autowired
    private ConsultaBO bo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(String nomeMedicoPaciente, Date dataInicial, Date dataFinal) {
        return bo.pesquisarConsulta(nomeMedicoPaciente, dataInicial, dataFinal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Consulta findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Consulta insert(@RequestBody ConsultaDTO consultaDTO) throws Exception {
        return bo.inserirConsulta(consultaDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Consulta update(@RequestBody Consulta updatedConsulta, @PathVariable Long id) {
        return bo.atualizarConsulta(updatedConsulta, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        bo.removerConsulta(id);
    }
}