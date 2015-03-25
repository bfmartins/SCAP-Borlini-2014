package br.ufes.scap.persistencia;

import javax.ejb.Local;

import br.ufes.scap.dominio.Mandato;

@Local
public interface MandatoDAO extends BaseDAO<Mandato> {
	
	public int contarQuantidadeChefesDepartamento();

}
