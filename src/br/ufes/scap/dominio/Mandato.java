package br.ufes.scap.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "Mandato")
public class Mandato{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="cd_mandato")
	private int cd_mandato;	

	@ManyToOne
	@JoinColumn(name="cd_pessoa")
	private Pessoa pessoa;
	
	@NotNull
	@Column
	@Temporal(value=TemporalType.DATE)
	private Date dt_iniMandato;
	
	@Column
	@Temporal(value=TemporalType.DATE)
	private Date dt_fimMandato;	

	

	public int getCd_mandato() {
		return cd_mandato;
	}

	public void setCd_mandato(int cd_mandato) {
		this.cd_mandato = cd_mandato;
	}
	

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDt_iniMandato() {
		return dt_iniMandato;
	}

	public void setDt_iniMandato(Date dt_iniMandato) {
		this.dt_iniMandato = dt_iniMandato;
	}

	public Date getDt_fimMandato() {
		return dt_fimMandato;
	}

	public void setDt_fimMandato(Date dt_fimMandato) {
		this.dt_fimMandato = dt_fimMandato;
	}
	
	

}
