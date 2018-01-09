package br.com.clinicamed.api.modules.solicitacaoexame;

import br.com.clinicamed.api.modules.medico.Medico;
import br.com.clinicamed.api.modules.paciente.Paciente;
import br.com.clinicamed.api.modules.solicitacaoexame.exame.Exame;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class SolicitacaoExame {

    @Id
    @Column(name = "se_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pr_entregue")
    private Boolean entregue;

    @JoinColumn(name = "pa_id")
    @OneToOne
    private Paciente paciente;

    @JoinColumn(name = "md_id")
    @OneToOne
    private Medico medico;

    @Transient
    private transient List<Exame> exames;

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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }
}