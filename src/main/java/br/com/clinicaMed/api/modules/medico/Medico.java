package br.com.clinicaMed.api.modules.medico;

import br.com.clinicaMed.api.common.enumeration.EspecialidadeMedica;
import br.com.clinicaMed.security.usuario.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Medico {

    @Id
    @Column(name = "md_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "md_nome")
    private String nome;

    @Column(name = "md_crm")
    private String crm;

    @Column(name = "md_especialidade")
    @Enumerated(EnumType.STRING)
    private EspecialidadeMedica especialidade;

    @JoinColumn(name = "us_id")
    @OneToOne
    private Usuario usuario;

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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public EspecialidadeMedica getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeMedica especialidade) {
        this.especialidade = especialidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
