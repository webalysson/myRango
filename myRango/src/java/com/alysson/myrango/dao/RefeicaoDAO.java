/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alysson.myrango.dao;

import com.alysson.myrango.model.Operacao;
import com.alysson.myrango.model.Refeicao;
import com.alysson.myrango.model.Refeicao;
import com.alysson.myrango.model.Usuario;
import com.alysson.myrango.util.DAO;
import com.alysson.myrango.util.Datas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alysson
 */
public class RefeicaoDAO {
    
    private DAO dao;
    private OperacaoDAO operacaoDAO;
    private Refeicao refeicao = new Refeicao();
    private List<Refeicao> lista;

    public RefeicaoDAO() {
        this.dao=new DAO();
        this.operacaoDAO = new OperacaoDAO();
    }
    
    public Refeicao save(Refeicao refeicao) throws Exception{
        try {
            //formata a data da view para formato do banco
            String data_string = new Datas().converte2Banco( refeicao.getDataUso() );
            //converte a string formatada em Date
            Date dataUso = new Datas().montaDateBanco(data_string);
            refeicao.setDataUso(dataUso);
            //se existir um registro deste usuario para a data sera lancada uma excecao
            existeParaData(refeicao);
            refeicao.setDataCad(new Datas().getDataHora());
            refeicao.setDataMod(new Datas().getDataHora());
            //registra a operacao de debito associada a refeicao
            Operacao operacaoAssociada = registraOperacao(refeicao.getUsuario());
            refeicao.setOperacao( operacaoAssociada ); 
            //reinicia a o objeto dao para recuperar a sessao com a banco
            this.dao= new DAO();
            return (Refeicao) this.dao.salva(refeicao);         
            } catch (Exception e) {
              throw new Exception( e.getMessage() );
            }
    }
    
    public void existeParaData(Refeicao ref) throws Exception{
        if (this.lista(ref).size() > 0)            
            throw new Exception("Usuário possui uma confirmação para essa data: ");
    }  
    
    public Operacao registraOperacao(Usuario usuario) throws Exception{
        try {
        Operacao operacao = new Operacao();
        operacao.setTipo("D");
        operacao.setQuantidade(1);
        operacao.setStatus(1);
        operacao.setUsuario(usuario);
        return (Operacao) this.operacaoDAO.save(operacao);            
        } catch (Exception e) {
            throw new Exception("Erro ao debitar: "+e.getMessage());
        }
    }
    
    
    public Refeicao procura(Refeicao obj){
        return  (Refeicao) this.dao.procura(obj.getId(),Refeicao.class);
    }
    
    public List<Refeicao> listaTudo(){
        return  this.dao.listaRefeicao(Refeicao.class);
    } 
    
    public List<Refeicao> lista(Refeicao dto)throws Exception {
        try {
            Criteria sa = this.dao.getSession().createCriteria(Refeicao.class);
            sa.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);           
            if(dto.getUsuario()!=null && dto.getUsuario().getId()!=null)
                    sa.add(Restrictions.eq("usuario.id",dto.getUsuario().getId()));
            if(dto.getDataInicioPesquisa()!=null && dto.getDataFimPesquisa()!=null){
                    sa.add(Restrictions.between("dataUso",dto.getDataInicioPesquisa(),dto.getDataFimPesquisa()));
                    }else if(dto.getDataUso()!=null){
                            sa.add(Restrictions.eq("dataUso",dto.getDataUso()));                    
                            }
            //if(dto.getStatus()!=null)
            //        sa.add(Restrictions.eq("status",dto.getStatus()));
            sa.addOrder(Order.desc("id"));
            lista = sa.list();            
            return sa.list();            
        } catch (Exception e) {
             throw new Exception("Pesquisa falhou... ");
        }finally{
                 return lista ;            
                 }
    }     
    
    public void remove(Refeicao refeicao) throws Exception{
        //pega o objeto completo
        refeicao = this.procura(refeicao);
        //
        //new OperacaoDAO().remove( refeicao.getOperacao() );        
        this.dao.remove( refeicao );

    }
    
    public void salvaLista(ArrayList<Refeicao> refeicoes) throws Exception{
        this.dao.salvaLista(refeicoes);
    }    
    
    
}
