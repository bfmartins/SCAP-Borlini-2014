package br.ufes.scap.dominio;

public enum TipoAfastamento {
	
	NACIONAL("Nacional"),   
    INTERNACIONAL("Internacional");
	
	private String label;  
	
	
	 TipoAfastamento(String label){  
	        this.label = label;  
	    }  
	  
	    public String getLabel(){  
	        return label;  
	    }

    
    

	  
  
    


}
