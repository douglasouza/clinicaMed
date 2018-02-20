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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class SolicitacaoExame {

    @Id
    @Column(name = "se_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "se_resultado")
    private byte[] resultado;

    @JoinColumn(name = "pa_id")
    @OneToOne
    private Paciente paciente;

    @JoinColumn(name = "md_id")
    @OneToOne
    private Medico medico;

    @JoinColumn(name = "ex_id")
    @OneToOne
    private Exame exame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getResultado() {
        return resultado;
    }

    public void setResultado(byte[] resultado) {
        this.resultado = resultado;
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

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }
}