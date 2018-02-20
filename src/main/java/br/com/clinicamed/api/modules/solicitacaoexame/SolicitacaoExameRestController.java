package br.com.clinicamed.api.modules.solicitacaoexame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitacaoExame")
public class SolicitacaoExameRestController {

    @Autowired
    private SolicitacaoExameRepository repo;

    @Autowired
    private SolicitacaoExameBO bo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(String filtro) {
        return bo.pesquisarSolicitacaoExame(filtro);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SolicitacaoExame findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SolicitacaoExame insert(@RequestBody SolicitacaoExame solicitacaoExame) throws Exception {
        return bo.inserirSolicitacaoExame(solicitacaoExame);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public SolicitacaoExame update(@RequestBody SolicitacaoExame updatedSolicitacaoExame, @PathVariable Long id) {
        return bo.atualizarSolicitacaoExame(updatedSolicitacaoExame, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        bo.removerSolicitacaoExame(id);
    }

    @RequestMapping(value = "/{id}/uploadResultadoExame", method = RequestMethod.POST)
    public SolicitacaoExame uploadResultadoExame(@RequestBody byte[] resultadoExame, @PathVariable Long id) {
        return bo.atualizarResultadoExame(resultadoExame, id);
    }
}