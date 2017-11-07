package br.com.clinicamed.api.modules.medicamento;

import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MedicamentoBO {

    @Autowired
    private MedicamentoRepository repo;

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
        medicamento.setId(null);
        return repo.saveAndFlush(medicamento);
    }

    public Medicamento atualizarMedicamento(Medicamento updatedMedicamento, Long id) {
        updatedMedicamento.setId(id);
        return repo.saveAndFlush(updatedMedicamento);
    }
}
