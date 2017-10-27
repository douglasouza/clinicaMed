package br.com.clinicaMed.security.dto;

public class ErroHttp {

    private String descricao;

    private String mensagem;

    public ErroHttp(String descricao, String mensagem) {
        this.descricao = descricao;
        this.mensagem = mensagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}