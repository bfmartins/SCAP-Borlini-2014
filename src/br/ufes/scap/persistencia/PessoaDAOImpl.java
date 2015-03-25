package br.ufes.scap.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import br.ufes.scap.persistencia.BaseDAOImpl;
import br.ufes.scap.dominio.Pessoa;
//import br.ufes.scap.dominio.Professor;

@Stateless
public class PessoaDAOImpl extends BaseDAOImpl<Pessoa> implements PessoaDAO {
	

	//private static final long serialVersionUID = 1L;
	
	//@PersistenceContext
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}

		
	
public Pessoa retrieveByMatricula(String matricula){
		
		Pessoa pessoa = new Pessoa();
		
		EntityManager em=null;
		PessoaDAOImpl pessoaDAO = new PessoaDAOImpl();
		
		em=pessoaDAO.getConnection();
		

		try{
			

			Query q = em.createNativeQuery("SELECT * FROM pessoa WHERE matricula = " + matricula + ";");

			
			Object[] result = (Object[]) q.getSingleResult();

			pessoa.setCd_pessoa((int) result[0]);
			pessoa.setNome((String) result[1]);
			pessoa.setMatricula((String) result[2]);
			pessoa.setEmail((String) result[3]);
			pessoa.setPassword((String) result[4]);
			pessoa.setTipoPessoa((int) result[5]);

			
		}catch(NoResultException e1) {
			System.out.println("Pessoa inexistente");
		}catch(NonUniqueResultException e2){
			System.out.println("Mais de uma pessoa cadastada com a mesma matricula");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		
		return pessoa;
		
	}

public Pessoa retrieveByPK(int codigo){
	
	Pessoa pessoa = new Pessoa();
	
	EntityManager em=null;
	PessoaDAOImpl pessoaDAO = new PessoaDAOImpl();
	
	em=pessoaDAO.getConnection();
	

	try{
		

		Query q = em.createNativeQuery("SELECT * FROM pessoa WHERE cd_pessoa = " + codigo + ";");

		
		Object[] result = (Object[]) q.getSingleResult();

		pessoa.setCd_pessoa((int) result[0]);
		pessoa.setNome((String) result[1]);
		pessoa.setMatricula((String) result[2]);
		pessoa.setEmail((String) result[3]);
		pessoa.setPassword((String) result[4]);
		
		
		
		
	}catch(NoResultException e1) {
		System.out.println("Pessoa inexistente");
	}catch(NonUniqueResultException e2){
		System.out.println("Mais de uma pessoa cadastada com a mesma matricula");
	}catch(QueryTimeoutException e3){
		System.out.println("Query timmed out");
	}catch(Exception e4){
		e4.printStackTrace();
	}
	
	return pessoa;
	
}



@Override
public List<Pessoa> findAllProfessores() {
	//List<Object[]> result = new ArrayList<Object[]>();
	List<Pessoa> professores = new ArrayList<Pessoa>();
	List<Object[]> resultset = new ArrayList<Object[]>();
	
	
	EntityManager em=null;
	PessoaDAOImpl pessoaDAO = new PessoaDAOImpl();
	
	em=pessoaDAO.getConnection();
	try{
		Query q = em.createNativeQuery("SELECT cd_pessoa,nome,matricula FROM pessoa WHERE tipoPessoa = 1 ORDER BY pessoa.nome");		
		
		resultset = q.getResultList();
		for(int i = 0; i < resultset.size(); i++){
			Object[] temp = (Object[]) resultset.get(i);			
			int codigo = (int) temp[0];
			String nome = (String) temp[1];
			String matrl = (String) temp[2];
			
			Pessoa p = new Pessoa();
			p.setCd_pessoa(codigo);
			p.setNome(nome);			
			p.setMatricula(matrl);
			professores.add(p);			
			
		}
		
		
	}catch(NoResultException e1) {
		System.out.println("Pessoa inexistente");	
	}catch(QueryTimeoutException e2){
		System.out.println("Query timmed out");
	}catch(Exception e4){
		e4.printStackTrace();
	}
	return professores;
	

}




}
