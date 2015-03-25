package br.ufes.scap.dominio;

public enum TipoParecer {
	
	FAVORAVEL("FAVORAVEL"),   
    DESFAVORAVEL("DESFAVORAVEL");
	
	private String label;  
	
	
	TipoParecer(String label){  
	        this.label = label;  
	    }  
	  
	    public String getLabel(){  
	        return label;  
	    }

}
