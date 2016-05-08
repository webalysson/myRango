/*
 * Autor Prof: Jefferson de Sousa Silva 
 */

package com.alysson.myrango.util;


import com.alysson.myrango.model.Funcao;
import com.alysson.myrango.model.Modulo;
import com.alysson.myrango.model.Operacao;
import com.alysson.myrango.model.Refeicao;
import com.alysson.myrango.model.Usuario;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.alysson.myrango.util.HibernateUtil;
import com.alysson.myrango.util.Merge;

/**
 *
 * @author 
 * DAO = Data Access Objects 
 */
public class DAO {
    
    private final Session session  ;
    
	private Transaction transaction;
	
	public DAO(){
			this.session = HibernateUtil.getSession();
	}
	
	public void beginTransaction() {
		this.transaction = this.session.beginTransaction();
	}

	
	public void commit() {
		this.transaction.commit();
		this.transaction = null;
	}
	
	public boolean hasTransaction() {
		return this.transaction != null;
	}
	
	public void rollback() {
		this.transaction.rollback();
		this.transaction = null;
	}
	
	public void close() {
		this.session.close();
	}

	public void remove (Object dto)throws Exception  {
		try{
			this.beginTransaction();
			this.session.delete(dto);
			this.commit();
		}catch(Exception e){this.rollback();throw new Exception(e.getMessage());}			
	}

	public void removeSemTransacao (Object dto)throws Exception  {
		try{
			this.session.delete(dto);
		}catch(Exception e){throw new Exception(e.getMessage());}			
	}
		
	
	public Object salva (Object dto) throws Exception {
		return this.salva(dto, true);
	}

	public Object salva (Object dto,boolean evict) throws Exception {
		try{
			this.beginTransaction();
			dto = this.session.merge(dto);
			this.commit();
			if(evict)
				this.session.evict(dto);
		}catch(Exception e){this.rollback();throw new Exception(e.getCause());}
		return dto;// retorna p objeto persistente
	}
	
	
	public Object salvaSemTransacao (Object dto) throws Exception {
		return this.salvaSemTransacao(dto, true);
	}
	public Object salvaSemTransacao (Object dto,boolean evict) throws Exception {
		try{
			dto = this.session.merge(dto);
			if(evict)
				this.session.evict(dto);
		} catch (Exception e) {
			throw new Exception(e.getCause());
		}
		return dto;// retorna p objeto persistente
	}
	
   	public ArrayList salvaLista (ArrayList lista) throws Exception {
    		try{
    			this.beginTransaction();
				for(Object dto : lista)
					dto = this.session.merge(dto);
				this.commit();
    		}catch(Exception e){this.rollback();throw new Exception(e.getMessage());}
    	return 	lista;
   	}		
	
	public List<Object> listaTudo(Class classe) {
		return this.session.createCriteria(classe).list();
	}
        
	public List<Usuario> listaUsuario(Class classe) {
		return this.session.createCriteria(classe).list();
	}   
        
	public List<Modulo> listaModulo(Class classe) {
		return this.session.createCriteria(classe).list();
	}  
        
	public List<Operacao> listaOperacao(Class classe) {
		return this.session.createCriteria(classe).list();
	}     
        
	public List<Refeicao> listaRefeicao(Class classe) {
		return this.session.createCriteria(classe).list();
	}  
        
	public List<Funcao> listaFuncao(Class classe) {
		return this.session.createCriteria(classe).list();
	}        

	public Object procura(Long id,Class classe) {
			return (Object) session.load(classe, id);
	    }

	public Session getSession() {
		return session;
	}
	
		
}