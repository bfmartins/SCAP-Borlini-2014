package br.ufes.scap.aplicacao;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufes.scap.dominio.Pessoa;
import br.ufes.scap.persistencia.PessoaDAO;



@SessionScoped
@Stateful
public class LoginService {
	

	@EJB
	private PessoaDAO pessoaDAO;
	
	private Pessoa currentUser;
	
	public static boolean isloggedin;

	public Pessoa getCurrentUser() {
		return currentUser;
	}
	
	public boolean login(String matricula, String password){
		try{
			Pessoa usuario = pessoaDAO.retrieveByMatricula(matricula);
		
			if((usuario.getPassword() != null) && usuario.getPassword().equals(password)){
				this.currentUser = usuario; 
				System.out.println("Usuario " + usuario.getMatricula() + " logado com sucesso");
				HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				session.setAttribute("currentUser", currentUser);
				this.isloggedin = true;
				return true;				
			}else{
				return false;
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
public static boolean verificalogin(){
		
		if(LoginService.isloggedin){
			return true;
		}else{
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			try {
				context.redirect(context.getRequestContextPath() + "/" + "login.faces");
			} catch (IOException e) {				
				e.printStackTrace();
			}
			return false; 
		}
		
	}
	

	
}
