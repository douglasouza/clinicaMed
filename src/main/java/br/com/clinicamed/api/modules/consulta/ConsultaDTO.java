package br.com.clinicamed.api.modules.consulta;

import java.util.Date;

public class ConsultaDTO {

    private Long id;

    private Long idMedico;

    private String nomeMedico;

    private Long idPaciente;

    private String nomePaciente;

    private Date dataConsulta;

    private Long idHorarioConsulta;

    private String horarioConsulta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Long getIdHorarioConsulta() {
        return idHorarioConsulta;
    }

    public void setIdHorarioConsulta(Long idHorarioConsulta) {
        this.idHorarioConsulta = idHorarioConsulta;
    }

    public String getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(String horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }
}
