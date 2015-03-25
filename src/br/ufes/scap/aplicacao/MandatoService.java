package br.ufes.scap.aplicacao;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import br.ufes.scap.dominio.Mandato;
import br.ufes.scap.persistencia.MandatoDAO;

@SessionScoped
@Stateful
public class MandatoService {
	
	@EJB
	private MandatoDAO mandatoDAO;
	
	public void CadastrarChefeDepartamento(Mandato mandato){
		
		mandatoDAO.salvar(mandato);		
		
	}
	
	public void contaQuantChefesDepartamento(){
		mandatoDAO.contarQuantidadeChefesDepartamento();
	}

}
