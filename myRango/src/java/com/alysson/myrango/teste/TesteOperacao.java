/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alysson.myrango.teste;

import com.alysson.myrango.dao.OperacaoDAO;
import com.alysson.myrango.dao.UsuarioDAO;
import com.alysson.myrango.model.Operacao;
import com.alysson.myrango.model.Usuario;

/**
 *
 * @author Alysson
 */
public class TesteOperacao {
    
    public static void main(String[] args) throws Exception {
        
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuario.setId(2l);
        usuario = usuarioDAO.procura(usuario);
        
        Operacao operacao = new Operacao();
        operacao.setTipo("C");
        operacao.setUsuario(usuario);
        
        OperacaoDAO operacaoDAO = new OperacaoDAO();
        operacaoDAO.save(operacao);
        
        
    }
    
}
