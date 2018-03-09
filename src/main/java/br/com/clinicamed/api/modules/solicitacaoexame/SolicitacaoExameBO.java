package br.com.clinicamed.api.modules.solicitacaoexame;

import br.com.clinicamed.api.common.exception.SolicitacaoExamePossuiResultadoException;
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

    public Object pesquisarSolicitacaoExame(String filtro) {
        Iterable<SolicitacaoExameDTO> prescricoes;
        if (StringUtils.isEmpty(filtro))
            prescricoes = getSolicitacaoExameDTO(repo.findAll());
        else
            prescricoes = getSolicitacaoExameDTO(repo.findAll(getBooleanExpression(filtro)));

        return prescricoes;
    }

    private List<SolicitacaoExameDTO> getSolicitacaoExameDTO(Iterable<SolicitacaoExame> solicitacoesExame) {
        List<SolicitacaoExameDTO> solicitacoesExameDTOs = new ArrayList<>();
        for (SolicitacaoExame solicitacaoExame : solicitacoesExame) {
            SolicitacaoExameDTO solicitacaoExameDTO = new SolicitacaoExameDTO();
            solicitacaoExameDTO.setId(solicitacaoExame.getId());
            solicitacaoExameDTO.setNomePaciente(solicitacaoExame.getPaciente().getNome());
            solicitacaoExameDTO.setNomeMedico(solicitacaoExame.getMedico().getNome());
            solicitacaoExameDTO.setResultadoEntregue(solicitacaoExame.getResultado() != null ? "Sim" : "Não");
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
        return solicitacaoExameSalva;
    }

    @Transactional
    public SolicitacaoExame atualizarSolicitacaoExame(SolicitacaoExame updatedSolicitacaoExame, Long id) {
        SolicitacaoExame solicitacaoExameAntesEdicao = repo.findOne(id);
        if (solicitacaoExameAntesEdicao.getResultado() != null)
            throw new SolicitacaoExamePossuiResultadoException();

        updatedSolicitacaoExame.setId(id);
        SolicitacaoExame solicitacaoExameAtualizada = repo.saveAndFlush(updatedSolicitacaoExame);
        return solicitacaoExameAtualizada;
    }

    @Transactional
    public SolicitacaoExame atualizarResultadoExame(byte[] resultadoExame, String nomeArquivo, String mimeType, Long id) {
        SolicitacaoExame solicitacaoExameAntesEdicao = repo.findOne(id);
        solicitacaoExameAntesEdicao.setNomeArquivoResultado(nomeArquivo);
        solicitacaoExameAntesEdicao.setArquivoMimeType(mimeType);
        solicitacaoExameAntesEdicao.setResultado(resultadoExame);
        SolicitacaoExame solicitacaoExameAtualizada = repo.saveAndFlush(solicitacaoExameAntesEdicao);
        return solicitacaoExameAtualizada;
    }

    @Transactional
    public SolicitacaoExame removerResultadoExame(Long id) {
        SolicitacaoExame solicitacaoExameAntesEdicao = repo.findOne(id);
        solicitacaoExameAntesEdicao.setNomeArquivoResultado(null);
        solicitacaoExameAntesEdicao.setArquivoMimeType(null);
        solicitacaoExameAntesEdicao.setResultado(null);
        SolicitacaoExame solicitacaoExameAtualizada = repo.saveAndFlush(solicitacaoExameAntesEdicao);
        return solicitacaoExameAtualizada;
    }

    @Transactional
    public void removerSolicitacaoExame(Long idSolicitacaoExame) {
        SolicitacaoExame solicitacaoexame = repo.findOne(idSolicitacaoExame);
        if (solicitacaoexame.getResultado() != null)
            throw new SolicitacaoExamePossuiResultadoException();
        repo.delete(idSolicitacaoExame);
    }
}
