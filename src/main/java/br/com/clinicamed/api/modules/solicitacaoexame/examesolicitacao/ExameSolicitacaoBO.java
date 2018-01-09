package br.com.clinicamed.api.modules.solicitacaoexame.examesolicitacao;

import br.com.clinicamed.api.modules.solicitacaoexame.SolicitacaoExame;
import br.com.clinicamed.api.modules.solicitacaoexame.exame.Exame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExameSolicitacaoBO {

    @Autowired
    private ExameSolicitacaoRepository repo;

    public void inserirExamesSolicitacao(List<Exame> exames, SolicitacaoExame solicitacaoExame) {
        for (Exame exame : exames) {
            salvarExamesSolicitacao(exame, solicitacaoExame);
        }
    }

    private void salvarExamesSolicitacao(Exame exame, SolicitacaoExame solicitacaoExame) {
        ExameSolicitacao exameSolicitacao = new ExameSolicitacao();
        exameSolicitacao.setExame(exame);
        exameSolicitacao.setSolicitacaoExame(solicitacaoExame);
        repo.save(exameSolicitacao);
    }

    public void atualizarExamesSolicitacao(List<Exame> exames, SolicitacaoExame solicitacaoExame) {
        removerExamesSolicitacao(solicitacaoExame.getId());
        for (Exame exame : exames) {
            salvarExamesSolicitacao(exame, solicitacaoExame);
        }
    }

    public void removerExamesSolicitacao(Long idSolicitacaoExame) {
        repo.removerExames(idSolicitacaoExame);
    }
}
