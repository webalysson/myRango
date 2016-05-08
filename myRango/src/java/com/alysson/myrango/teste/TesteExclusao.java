/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alysson.myrango.teste;

import com.alysson.myrango.dao.RefeicaoDAO;
import com.alysson.myrango.model.Refeicao;

/**
 *
 * @author EU
 */
public class TesteExclusao {
    
    public static void main(String[] args) throws Exception {
       Refeicao ref1 = new Refeicao();
       RefeicaoDAO refDao = new RefeicaoDAO();
       
       ref1.setId(31l);
       //ref1 = refDao.procura(ref1);
       refDao.remove(ref1);
       
    }
    
}
