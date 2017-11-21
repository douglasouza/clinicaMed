package br.com.clinicamed.api.modules.consulta;

import br.com.clinicamed.api.modules.consulta.horarioconsulta.Horario;
import br.com.clinicamed.api.modules.medico.Medico;
import br.com.clinicamed.api.modules.paciente.Paciente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Consulta {

    @Id
    @Column(name = "co_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "co_data_consulta")
    private Date dataConsulta;

    @JoinColumn(name = "ho_id")
    @OneToOne
    private Horario horarioConsulta;

    @JoinColumn(name = "md_id")
    @OneToOne
    private Medico medico;

    @JoinColumn(name = "pa_id")
    @OneToOne
    private Paciente paciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Horario getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(Horario horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
