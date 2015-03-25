package br.ufes.scap.aplicacao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;





import br.ufes.scap.dominio.Pessoa;
import br.ufes.scap.dominio.Professor;
import br.ufes.scap.dominio.Secretaria;
import br.ufes.scap.persistencia.PessoaDAO;

@SessionScoped
@Stateful
public class PessoaService {
	
	@EJB
	private PessoaDAO pessoaDAO;
	
	public void CadastrarProfessor(Professor p){
		
		pessoaDAO.salvar(p);		
		
	}
	
	public void CadastrarSecretaria(Secretaria s){
		
		pessoaDAO.salvar(s);		
		
	}
	
	public Pessoa findByPK(int cd_pessoa){
		Pessoa pessoa = new Pessoa();		
		pessoa=pessoaDAO.retrieveByPK(cd_pessoa);		
		return pessoa;
	}
	
	public List<Pessoa>buscarTodosProfessores(){
		List<Pessoa> professores = new ArrayList<Pessoa>();
		professores = pessoaDAO.findAllProfessores();
		
		return professores;
	}
	
	

}
