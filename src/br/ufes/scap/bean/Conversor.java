package br.ufes.scap.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufes.scap.dominio.Pessoa;
import br.ufes.scap.persistencia.BaseDAO;
import br.ufes.scap.persistencia.PessoaDAO;

@FacesConverter("conversor")
public class Conversor<T> implements Converter {
	

	/** The DAO used to retrieve objects given their IDs. */
	private PessoaDAO pessoaDAO;

	/** The persistent class being handled by this converter. */
	private Class<T> persistentClass;

	/*
	public PersistentObjectConverterFromId(BaseDAO<T> dao) {
		this.dao = dao;
		persistentClass = dao.getDomainClass();
	}*/

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa entity = null;
		

		// Checks for nulls and empties.
		if ((value != null) && (value.trim().length() > 0)) {
			// Loads the entity given the id.
			try {
				int id = Integer.valueOf(value);
				entity = pessoaDAO.retrieveByPK(id);
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

	
		return entity;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		

		// Checks that the supplied value is an entity of the class referred to by the converter.
		if ((value != null) && (value.getClass().equals(persistentClass))) {
			@SuppressWarnings("unchecked")
			Pessoa entity = (Pessoa) value;

			// Checks for null id and returns the id converted to string.
			if (entity.getCd_pessoa() != 0)
				return String.valueOf(entity.getCd_pessoa());
		}

		// If it doesn't pass one of the previous checks, return an empty string.
		return "";
	}
	
	
	/* public Object getAsObject(FacesContext ctx, UIComponent component, String value) {  
	        if (value != null) {  
	            return this.getAttributesFrom(component).get(value);  
	        }  
	        return null;  
	    }  
	  
	    public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
	  
	        if (value != null  
	                && !"".equals(value)) {  
	  
	            Pessoa pessoa = (Pessoa) value;  
	  
	            // adiciona item como atributo do componente  
	            this.addAttribute(component, pessoa);  
	  
	            Long codigo = (long)pessoa.getCd_pessoa();
	            if (codigo != null) {  
	                return String.valueOf(codigo);  
	            }  
	        }  
	  
	        return (String) value;  
	    }  
	  
	    protected void addAttribute(UIComponent component, Pessoa o) {  
	        String key = String.valueOf(o.getCd_pessoa()); 
	        this.getAttributesFrom(component).put(key, o);  
	    }  
	  
	    protected Map<String, Object> getAttributesFrom(UIComponent component) {  
	        return component.getAttributes();  
	    }  */
	

}
