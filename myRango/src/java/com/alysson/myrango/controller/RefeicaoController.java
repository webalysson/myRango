/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alysson.myrango.controller;

import com.alysson.myrango.dao.OperacaoDAO;
import com.alysson.myrango.dao.RefeicaoDAO;
import com.alysson.myrango.dao.UsuarioDAO;
import com.alysson.myrango.model.Operacao;
import com.alysson.myrango.model.Refeicao;
import com.alysson.myrango.model.Usuario;
import com.alysson.myrango.util.Constante;
import com.alysson.myrango.util.Datas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alysson
 */
@ManagedBean
@RequestScoped 
public class RefeicaoController implements Serializable{
    
    private Usuario usuario = new Usuario();
    
    private Operacao operacao = new Operacao();   
    
    private List<Refeicao> refeicoes;
    
    private Refeicao refeicao = new Refeicao();
    
    private RefeicaoDAO dao = new RefeicaoDAO();
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    FacesMessage message = null;
    private String retorno;
    private int registros;

    public RefeicaoController() {
    }
    
    
    public String lista(){
      return "/refeicao/lista?faces-redirect=true";                    
    }
    
    public String confima(){
      return "/refeicao/confirma?faces-redirect=true";                    
    }
    
    public String cancelamento(){
      return "/refeicao/lista?faces-redirect=true&id="+refeicao.getId();                    
    }    
    
    public String processamento(){
      return "/refeicao/processamento?faces-redirect=true";                    
    }    
    
    public String listaPorData(){
      return "/refeicao/listaPorData?faces-redirect=true";                    
    }    
    
    public void detalhes(){
        refeicao.setOperacao(operacao);
    }
    
    public void pesquisa(){
        //formata data para padrao Mysql
        if (refeicao.getDataUso()!=null) {
            String data_string = new Datas().converte2Banco( refeicao.getDataUso() );
            Date dataUso = new Datas().montaDateBanco(data_string);
            refeicao.setDataUso(dataUso);
            refeicao.setDataInicioPesquisa(dataUso);
        }
        if (refeicao.getDataFimPesquisa()!=null) {
            String data_string = new Datas().converte2Banco( refeicao.getDataFimPesquisa());
            Date dataFim = new Datas().montaDateBanco(data_string);
            refeicao.setDataFimPesquisa(dataFim);
        }        
    refeicao.setUsuario(usuario);
    }
    

    public Usuario getUsuario() {
        if(usuario!=null && usuario.getId()!=null)
            usuario = this.usuarioDAO.procura(usuario);
        return usuario;
    }

    public void setUsuario(Usuario usuario) {       
        this.usuario = usuario;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Refeicao getRefeicao() {
        if(refeicao!=null && refeicao.getId()!=null)
            refeicao = this.dao.procura(refeicao);         
        return refeicao;
    }

    public void setRefeicao(Refeicao refeicao) {
        this.refeicao = refeicao;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registro) {
        this.registros = registro;
    }

    
    
    public float getValorTotalDia() {
        float valorTotalDia=0.0f;
        for (Refeicao ref : refeicoes) {
            valorTotalDia+=ref.getOperacao().getValor();
        }
        return valorTotalDia;
    }
    
    public float getValorProcessado() {
        float valorProcessado=0.0f;
        for (Refeicao ref : refeicoes) {
            if(ref.getOperacao().getStatus()==Constante.OPERACAO_PROCESSADA)
               valorProcessado+=ref.getOperacao().getValor();
        }
        return valorProcessado;
    }   
        

    
    public int getRegNaoProcessados() {
        int naoProcessados=0;
        if(refeicoes!=null){
            for (Refeicao ref : refeicoes) {                
                if(ref.getOperacao().getStatus()==Constante.OPERACAO_NAO_PROCESSADA){
                    naoProcessados+=1;
                }    
            }
        }
        return naoProcessados;
    }   

    public Calendar dataHoje(){
       return new Datas().getDataAtual();
    }
    

    public List<Refeicao> getRefeicoes() {   
        try{
        refeicao.setUsuario(usuario);
        refeicoes = this.dao.lista(refeicao);
            } catch (Exception e) {
             message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", "Busca por dados falhou!");
             FacesContext.getCurrentInstance().addMessage(null, message);
        }finally{
            return refeicoes;
        }            
        
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }
    
    
    public Usuario getUsuarioLogado() {
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
         Usuario usuarioLogado  = (Usuario) session.getAttribute("logado");
         if(usuarioLogado!=null){
             return usuarioLogado;
         }
        return null;
    }    

    
    public void salva() throws Exception{

        try {
            refeicao.setUsuario(usuario);
            refeicao.setOperacao(operacao);
                refeicao = this.dao.save(refeicao);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Refeição confirmada com sucesso!", "");              
                this.retorno="/refeicao/lista?faces-redirect=true&userId="+refeicao.getUsuario().getId();   
                //
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgAcaoConcluida').show()");            
                //
            } catch (Exception e) {
                                   message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Refeição não registrada."+e.getMessage(),"");
                                  }finally{
                                           FacesContext.getCurrentInstance().addMessage(null, message);
                                           }

        
    }
    
    
    public void cancelar(){
        try {
                this.dao.remove(refeicao);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Refeição cancelada com sucesso!","");              
                this.retorno="/refeicao/lista?faces-redirect=true";     
                //
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgAcaoConcluida').show()");            
                //                
             } catch (Exception e) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Refeição não cancelada."+e.getMessage(),"");
                }finally{
                         FacesContext.getCurrentInstance().addMessage(null, message);
                         }
    }   
    
/**
 * Realiza o processamento dos registro de refeicoes agendadas para um certo dia
 * para cada refeicao pega sua opecao e atualiza seus status para [2]PROCESSADA
 */    
    public void processar(){
        try {
            //Lista de operaccoes a serem processadas
            ArrayList <Operacao> operacoes= new ArrayList<>();
            //Lista de usuarios a terem seus saldos atualizados
            ArrayList <Usuario> usuarios= new ArrayList<>();
            //Objeto Usuario para adicionar  a lista
            Usuario user = new Usuario();
            for (Refeicao ref : refeicoes) {
                //atualiza o status da [Operacao] para [2]PROCESSSADA
                ref.getOperacao().setStatus(2);
                //atualiza o status da [Refeicao] para [2]CONFIRMADA
                ref.setStatus(2);
                //adiciona o objeto na lista
                operacoes.add(ref.getOperacao());
                //Pega o Usuario da Operacao 
                user = ref.getUsuario();
                //Pega o valor do debito agendado para aquele dia
                user.setDebitoDoDia( ref.getOperacao().getValor() );
                //Adiciona o obejto na lista
                usuarios.add(user);
                registros+=1;
                Thread.sleep(1000);
            }
            new UsuarioDAO().processaDebitoAgendado(usuarios);
            new OperacaoDAO().salvaLista(operacoes);              
            this.dao.salvaLista((ArrayList<Refeicao>) refeicoes);
            //
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgAcaoConcluida').show()");            
            //
              
        } catch (Exception e) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não processado."+e.getMessage(),"");
                }finally{
                         FacesContext.getCurrentInstance().addMessage(null, message);
                         }
    }
    
    
    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Processamento concluido!"));
    }    
    
    public void dataPermitida(){
        
    }
    
}
