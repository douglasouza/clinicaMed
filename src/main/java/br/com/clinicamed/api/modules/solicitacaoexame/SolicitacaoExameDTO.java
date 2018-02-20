package br.com.clinicamed.api.modules.solicitacaoexame;

public class SolicitacaoExameDTO {

    private Long id;

    private String nomePaciente;

    private String nomeMedico;

    private String resultadoEntregue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getResultadoEntregue() {
        return resultadoEntregue;
    }

    public void setResultadoEntregue(String resultadoEntregue) {
        this.resultadoEntregue = resultadoEntregue;
    }
}
