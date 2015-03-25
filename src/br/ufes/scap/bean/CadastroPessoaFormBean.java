package br.ufes.scap.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ufes.scap.aplicacao.LoginService;
import br.ufes.scap.aplicacao.PessoaService;
import br.ufes.scap.dominio.Pessoa;
import br.ufes.scap.dominio.Professor;
import br.ufes.scap.dominio.Secretaria;
import br.ufes.scap.dominio.Solicitacao;

@ManagedBean(name="CadastroPessoaFormBean")
@SessionScoped
public class CadastroPessoaFormBean {
	
	//private Pessoa pessoa = new Pessoa();
	
	//private Professor professor = new Professor();
	
	//private Secretaria secretaria new Secretaria();
	
	@EJB
	private PessoaService pessoaService;
	
	private String nome;	

	private String repetirPassword;
	
	private String password;
	
	private String matricula;
	
	private String email;	
	
	private int tpPessoa;
	
	public int getTpPessoa() {
		return tpPessoa;
	}

	public void setTpPessoa(int tpPessoa) {
		this.tpPessoa = tpPessoa;
	}

	public String getRepetirPassword() {
		return repetirPassword;
	}

	public void setRepetirPassword(String repetirPassword) {
		this.repetirPassword = repetirPassword;
	}	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	@PostConstruct
	 public void init() {		
		 LoginService.verificalogin();

	 }
	 
	 public void onbtnCadastrar(){
		try{
		if(tpPessoa==1){
			Professor p = new Professor();		
			p.setNome(this.nome);
			p.setEmail(this.email);
			p.setMatricula(this.matricula);
			p.setPassword(this.password);
			
			if(validaPassword()){
				pessoaService.CadastrarProfessor(p);						
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Password não corresponde"));
				throw new Exception("Erro ao validar Password");
			}
			
			
		}else{
				if(tpPessoa==2){					
					Secretaria s = new Secretaria();
					s.setNome(this.nome);
					s.setEmail(this.email);
					s.setMatricula(this.matricula);
					s.setPassword(this.password);
			
						if(validaPassword()){
							pessoaService.CadastrarSecretaria(s);						
						}else{
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Password não corresponde"));
							throw new Exception("Erro ao validar Password");
						}
			
				}else{
					//caso tpPessoa seja = 10 (nao selecionou função)
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Favro selecionar a função do novo usuário"));
			
				}
		} 
	
		 
		}catch (Exception e){
			e.printStackTrace();
			return;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO -","Usuário " + this.getNome() + " Cadastrado com sucesso!"));
		resetarCampos();

		}
	 
	 public boolean validaPassword(){
		 
		if(this.password.equals(this.repetirPassword)) {
			return true;
		}else{
			return false;		
		}
	 }
	 
	public void resetarCampos(){
		this.email="";
		this.matricula="";
		this.nome = "";
		this.password="";
		this.repetirPassword="";
		this.tpPessoa=10;		
		
	}


}
