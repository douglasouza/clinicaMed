package br.com.clinicamed.security.usuario;

import br.com.clinicamed.api.common.enumeration.TipoUsuario;

public class UsuarioDTO {

    private String login;

    private TipoUsuario tipoUsuario;

    private Boolean ativado;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }
}
