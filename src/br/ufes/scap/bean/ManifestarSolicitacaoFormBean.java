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
import javax.servlet.http.HttpSession;


import br.ufes.scap.aplicacao.LoginService;
import br.ufes.scap.aplicacao.ParecerService;
import br.ufes.scap.aplicacao.PessoaService;
import br.ufes.scap.aplicacao.SolicitacaoService;
import br.ufes.scap.dominio.Parecer;
import br.ufes.scap.dominio.Pessoa;
import br.ufes.scap.dominio.Solicitacao;
import br.ufes.scap.dominio.StatusSolicitacao;
import br.ufes.scap.dominio.TipoAfastamento;
import br.ufes.scap.dominio.TipoParecer;
import br.ufes.scap.util.SolicitacaoDataModel;


@SessionScoped
@ManagedBean(name = "ManifestarSolicitacaoFormBean")
public class ManifestarSolicitacaoFormBean{
	
	@EJB
	private SolicitacaoService solicitacaoService;
	
	@EJB
	private PessoaService pessoaService;
	
	@EJB
	private ParecerService parecerService;
	
	private Pessoa professorSelecionado;
	
	private Solicitacao solicitacao;
	
	private Solicitacao solicitacaoSelecionada;
	
	private List<Solicitacao> solicitacoes;
	
	//DataModel utilizado para preencher DataTable. Em br.ufes.scap.util
	private SolicitacaoDataModel solicitacaoDM;	

	private int codigo;
	
	public Pessoa getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(Pessoa professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

	private String motivoManifestacao;

	
	private List<Pessoa> professores;

	//getters and setters	
	
	
	public String getMotivoManifestacao() {
		return motivoManifestacao;
	}

	public void setMotivoManifestacao(String motivoManifestacao) {
		this.motivoManifestacao = motivoManifestacao;
	}

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
		 LoginService.verificalogin();
		 professorSelecionado = new Pessoa();
		 carregarProfessores();
		 this.solicitacoes = new ArrayList<Solicitacao>();
	 }
	
	public void onbtnPesquisar(ActionEvent actionEvent){
		//this.solicitacoes = new ArrayList<Solicitacao>();
		Solicitacao aux = new Solicitacao();


		try{

		//busca solicitacao selecionada do banco
		aux = solicitacaoService.buscarSolicitacaobyPK(this.codigo);

		
		
		this.solicitacoes.add(aux);
	
		this.preecherDataTableSolicitacoes(solicitacoes);
		this.solicitacaoDM = new SolicitacaoDataModel(solicitacoes);	
		
		}catch(Exception e){
			e.printStackTrace();
		}		
		

		
	}
	
	public void onbtnParecer(ActionEvent actionEvent){
		Pessoa currentUser = new Pessoa();
		try{
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		currentUser= (Pessoa) session.getAttribute("currentUser");
		
		if(this.motivoManifestacao.equalsIgnoreCase("")|| this.motivoManifestacao == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Deve ser informado um motivo para a Manifestação contra Afastamento"));
			return;
		}
		
		if(isSolicitacaoInternacional()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Não é possivel manifestar-se contra uma solicitação de afastamento internacional. O professor escolhido como relator é responsável por dar o parecer"));
			return;
		}
		
		if(!isSolicitacaoIniciadaOULiberada()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Não é possivel manifestar-se contra uma solicitação de afastamento com status " + this.solicitacaoSelecionada.getStatusSolicitacao().toString()));
			return;		
		}		
		
		
		
		Parecer parecer = new Parecer();
		parecer.setDt_parecer(new Date());
		parecer.setJulgamento(TipoParecer.DESFAVORAVEL);
		parecer.setMotivoIndeferimento(getMotivoManifestacao());
		parecer.setRelator(currentUser);
		parecer.setSolicitacao(getSolicitacaoSelecionada());
		//this.solicitacaoSelecionada.setStatusSolicitacao(StatusSolicitacao.BLOQUEADA);
		//solicitacaoService.alterarStatusSolicitacao(solicitacaoSelecionada);
		parecerService.SalvarParecer(parecer);
		
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Erro ao Salvar Parecer"));
		
			return;
		}
		this.solicitacaoSelecionada = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO -","Parecer gravado com Sucesso"));
		

		
	}

	
	//Preenche o SelectOneMenu com os professores cadastrados no SCAP
	public void carregarProfessores() {
		List<Pessoa> professores = new ArrayList<Pessoa>();
		this.setProfessores(pessoaService.buscarTodosProfessores());	

	}
	
	public boolean isSolicitacaoInternacional(){
		//caso um professor tente se manifestar contra uma solicitação de afastamento internacional
				if(this.solicitacaoSelecionada.getTipoAfastamento().equals(TipoAfastamento.INTERNACIONAL)){					
					return true;
				}else{
					return false;
				}
	}
	
	public boolean isSolicitacaoIniciadaOULiberada(){
		//caso um professor tente se manifestar contra uma solicitação de afastamento internacional
		if((this.solicitacaoSelecionada.getStatusSolicitacao().equals(StatusSolicitacao.INICIADA)) ||
		   (this.solicitacaoSelecionada.getStatusSolicitacao().equals(StatusSolicitacao.LIBERADA)))
		{
			return true;
		}else{
			return false;
			
			
		}
	}
	
	public void resetarCampos(){
		this.solicitacaoSelecionada = null;
		this.codigo= 0;
		this.professorSelecionado = null;
		this.motivoManifestacao="";
	}

}
