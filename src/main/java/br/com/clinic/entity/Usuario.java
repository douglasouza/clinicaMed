package br.com.clinic.entity;

import br.com.clinic.enumeration.ETipoUsuario;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends Pessoa {

    @Column
    private String login;

    @Column
    private String senha;

    @Column
    private ETipoUsuario tipoUsuario;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ETipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(ETipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
