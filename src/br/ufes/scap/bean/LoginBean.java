package br.ufes.scap.bean;

//import java.sql.PreparedStatement;
//import java.util.ArrayList;
//import java.util.List;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;






import javax.ejb.EJB;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import org.hibernate.transform.Transformers;
import br.ufes.scap.persistencia.PessoaDAO;
import br.ufes.scap.persistencia.PessoaDAOImpl;
import br.ufes.scap.aplicacao.LoginService;
import br.ufes.scap.dominio.Pessoa;

@ManagedBean(name="LoginBean")
//@RequestScoped
public class LoginBean {	

	
	private Pessoa pessoa = new Pessoa();	
	
	@EJB
	private LoginService loginservice;
	
	 public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void onbtnEntrar(ActionEvent actionEvent){
		boolean login;

		login=loginservice.login(this.pessoa.getMatricula(),this.pessoa.getPassword());
		
		if(login){
			
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			try {
				//redireciona para pagina inicial do SCAP
				context.redirect(context.getRequestContextPath() + "/" + "inicial.faces");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","Matricula ou senha Inválidos"));
		}	
				
	}
	
	/*public boolean isLoggedIn() {
		return LoginService.getCurrentUser() != null;
	}*/

}
