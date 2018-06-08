package br.com.clinicamed.api.modules.medicamento;

import br.com.clinicamed.api.common.exception.MedicamentoPossuiPrescricaoCadastrada;
import br.com.clinicamed.api.common.exception.NomeFabricaNaoUnicoException;
import br.com.clinicamed.api.modules.prescricao.Prescricao;
import br.com.clinicamed.api.modules.prescricao.PrescricaoRepository;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class MedicamentoBO {

    @Autowired
    private MedicamentoRepository repo;

    @Autowired
    private PrescricaoRepository prescricaoRepo;

    public Object pesquisarMedicamento(String filtro) {
        if (StringUtils.isEmpty(filtro))
            return repo.findAll();
        else
            return repo.findAll(getBooleanExpression(filtro));
    }

    private BooleanExpression getBooleanExpression(String filtro) {
        return QMedicamento.medicamento.nomeGenerico.containsIgnoreCase(filtro)
                .or(QMedicamento.medicamento.nomeFabrica.containsIgnoreCase(filtro))
                .or(QMedicamento.medicamento.fabricante.containsIgnoreCase(filtro));
    }

    public Medicamento inserirMedicamento(Medicamento medicamento) {
        if (repo.buscarMedicamentoPorNomeFabrica(medicamento.getNomeFabrica()) != null)
            throw new NomeFabricaNaoUnicoException();

        medicamento.setId(null);
        return repo.saveAndFlush(medicamento);
    }

    public Medicamento atualizarMedicamento(Medicamento updatedMedicamento, Long id) {
        if (repo.buscarMedicamentoPorNomeFabrica(updatedMedicamento.getNomeFabrica()) != null)
            throw new NomeFabricaNaoUnicoException();

        updatedMedicamento.setId(id);
        return repo.saveAndFlush(updatedMedicamento);
    }

    public void removerMedicamento(Long idMedicamento) {
        List<Prescricao> prescricoesMedicamento = prescricaoRepo.buscarPrescricoesPorMedicamento(idMedicamento);
        if (!CollectionUtils.isEmpty(prescricoesMedicamento))
            throw new MedicamentoPossuiPrescricaoCadastrada();

        repo.delete(idMedicamento);
    }
}
