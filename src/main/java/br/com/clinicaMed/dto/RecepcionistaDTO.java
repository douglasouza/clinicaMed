package br.com.clinicaMed.dto;

public class RecepcionistaDTO {

    private Long id;

    private String nome;

    private String login;

    public RecepcionistaDTO(Long id, String nome, String login) {
        this.id = id;
        this.nome = nome;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
