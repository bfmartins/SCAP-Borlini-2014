package br.ufes.scap.bean;





//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

//import org.primefaces.event.FileUploadEvent;  
//import org.primefaces.model.UploadedFile; 

import br.ufes.scap.aplicacao.LoginService;
import br.ufes.scap.aplicacao.SolicitacaoService;
import br.ufes.scap.dominio.Pessoa;
import br.ufes.scap.dominio.StatusSolicitacao;
import br.ufes.scap.dominio.Solicitacao;

@ManagedBean(name="CadastroSolicitacaoFormBean")
//@SessionScoped
public class CadastroSolicitacaoFormBean {
	
	
	private Solicitacao solicitacao = new Solicitacao();
	
	@EJB
	private SolicitacaoService solicitacaoService;
	
	//UploadedFile file;
	
	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}	
	
	@PostConstruct
	 public void init() {		
		 LoginService.verificalogin();		
	 }
	public void onbtnCadastrar(ActionEvent actionEvent){
		
		
		//Pega da sessao o usuario atual do sistema
		Pessoa currentUser = new Pessoa();		
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		currentUser= (Pessoa) session.getAttribute("currentUser");
		
		
		
		try{
		
		//Preenche o objeto a ser persistido
		Solicitacao solicitacaoAfast = new Solicitacao();
		solicitacaoAfast.setPessoa(currentUser);
		solicitacaoAfast.setDs_mtvafast(solicitacao.getDs_mtvafast());
		solicitacaoAfast.setDt_iniAfast(solicitacao.getDt_iniAfast());
		solicitacaoAfast.setDt_fimAfast(solicitacao.getDt_fimAfast());
		solicitacaoAfast.setDt_inievento(solicitacao.getDt_inievento());
		solicitacaoAfast.setDt_fimevento(solicitacao.getDt_fimevento());
		solicitacaoAfast.setNm_cidade(solicitacao.getNm_cidade());
		solicitacaoAfast.setnm_evento(solicitacao.getnm_evento());
		solicitacaoAfast.setTipoAfastamento(solicitacao.getTipoAfastamento());
		solicitacaoAfast.setOnus(solicitacao.getOnus());
		solicitacaoAfast.setStatusSolicitacao(StatusSolicitacao.INICIADA);	
		
		//valida combobox de tipo afastamento
		if(solicitacao.getTipoAfastamento().equals("SELECIONE")){				
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","O campo Tipo de Afastamento deve ser preenchido"));
			throw new Exception("Campo TipoAfastamento não foi preenchido");
			
		}
		//valida combobox de onus
		if(solicitacao.getOnus().equals("SELECIONE")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","O campo Onus deve ser preenchido"));
			throw new Exception("Campo Onus não foi preenchido");
			
		}
		//valida as datas preechidas
		if(!validaDatas(solicitacao.getDt_iniAfast(),solicitacao.getDt_fimAfast(),solicitacao.getDt_inievento(),solicitacao.getDt_fimevento()))
		{
			throw new Exception("Erro na validação das datas");
			
			
		}else{
			solicitacaoService.CadastrarSolicitacao(solicitacaoAfast);	
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO -","Solicitação de Afastamento Numero 18 cadastrada com sucesso!"));
			resetarCampos();
			
		}		
		
			
		}catch(Exception e){
			e.printStackTrace();
			resetarCampos();
			
		}
		
		

		
		
	}
	
	public boolean validaDatas(Date dt_iniAfast, Date dt_fimAfast, Date dt_iniEvento, Date dt_fimEvento){
		//Método que valida se as datas de inicio do afastamento e do evento são menores que as datas de fim.
		
		if(dt_iniAfast.after(dt_fimAfast)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","A data de início do afastamento não deve ser após a data de fim"));
			return false;
		}
		if(dt_iniEvento.after(dt_fimEvento)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO -","A data de início do evento não deve ser após a data de fim"));
			return false;
		}				
		
		return true;
		}
	

		
		/*public void handleFileUpload(FileUploadEvent event)
		{
			file = event.getFile();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String newFileName = servletContext.getRealPath("") + File.separator + "uploaded" + File.separator+ file.getFileName();
			FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			try
			{
				FileOutputStream fos = new FileOutputStream(new File(newFileName));
				InputStream is = file.getInputstream();
				int BUFFER_SIZE = 8192;
				byte[] buffer = new byte[BUFFER_SIZE];
				int a;
				while(true)
				{
					a = is.read(buffer);
					if(a < 0) break;
					fos.write(buffer, 0, a);
					fos.flush();
				}
		fos.close();
		is.close();
		}
		catch(IOException e){
		e.printStackTrace(); 
		}
		}*/
		
		public void resetarCampos(){
			this.solicitacao.setNm_cidade("");
			this.solicitacao.setnm_evento("");
			this.solicitacao.setDt_iniAfast(null);
			this.solicitacao.setDt_fimAfast(null);
			this.solicitacao.setDt_inievento(null);
			this.solicitacao.setDt_fimevento(null);
			this.solicitacao.setOnus(null);
			this.solicitacao.setTipoAfastamento(null);
			this.solicitacao.setStatusSolicitacao(null);		
			
		}

}
