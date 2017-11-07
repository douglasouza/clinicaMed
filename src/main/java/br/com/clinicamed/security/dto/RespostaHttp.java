package br.com.clinicamed.security.dto;

public class RespostaHttp {

    private int codigo;

    private String mensagem;

    private ErroHttp erro;

    public RespostaHttp(int codigo, String mensagem, ErroHttp erro) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.erro = erro;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ErroHttp getErro() {
        return erro;
    }

    public void setErro(ErroHttp erro) {
        this.erro = erro;
    }
}