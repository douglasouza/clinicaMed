package br.com.clinicamed.api.modules.solicitacaoexame;

import br.com.clinicamed.api.common.exception.SolicitacaoExameEntregueException;
import br.com.clinicamed.api.modules.solicitacaoexame.examesolicitacao.ExameSolicitacaoBO;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class SolicitacaoExameBO {

    @Autowired
    private SolicitacaoExameRepository repo;

    @Autowired
    private ExameSolicitacaoBO exameSolicitacaoBO;

    public Object pesquisarSolicitacaoExame(String filtro) {
        Iterable<SolicitacaoExameDTO> prescricoes;
        if (StringUtils.isEmpty(filtro))
            prescricoes = getExamesDTO(repo.findAll());
        else
            prescricoes = getExamesDTO(repo.findAll(getBooleanExpression(filtro)));

        return prescricoes;
    }

    private List<SolicitacaoExameDTO> getExamesDTO(Iterable<SolicitacaoExame> solicitacoesExame) {
        List<SolicitacaoExameDTO> solicitacoesExameDTOs = new ArrayList<>();
        for (SolicitacaoExame solicitacaoExame : solicitacoesExame) {
            SolicitacaoExameDTO solicitacaoExameDTO = new SolicitacaoExameDTO();
            solicitacaoExameDTO.setId(solicitacaoExame.getId());
            solicitacaoExameDTO.setNomePaciente(solicitacaoExame.getPaciente().getNome());
            solicitacaoExameDTO.setNomeMedico(solicitacaoExame.getMedico().getNome());
            solicitacaoExameDTO.setEntregue(solicitacaoExame.getEntregue().equals(Boolean.TRUE) ? "Sim" : "Não");
            solicitacoesExameDTOs.add(solicitacaoExameDTO);
        }

        return solicitacoesExameDTOs;
    }

    private BooleanExpression getBooleanExpression(String filtro) {
        return QSolicitacaoExame.solicitacaoExame.paciente.nome.containsIgnoreCase(filtro)
                .or(QSolicitacaoExame.solicitacaoExame.medico.nome.containsIgnoreCase(filtro));
    }

    @Transactional
    public SolicitacaoExame inserirSolicitacaoExame(SolicitacaoExame solicitacaoexame) {
        solicitacaoexame.setId(null);
        SolicitacaoExame solicitacaoExameSalva = repo.saveAndFlush(solicitacaoexame);
        exameSolicitacaoBO.inserirExamesSolicitacao(solicitacaoexame.getExames(), solicitacaoExameSalva);
        return solicitacaoExameSalva;
    }

    @Transactional
    public SolicitacaoExame atualizarSolicitacaoExame(SolicitacaoExame updatedSolicitacaoExame, Long id) {
        SolicitacaoExame solicitacaoExameAntesEdicao = repo.findOne(id);
        if (solicitacaoExameAntesEdicao.getEntregue())
            throw new SolicitacaoExameEntregueException();

        updatedSolicitacaoExame.setId(id);
        SolicitacaoExame solicitacaoExameAtualizada = repo.saveAndFlush(updatedSolicitacaoExame);
        exameSolicitacaoBO.atualizarExamesSolicitacao(updatedSolicitacaoExame.getExames(), solicitacaoExameAtualizada);
        return solicitacaoExameAtualizada;
    }

    @Transactional
    public void removerSolicitacaoExame(Long idSolicitacaoExame) {
        SolicitacaoExame solicitacaoexame = repo.findOne(idSolicitacaoExame);
        if (solicitacaoexame.getEntregue())
            throw new SolicitacaoExameEntregueException();

        exameSolicitacaoBO.removerExamesSolicitacao(idSolicitacaoExame);
        repo.delete(idSolicitacaoExame);
    }
}
