package br.com.clinicamed.api.modules.prescricao;

public class PrescricaoDTO {

    private Long id;

    private String nomePaciente;

    private String nomeGenericoMedicamento;

    private String entregue;

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

    public String getNomeGenericoMedicamento() {
        return nomeGenericoMedicamento;
    }

    public void setNomeGenericoMedicamento(String nomeGenericoMedicamento) {
        this.nomeGenericoMedicamento = nomeGenericoMedicamento;
    }

    public String getEntregue() {
        return entregue;
    }

    public void setEntregue(String entregue) {
        this.entregue = entregue;
    }
}
