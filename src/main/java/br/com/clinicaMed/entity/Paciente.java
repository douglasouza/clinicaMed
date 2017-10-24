package br.com.clinicaMed.entity;

import br.com.clinicaMed.enumeration.Sexo;

import javax.persistence.*;

@Entity
public class Paciente {

    @Id
    @Column(name = "pa_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pa_nome")
    private String nome;

    @Column(name = "pa_cpf")
    private String cpf;

    @Column(name = "pa_sexo")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
