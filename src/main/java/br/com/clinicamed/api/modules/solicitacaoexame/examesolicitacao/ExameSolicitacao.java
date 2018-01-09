package br.com.clinicamed.api.modules.solicitacaoexame.examesolicitacao;

import br.com.clinicamed.api.modules.solicitacaoexame.SolicitacaoExame;
import br.com.clinicamed.api.modules.solicitacaoexame.exame.Exame;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ExameSolicitacao {

    @Id
    @Column(name = "es_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ex_id")
    private Exame exame;

    @ManyToOne
    @JoinColumn(name = "se_id")
    private SolicitacaoExame solicitacaoExame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public SolicitacaoExame getSolicitacaoExame() {
        return solicitacaoExame;
    }

    public void setSolicitacaoExame(SolicitacaoExame solicitacaoExame) {
        this.solicitacaoExame = solicitacaoExame;
    }
}
