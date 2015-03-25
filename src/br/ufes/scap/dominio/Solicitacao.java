package br.ufes.scap.dominio;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
@Table(name = "Solicitacao")
public class Solicitacao /*extends PersistentObjectSupport*/ {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="cd_solicitacao")
	private int cd_solicitacao;	

	public int getCd_solicitacao() {
		return cd_solicitacao;
	}

	public void setCd_solicitacao(int cd_solicitacao) {
		this.cd_solicitacao = cd_solicitacao;
	}

	@ManyToOne
	@JoinColumn(name="cd_pessoa")
	private Pessoa pessoa;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitacao")
	private Set<Parecer> pareceres;

	public Set<Parecer> getPareceres() {
		return pareceres;
	}

	public void setPareceres(Set<Parecer> pareceres) {
		this.pareceres = pareceres;
	}

	@Enumerated(EnumType.STRING)
	@NotNull
	private StatusSolicitacao statusSolicitacao;
	
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoAfastamento tipoAfastamento;	

	@Enumerated(EnumType.STRING)
	private Onus onus;

	@Column(name="dt_iniAfast")
	@NotNull
	@Temporal(value=TemporalType.DATE)
	private Date dt_iniAfast;
	
	@Column(name="dt_fimAfast") 
	@NotNull
	@Temporal(value=TemporalType.DATE)
	private Date dt_fimAfast;
	
	@Column(name="dt_inievento")
	@NotNull
	@Temporal(value=TemporalType.DATE)
	private Date dt_inievento;
	
	@Column(name="dt_fimevento")
	@NotNull
	@Temporal(value=TemporalType.DATE)
	private Date dt_fimevento;
	
	@Column(name="dt_julgamentoAfast")
	@Temporal(value=TemporalType.DATE)
	private Date dt_julgamentoAfast;	

	@Size(max = 144)
	@Column(name="nm_evento")
	@NotNull
	private String nm_evento;
	
	@Size(max = 144)
	@Column(name="nm_cidade")
	@NotNull
	private String nm_cidade;
	
	@Size(max = 255)
	@Column(name="ds_mtvafast")
	//@NotNull
	private String ds_mtvafast;
	
	@Size(max = 255)
	@Column(name="ds_mtvcancel")
	//@NotNull
	private String ds_mtvcancel;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitacao")
	private Set<Documento> documentos;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	public StatusSolicitacao getStatusSolicitacao() {
		return statusSolicitacao;
	}

	public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
		this.statusSolicitacao = statusSolicitacao;
	}

	public TipoAfastamento getTipoAfastamento() {
		return tipoAfastamento;
	}
	
	public void setTipoAfastamento(TipoAfastamento tipoAfastamento) {
		this.tipoAfastamento = tipoAfastamento;
	}

	public Onus getOnus() {
		return onus;
	}

	public void setOnus(Onus onus) {
		this.onus = onus;
	}		

	public Date getDt_iniAfast() {
		return dt_iniAfast;
	}

	public void setDt_iniAfast(Date dt_iniAfast) {
		this.dt_iniAfast = dt_iniAfast;
	}

	public Date getDt_fimAfast() {
		return dt_fimAfast;
	}

	public void setDt_fimAfast(Date dt_fimAfast) {
		this.dt_fimAfast = dt_fimAfast;
	}

	public Date getDt_inievento() {
		return dt_inievento;
	}

	public void setDt_inievento(Date dt_inievento) {
		this.dt_inievento = dt_inievento;
	}

	public Date getDt_fimevento() {
		return dt_fimevento;
	}

	public void setDt_fimevento(Date dt_fimevento) {
		this.dt_fimevento = dt_fimevento;
	}
	
	public Date getDt_julgamentoAfast() {
		return dt_julgamentoAfast;
	}

	public void setDt_julgamentoAfast(Date dt_julgamentoAfast) {
		this.dt_julgamentoAfast = dt_julgamentoAfast;
	}

	public String getnm_evento() {
		return nm_evento;
	}

	public void setnm_evento(String nm_evento) {
		this.nm_evento = nm_evento;
	}

	public String getNm_cidade() {
		return nm_cidade;
	}

	public void setNm_cidade(String nm_cidade) {
		this.nm_cidade = nm_cidade;
	}

	public String getDs_mtvafast() {
		return ds_mtvafast;
	}

	public void setDs_mtvafast(String ds_mtvafast) {
		this.ds_mtvafast = ds_mtvafast;
	}

	public String getDs_mtvcancel() {
		return ds_mtvcancel;
	}

	public void setDs_mtvcancel(String ds_mtvcancel) {
		this.ds_mtvcancel = ds_mtvcancel;
	}	
	
	public Set<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}
	
	
}
