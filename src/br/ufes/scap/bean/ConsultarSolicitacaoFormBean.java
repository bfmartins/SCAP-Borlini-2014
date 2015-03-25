package br.ufes.scap.bean;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.ufes.scap.aplicacao.PessoaService;
import br.ufes.scap.aplicacao.SolicitacaoService;
import br.ufes.scap.dominio.Pessoa;
import br.ufes.scap.dominio.Solicitacao;
import br.ufes.scap.util.SolicitacaoDataModel;


@SessionScoped
@ManagedBean(name = "ConsultarSolicitacaoFormBean")
public class ConsultarSolicitacaoFormBean{
	
	@EJB
	private SolicitacaoService solicitacaoService;
	
	@EJB
	private PessoaService pessoaService;
	
	private Pessoa professor;
	
	private Solicitacao solicitacao;
	
	private Solicitacao solicitacaoSelecionada;
	
	private List<Solicitacao> solicitacoes;
	
	private SolicitacaoDataModel solicitacaoDM;	

	private int codigo;

	public Pessoa getProfessor() {
		return professor;
	}

	public void setProfessor(Pessoa professor) {
		this.professor = professor;
	}
	
	private List<Pessoa> professores;	

	public List<Pessoa> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Pessoa> professores) {
		this.professores = professores;
	}	

	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}	

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	
	public SolicitacaoDataModel getSolicitacaoDM() {
		return solicitacaoDM;
	}

	public void setSolicitacaoDM(SolicitacaoDataModel solicitacaoDM) {
		this.solicitacaoDM = solicitacaoDM;
	}
	
	public List<Solicitacao> preecherDataTableSolicitacoes(List<Solicitacao> solicitacoes){
		return solicitacoes;		
	}	
	
	@PostConstruct
	 public void init() {	
		 professor = new Pessoa();
		 carregarProfessores();
		 this.solicitacoes = new ArrayList<Solicitacao>();
	 }
	
	public void onbtnPesquisar(ActionEvent actionEvent){
		//this.solicitacoes = new ArrayList<Solicitacao>();
		Solicitacao aux = new Solicitacao();
		Solicitacao aux2 = new Solicitacao();


		try{

		//busca solicitacao do banco
		aux = solicitacaoService.buscarSolicitacaobyPK(this.codigo);
		aux2 = solicitacaoService.buscarSolicitacaobyPK(2);
		
		this.solicitacoes.add(aux);
		this.solicitacoes.add(aux2);
		this.solicitacoes.add(aux);		
		this.preecherDataTableSolicitacoes(solicitacoes);
		this.solicitacaoDM = new SolicitacaoDataModel(solicitacoes);
		
	
		
		}catch(Exception e){
			e.printStackTrace();
		}		
		

		
	}

	/*public long calculaQuantDiasAfast(Date dt_iniAfast, Date dt_fimAfast){
		
		long quantidadeDiasAfast = (dt_fimAfast.getTime() - dt_iniAfast.getTime())/86400000;		
		System.out.println("Dias de Afastamento 2 " + quantidadeDiasAfast);
		return quantidadeDiasAfast;	
		
	}*/
	
	public void carregarProfessores() {
		List<Pessoa> professores = new ArrayList<Pessoa>();
		this.setProfessores(pessoaService.buscarTodosProfessores());	
		//professores = pessoaService.buscarTodosProfessores();
	}	
	

}