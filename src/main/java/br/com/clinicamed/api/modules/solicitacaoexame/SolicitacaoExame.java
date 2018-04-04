package br.com.clinicamed.api.modules.solicitacaoexame;

import br.com.clinicamed.api.modules.medico.Medico;
import br.com.clinicamed.api.modules.paciente.Paciente;
import br.com.clinicamed.api.modules.solicitacaoexame.exame.Exame;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class SolicitacaoExame {

    @Id
    @Column(name = "se_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "se_nome_arq_resultado")
    private String nomeArquivoResultado;

    @Column(name = "se_arquivo_mimetype")
    private String arquivoMimeType;

    @Column(name = "se_resultado", length = 20971520)
    private byte[] resultado;

    @JoinColumn(name = "pa_id")
    @OneToOne
    private Paciente paciente;

    @JoinColumn(name = "md_id")
    @OneToOne
    private Medico medico;

    @JoinColumn(name = "ex_id")
    @OneToOne
    private Exame exame;

    @Column(name = "se_dt_hr_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCadastro;

    @Column(name = "se_dt_hr_entregue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEntregue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivoResultado() {
        return nomeArquivoResultado;
    }

    public void setNomeArquivoResultado(String nomeArquivoResultado) {
        this.nomeArquivoResultado = nomeArquivoResultado;
    }

    public String getArquivoMimeType() {
        return arquivoMimeType;
    }

    public void setArquivoMimeType(String arquivoMimeType) {
        this.arquivoMimeType = arquivoMimeType;
    }

    public byte[] getResultado() {
        return resultado;
    }

    public void setResultado(byte[] resultado) {
        this.resultado = resultado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Date getDataHoraEntregue() {
        return dataHoraEntregue;
    }

    public void setDataHoraEntregue(Date dataHoraEntregue) {
        this.dataHoraEntregue = dataHoraEntregue;
    }
}