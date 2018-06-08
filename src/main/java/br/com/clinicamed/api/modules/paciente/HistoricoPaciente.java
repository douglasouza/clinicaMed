package br.com.clinicamed.api.modules.paciente;

import br.com.clinicamed.api.common.enumeration.TipoHistoricoPaciente;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Immutable
public class HistoricoPaciente {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "dt_hr")
    private String dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoHistoricoPaciente tipo;

    @Column(name = "desc")
    private String descricao;

    @JoinColumn(name = "pa_id")
    @ManyToOne
    private Paciente paciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipo() {
        return tipo.getTipoHistorico();
    }

    public void setTipo(TipoHistoricoPaciente tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}

