package br.ufes.scap.dominio;

public enum StatusSolicitacao {
	
	INICIADA("Iniciada"),
	LIBERADA("Liberada"),
	BLOQUEADA("Bloqueada"),
	APROPRIADA("Apropriada"),
	APROVADA_CT("Aprovada_CT"),
	APROVADA_DI("Aprovada_DI"),
	APROVADA_PRPPG("Aprovada_PRPPG"),
	ARQUIVADA("Arquivada"),
	CANCELADA("Cancelada"),
	REPROVADA("Reprovada");
	
	private String statusSolicitacao;
	
	StatusSolicitacao(String sts_Solicitacao){
		this.statusSolicitacao = sts_Solicitacao;
	}
	
	//GETTER
	public String getStatusSolicitacao() {
		return statusSolicitacao;
	}

	
	
	
}
