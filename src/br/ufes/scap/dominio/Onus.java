package br.ufes.scap.dominio;

public enum Onus {
	
	INEXISTENTE("Inexistente"),   
    PARCIAL("Parcial"),
    TOTAL("Total");
	
	
	private String onus;
	
	Onus(String tp_Onus){
		this.onus= tp_Onus;
	}
	
	//GETTER
	public String getOnus() {
		return onus;
	}

	

	

}
