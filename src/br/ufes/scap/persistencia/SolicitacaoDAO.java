package br.ufes.scap.persistencia;

import java.util.List;

import javax.ejb.Local;


//import br.ufes.scap.persistencia.BaseDAO;
import br.ufes.scap.dominio.Solicitacao;


@Local
public interface SolicitacaoDAO extends BaseDAO<Solicitacao> {
	
	public Solicitacao findBySolicitante(String solicitante);
	

	public Solicitacao retrieveByPK(int pk);
	

}
