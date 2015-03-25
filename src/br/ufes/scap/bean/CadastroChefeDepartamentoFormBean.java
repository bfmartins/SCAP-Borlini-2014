
package br.ufes.scap.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.ufes.scap.aplicacao.LoginService;
import br.ufes.scap.aplicacao.MandatoService;
import br.ufes.scap.aplicacao.PessoaService;
import br.ufes.scap.dominio.Mandato;
import br.ufes.scap.dominio.Pessoa;


@SessionScoped
@ManagedBean(name="CadastroChefeDepartamentoFormBean")
public class CadastroChefeDepartamentoFormBean {
	
	@EJB
	private PessoaService pessoaService;
	
	@EJB
	private MandatoService mandatoService;
	
	private int codProfessorSelecionado;	

	private List<Pessoa> professores;
	
	private Mandato mandato;	
	

	public Mandato getMandato() {
		return mandato;
	}

	public void setMandato(Mandato mandato) {
		this.mandato = mandato;
	}

	public int getCodProfessorSelecionado() {
		return codProfessorSelecionado;
	}

	public void setCodProfessorSelecionado(int codProfessorSelecionado) {
		this.codProfessorSelecionado = codProfessorSelecionado;
	}

	public List<Pessoa> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Pessoa> professores) {
		this.professores = professores;
	}
	
	@PostConstruct
	 public void init() {
		this.mandato= new Mandato();
		 LoginService.verificalogin();		 
		 carregarProfessores();
		 
	 }
	

	
	public void onbtnCadastrar(ActionEvent actionEvent){		
		Mandato novoMandato = new Mandato();
		Pessoa professor = new Pessoa();
		
		try{
			
			if(isProfessorNaoSelecionado()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Deve-se Selecionar um Professor para Cadastrar Novo Chefe de Departamento"));
				return;
			}
			
			professor = this.pessoaService.findByPK(this.getCodProfessorSelecionado());
			novoMandato.setDt_iniMandato(this.mandato.getDt_iniMandato());
			novoMandato.setPessoa(professor);
			this.mandatoService.CadastrarChefeDepartamento(novoMandato);
		
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Erro ao Cadastrar Novo Chefe de Departamento"));
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO -","Chefe de Departamento " + professor.getNome() + " cadastrado com sucesso"));
		
	}
	
	public void carregarProfessores() {
		List<Pessoa> professores = new ArrayList<Pessoa>();
		this.setProfessores(pessoaService.buscarTodosProfessores());	
		
	}
	
	public void isdtIniMandatoVazia(){
		
	}
	
	public boolean isProfessorNaoSelecionado(){
		if(this.codProfessorSelecionado==0){
			return true;
		}else{
			return false;
		}
		
		
	}
	

}
