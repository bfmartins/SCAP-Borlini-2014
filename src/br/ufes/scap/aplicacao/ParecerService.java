package br.ufes.scap.aplicacao;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import br.ufes.scap.dominio.Parecer;
import br.ufes.scap.persistencia.ParecerDAO;


@SessionScoped
@Stateful
public class ParecerService {

	@EJB
	private ParecerDAO parecerDAO;
	
	
	public void SalvarParecer(Parecer parecer){
		
		parecerDAO.salvar(parecer);
	
	}
}
