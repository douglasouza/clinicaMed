package br.com.clinicamed.api.modules.prescricao;

import br.com.clinicamed.api.modules.medicamento.Medicamento;
import br.com.clinicamed.api.modules.paciente.Paciente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Prescricao {

    @Id
    @Column(name = "pr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pr_entregue")
    private Boolean entregue;

    @JoinColumn(name = "pa_id")
    @ManyToOne
    private Paciente paciente;

    @JoinColumn(name = "me_id")
    @ManyToOne
    private Medicamento medicamento;

    @Column(name = "pr_dt_hr_entregue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEntregue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEntregue() {
        return entregue;
    }

    public void setEntregue(Boolean entregue) {
        this.entregue = entregue;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Date getDataHoraEntregue() {
        return dataHoraEntregue;
    }

    public void setDataHoraEntregue(Date dataHoraEntregue) {
        this.dataHoraEntregue = dataHoraEntregue;
    }
}
