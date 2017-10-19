package br.com.clinicaMed.entity;

import br.com.clinicaMed.enumeration.EspecialidadeMedica;

import javax.persistence.*;

@Entity
public class Medico extends Usuario {

    @Column
    private String nome;

    @Column
    private String crm;

    @Column
    @Enumerated(EnumType.STRING)
    private EspecialidadeMedica especialidade;

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
}
