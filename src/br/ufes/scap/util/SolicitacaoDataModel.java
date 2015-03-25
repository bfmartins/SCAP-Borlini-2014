package br.ufes.scap.util;

import java.util.List; 
import javax.faces.model.ListDataModel; 
import br.ufes.scap.dominio.Solicitacao;
import org.primefaces.model.SelectableDataModel;

public class SolicitacaoDataModel extends ListDataModel<Solicitacao> implements SelectableDataModel<Solicitacao> {
		
		/*Construtor*/
	 	public SolicitacaoDataModel() {  
	    }
	 	
	 	/*Construtor*/
	    public SolicitacaoDataModel(List<Solicitacao> listaSolicitacoes) {  
	        super(listaSolicitacoes);  
	    }  
	      
	    @Override  
	    public Solicitacao getRowData(String rowKey) {  

	          
	        List<Solicitacao> solicitacoes = (List<Solicitacao>) getWrappedData();  
	          
	        for(Solicitacao solicitacao : solicitacoes) {  
	            if(String.valueOf(solicitacao.getCd_solicitacao()).equals(rowKey));
	                return solicitacao;  
	        }  
	          
	        return null;  
	    }  
	  
	    @Override  
	    public Object getRowKey(Solicitacao solicitacao) {  
	        return solicitacao.getCd_solicitacao();
	    }

		

}
