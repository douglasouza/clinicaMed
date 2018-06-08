package br.com.clinicamed.api.modules.prescricao;

import br.com.clinicamed.api.common.exception.PrescricaoEntregueException;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PrescricaoBO {

    @Autowired
    private PrescricaoRepository repo;

    public Object pesquisarPrescricao(String filtro) {
        Iterable<PrescricaoDTO> prescricoes;
        if (StringUtils.isEmpty(filtro))
            prescricoes = getPrescricaosDTO(repo.findAll());
        else
            prescricoes = getPrescricaosDTO(repo.findAll(getBooleanExpression(filtro)));

        return prescricoes;
    }

    private List<PrescricaoDTO> getPrescricaosDTO(Iterable<Prescricao> prescricoes) {
        List<PrescricaoDTO> prescricoesDTOS = new ArrayList<>();
        for (Prescricao prescricao : prescricoes) {
            PrescricaoDTO prescricaoDTO = new PrescricaoDTO();
            prescricaoDTO.setId(prescricao.getId());
            prescricaoDTO.setNomePaciente(prescricao.getPaciente().getNome());
            prescricaoDTO.setNomeGenericoMedicamento(prescricao.getMedicamento().getNomeGenerico());
            prescricaoDTO.setEntregue(prescricao.getEntregue().equals(Boolean.TRUE) ? "Sim" : "Não");
            prescricoesDTOS.add(prescricaoDTO);
        }

        return prescricoesDTOS;
    }

    private BooleanExpression getBooleanExpression(String filtro) {
        return QPrescricao.prescricao.paciente.nome.containsIgnoreCase(filtro)
                .or(QPrescricao.prescricao.medicamento.nomeGenerico.containsIgnoreCase(filtro));
    }

    public Prescricao inserirPrescricao(Prescricao prescricao) {
        prescricao.setId(null);

        if (prescricao.getEntregue())
            prescricao.setDataHoraEntregue(new Date());

        return repo.saveAndFlush(prescricao);
    }

    public Prescricao atualizarPrescricao(Prescricao updatedPrescricao, Long id) {
        Prescricao prescricaoAntesEdicao = repo.findOne(id);
        if (prescricaoAntesEdicao.getEntregue())
            throw new PrescricaoEntregueException();

        if (updatedPrescricao.getEntregue())
            updatedPrescricao.setDataHoraEntregue(new Date());

        updatedPrescricao.setId(id);
        return repo.saveAndFlush(updatedPrescricao);
    }

    public void removerPrescricao(Long idPrescricao) {
        Prescricao prescricao = repo.findOne(idPrescricao);
        if (prescricao.getEntregue())
            throw new PrescricaoEntregueException();

        repo.delete(idPrescricao);
    }
}
