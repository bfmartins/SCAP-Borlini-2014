package br.ufes.scap.persistencia;


import java.util.List;

import br.ufes.scap.persistencia.BaseDAO;
import br.ufes.scap.dominio.Pessoa;
//import br.ufes.scap.dominio.Professor;


import javax.ejb.Local;

@Local
public interface PessoaDAO extends BaseDAO<Pessoa> {
	

	public Pessoa retrieveByMatricula(String matricula);
	
	public Pessoa retrieveByPK(int codigo);
	
	public List<Pessoa> findAllProfessores();

}
