package br.ufes.scap.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Parecer")
public class Parecer {
	
	@Id
	@GeneratedValue
	private int cd_parecer;	
	
	@ManyToOne
	@JoinColumn(name = "cd_pessoa")
	@NotNull
	private Pessoa relator;
	
	@ManyToOne
	@JoinColumn(name = "cd_solicitacao")
	@NotNull
	private Solicitacao solicitacao;
	
	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	@Column(name= "dt_parecer")
	@Temporal(value=TemporalType.DATE)
	private Date dt_parecer;
	
	public int getCd_parecer() {
		return cd_parecer;
	}

	public void setCd_parecer(int cd_parecer) {
		this.cd_parecer = cd_parecer;
	}

	public Pessoa getRelator() {
		return relator;
	}

	public void setRelator(Pessoa relator) {
		this.relator = relator;
	}

	public Date getDt_parecer() {
		return dt_parecer;
	}

	public void setDt_parecer(Date dt_parecer) {
		this.dt_parecer = dt_parecer;
	}

	public TipoParecer getJulgamento() {
		return julgamento;
	}

	public void setJulgamento(TipoParecer julgamento) {
		this.julgamento = julgamento;
	}

	public String getMotivoIndeferimento() {
		return motivoIndeferimento;
	}

	public void setMotivoIndeferimento(String motivoIndeferimento) {
		this.motivoIndeferimento = motivoIndeferimento;
	}

	@Enumerated(EnumType.STRING)
	private TipoParecer julgamento;
	
	@Column
	@NotNull
	private String motivoIndeferimento;
	
	
	
	

}
