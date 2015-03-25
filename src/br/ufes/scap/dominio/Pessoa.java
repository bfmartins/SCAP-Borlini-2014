package br.ufes.scap.dominio;


//import javax.persistence.CascadeType;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;


//import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;



@Entity
@Table(name = "Pessoa")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPessoa", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("10")
public class Pessoa {
	

	
	@Id
	@GeneratedValue
	@Column(name="cd_pessoa")
	private int cd_pessoa;	

	public int getCd_pessoa() {
		return cd_pessoa;
	}

	public void setCd_pessoa(int cd_pessoa) {
		this.cd_pessoa = cd_pessoa;
	}

	@OneToMany(/*cascade = CascadeType.ALL,*/ mappedBy = "pessoa")
	private Set<Solicitacao> solicitacoes;	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
	private Set<Mandato> mandatos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "relator")
	private Set<Parecer> listaParecer;	
	
	//coluna setada como nullable= false, updatable=false, insertable=false para pegar o valor da DiscriminatorColumn caso necessario
	@Column(name="tipoPessoa", nullable=false, updatable=false, insertable=false)
	private int tipoPessoa;
	

	@Size(max = 144)
	@NotNull
	@Column(name="nome")
	private String nome;
	
	@Size(max = 144)
	@NotNull
	@Column(name="matricula")
	private String matricula;
	
	@Size(max = 144)
	@NotNull
	@Column(name="email")
	private String email;
	
	@Size(max = 144)
	@NotNull
	@Column(name="password")
	private String password;	
	
	
	public Set<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(Set<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public Set<Mandato> getMandatos() {
		return mandatos;
	}

	public void setMandatos(Set<Mandato> mandatos) {
		this.mandatos = mandatos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Set<Parecer> getListaParecer() {
		return listaParecer;
	}

	public void setListaParecer(Set<Parecer> listaParecer) {
		this.listaParecer = listaParecer;
	}
	
	

}
