/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alysson.myrango.dao;

import com.alysson.myrango.model.Operacao;
import com.alysson.myrango.model.Usuario;
import com.alysson.myrango.util.Constante;
import com.alysson.myrango.util.DAO;
import com.alysson.myrango.util.Datas;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alysson
 */
public class OperacaoDAO {
    
    private DAO dao;

    public OperacaoDAO() {
     this.dao = new DAO();
    }
    
    public Operacao save(Operacao operacao) throws Exception{
        try {
            operacao.setDataCad(new Datas().getDataHora());
            operacao.setDataMod(new Datas().getDataHora());
            //Atualizacao do saldo do usuario
            operacao = new UsuarioDAO().updateSaldo(operacao);
            return (Operacao) this.dao.salva(operacao);
            } catch (Exception e) {
             throw new Exception(e.getMessage());
            }
    }
    

    
    public Operacao procura(Operacao obj){
        return  (Operacao) this.dao.procura(obj.getId(),Operacao.class);
    }
    
    public List<Operacao> listaTudo(){
        return  this.dao.listaOperacao(Operacao.class);
    } 
    
    public List<Operacao> lista(Operacao dto)throws Exception {
            Criteria sa = this.dao.getSession().createCriteria(Operacao.class);
            if(dto!=null && dto.getId()!=null)
                   sa.add(Restrictions.eq("id",dto.getId()));
            if(dto.getUsuario()!=null && dto.getUsuario().getId()!=null)
                    sa.add(Restrictions.eq("usuario.id",dto.getUsuario().getId()));
            if(dto.getTipo()!=null && !dto.getTipo().equals(""))
                    sa.add(Restrictions.eq("tipo",dto.getTipo()));
            sa.addOrder(Order.desc("id"));
            return sa.list();		
    }    
    
    
    public void remove(Operacao operacao) throws Exception{
        //pega o objeto completo
        operacao = this.procura(operacao);
        //Se ja tiver sido processada nao pode ser removida
        if(operacao.getStatus()==Constante.OPERACAO_PROCESSADA)
           throw new Exception("Operação já processada."); 
        Usuario usuario = operacao.getUsuario();
        if(operacao.getTipo().equals(Constante.CREDITO))
           usuario.setSaldo( usuario.getSaldo() - operacao.getValor() );
        if(operacao.getTipo().equals(Constante.DEBITO))
           //usuario.setSaldo( usuario.getSaldo() + operacao.getValor() );
            usuario.setDebito( usuario.getDebito() - operacao.getValor() );
        //atualiza o usuario
        new UsuarioDAO().save(usuario);
        // remove o registro da operacao
        //this.dao.remove( operacao );
    }   
    
    public void salvaLista(ArrayList<Operacao> operacoes) throws Exception{
        this.dao.salvaLista(operacoes);
    }
    
    
}
