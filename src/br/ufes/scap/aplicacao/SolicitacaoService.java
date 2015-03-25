package br.ufes.scap.aplicacao;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;

import br.ufes.scap.dominio.Pessoa;
import br.ufes.scap.dominio.Solicitacao;
import br.ufes.scap.persistencia.SolicitacaoDAO;

@SessionScoped
@Stateful
public class SolicitacaoService {
	
	@EJB
	private SolicitacaoDAO solicitacaoDAO;
	
	
	public void CadastrarSolicitacao(Solicitacao solicitacaoAfast){
		
		solicitacaoDAO.salvar(solicitacaoAfast);
		
	}
	
	public Solicitacao buscarSolicitacaobyPK(int pk){
		Solicitacao solicitacao = new Solicitacao();				
		solicitacao=solicitacaoDAO.retrieveByPK(pk);
		
		return solicitacao;
		
	}
	
	public Solicitacao alterarStatusSolicitacao(Solicitacao solicitacaoAfast){
		Solicitacao solicitacao = new Solicitacao();	
		
		//solicitacao=solicitacaoDAO.merge(solicitacaoAfast);
		
		return solicitacao;
		
		
	}
	
	

}
