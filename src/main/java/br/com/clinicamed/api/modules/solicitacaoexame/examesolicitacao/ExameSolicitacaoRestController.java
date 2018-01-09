package br.com.clinicamed.api.modules.solicitacaoexame.examesolicitacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitacaoExame/{idSolicitacaoExame}/exames")
public class ExameSolicitacaoRestController {

    @Autowired
    private ExameSolicitacaoRepository repo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@PathVariable("idSolicitacaoExame") Long idSolicitacaoExame) {
        return repo.getExames(idSolicitacaoExame);
    }
}