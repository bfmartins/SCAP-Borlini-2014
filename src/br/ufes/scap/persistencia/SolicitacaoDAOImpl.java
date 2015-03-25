package br.ufes.scap.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
//import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;




import br.ufes.scap.persistencia.BaseDAOImpl;
import br.ufes.scap.dominio.Solicitacao;

@Stateless
public class SolicitacaoDAOImpl extends BaseDAOImpl<Solicitacao> implements SolicitacaoDAO {
		
		//private static final long serialVersionUID = 1L;
	
		//@PersistenceContext
		private EntityManager entityManager;
		
		protected EntityManager getEntityManager() {
			return entityManager;
		}
		
		/*@Override
		public Class<Solicitacao> getDomainClass() {
			return Solicitacao.class;
		}*/
		
		public Solicitacao findBySolicitante(String solicitante){
			Solicitacao solicitacao = new Solicitacao();
			
			//EntityManager em=null;
			PessoaDAOImpl pessoaDAO = new PessoaDAOImpl();			
			this.entityManager=pessoaDAO.getConnection();
			try{
				
					Query q = this.entityManager.createNativeQuery("SELECT * FROM solicitacao,pessoa WHERE pessoa.cd_pessoa = solicitacao.cd_pessoa AND pessoa.nome like %" + solicitante + "%;");
					List result =  new ArrayList<Solicitacao>();
					result = q.getResultList();
					
			}catch(NoResultException e1) {
				System.out.println("Nenhuma solicitação encontrada");
			}catch(NonUniqueResultException e2){
				System.out.println("Mais de uma pessoa cadastada com a mesma matricula");
			}catch(QueryTimeoutException e3){
				System.out.println("Query timmed out");
			}catch(Exception e4){
				e4.printStackTrace();
			}
			return solicitacao;
		}

		public void persistirSolicitacao(Solicitacao solicitacaoAfast){
			SolicitacaoDAOImpl solicitacaoDAO = new SolicitacaoDAOImpl();
			
			Solicitacao novaSolicitacao = new Solicitacao();
			this.entityManager=solicitacaoDAO.getConnection();
			entityManager.getTransaction().begin();
			//entityManager.persist(solicitacaoAfast);
			novaSolicitacao=entityManager.merge(solicitacaoAfast);
			entityManager.getTransaction().commit();
			
			
		}
		


		@Override
		public Solicitacao retrieveByPK(int pk) {
			Solicitacao solicitacao = new Solicitacao();
			SolicitacaoDAOImpl solicitacaoDAO = new SolicitacaoDAOImpl();			
			this.entityManager=solicitacaoDAO.getConnection();

			solicitacao= entityManager.find(Solicitacao.class, pk);
			
			return solicitacao;
		}
}
