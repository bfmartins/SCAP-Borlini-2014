package br.ufes.scap.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Documento {
	
	@Id
	@GeneratedValue
	@Column(name="cd_documento")
	private int cd_documeto;
	
	@ManyToOne
	@JoinColumn(name="cd_solicitacao")
	private Solicitacao solicitacao;
	
	@Column(name="dt_juntada")
	@NotNull
	private Date dt_juntada;
	
	@Column(name="nm_arquivo")
	@NotNull
	private String nm_arquivo;
	
	@Column(name="path")
	@NotNull
	private String path;
	

}
