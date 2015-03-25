package br.ufes.scap.persistencia;

import javax.ejb.Stateless;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufes.scap.dominio.Mandato;

@Stateless
public class MandatoDAOImpl  extends BaseDAOImpl<Mandato> implements MandatoDAO{
	
	public int contarQuantidadeChefesDepartamento(){
		int quantidade=0;
		
		EntityManager em=null;
		MandatoDAOImpl mandatoDAO = new MandatoDAOImpl();
		
		em=mandatoDAO.getConnection();
		
		Query query = em.createNativeQuery("Select count(*) from mandato where dt_fimMandato is null;");		
		Object[] result = (Object[]) query.getSingleResult();
		quantidade = (int)result[0]; 
		return quantidade;
		
	}

	

}
