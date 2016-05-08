/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alysson.myrango.dao;

import com.alysson.myrango.model.Funcao;
import com.alysson.myrango.util.DAO;
import java.util.List;

/**
 *
 * @author Alysson
 */
public class FuncaoDAO {
    
    private DAO dao;

    public FuncaoDAO() {
        this.dao = new DAO();
    }
    
    

    public Object salva(Funcao funcao) throws Exception{
      return this.dao.salva(funcao);
    } 
    
    public Funcao procura(){
        return (Funcao) this.dao.procura(1l,Funcao.class);
    }
    
    public List<Funcao> listaTudo(){
        return this.dao.listaFuncao(Funcao.class);
         
    }      
    
}
