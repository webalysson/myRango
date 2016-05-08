
package com.alysson.myrango.controller;

import com.alysson.myrango.dao.OperacaoDAO;
import com.alysson.myrango.dao.RefeicaoDAO;
import com.alysson.myrango.dao.UsuarioDAO;
import com.alysson.myrango.model.Modulo;
import com.alysson.myrango.model.Operacao;
import com.alysson.myrango.model.Refeicao;
import com.alysson.myrango.model.Usuario;
import com.alysson.myrango.util.Datas;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alysson
 */
@ManagedBean
@RequestScoped
public class TerminalController implements Serializable{

    
    private List<Operacao> operacoes;
    private OperacaoDAO operacaoDAO = new OperacaoDAO();
    private Operacao operacao = new Operacao();
    
    private Refeicao refeicao = new Refeicao();
    private RefeicaoDAO refeicaoDAO = new RefeicaoDAO();
    private List<Refeicao> refeicoes;
    
    private String retorno;
    private Usuario usuarioLogado; 
    private Usuario usuario = new Usuario();
    FacesMessage message = null;
    private boolean sessao;
    
    
    public TerminalController() {
    }
    
    public String inicio(){
       return "/terminal/home?faces-redirect=true";
    } 
    
    public void cancelar(ActionEvent event) throws IOException {
      FacesContext.getCurrentInstance().getExternalContext().redirect("/terminal/home.xhtml"); 
    }    
   
    public String meusDados(){
       return "/terminal/meusDados?faces-redirect=true";
    }      
    
    public String listaRefeicoes(){
       return "/terminal/refeicoes?faces-redirect=true";
    }     
    
    public String listaOperacoes(){
       return "/terminal/operacoes?faces-redirect=true";
    }  
    
    public String confirmarRefeicao(){
       return "/terminal/confirma?faces-redirect=true";
    }     
    
    
    public void setParametro(String Parametro,Object valor){
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
         session.setAttribute(Parametro, valor);        
    }
    
    public Object getParametro(String parametro){
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
         Object obj =  session.getAttribute(parametro);
         if(obj!=null){
             return obj;
         }
        return null;        
    }
    
    
    public Usuario getUsuarioLogado() {
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
         usuarioLogado  = (Usuario) session.getAttribute("logado");
         if(usuarioLogado!=null){
             return usuarioLogado;
         }
        return null;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isIsSessao() {
        return sessao;
    }

    public void setIsSessao(boolean isSessao) {
        this.sessao = isSessao;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }
    
    
    

    public List<Operacao> getOperacoes() {
        try{
            this.operacao.setUsuario(getUsuarioLogado());
            this.operacoes = this.operacaoDAO.lista(operacao);
            } catch (Exception e) {
             message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", "Busca por dados falhou!");
             FacesContext.getCurrentInstance().addMessage(null, message);
            
        }
        return operacoes;
    }

    public void setOperacoes(List<Operacao> operacoes) {
        this.operacoes = operacoes;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Usuario getUsuario() {
        this.usuario = this.getUsuarioLogado();
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
    public Refeicao getRefeicao() {
        if(isIsSessao())
          return (Refeicao) this.getParametro("refeicao");
        return refeicao;
    }

    public void setRefeicao(Refeicao refeicao) {
        this.refeicao = refeicao;
    }
    
    

    public List<Refeicao> getRefeicoes() {
        try{
            usuario = this.getUsuarioLogado();
            operacao.setUsuario(usuario);
            refeicao.setOperacao(operacao);
            refeicao.setUsuario(usuario);
            this.refeicoes = this.refeicaoDAO.lista(refeicao);
            } catch (Exception e) {
             message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Busca falhou ", ""+e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
            
        }             
        return refeicoes;
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }
    
    
    
    
    
    
    public void registrarRefeicao() throws Exception{
        FacesMessage message = null;
        try {
            refeicao.setUsuario(this.getUsuario());
            refeicao.setOperacao(operacao);
            refeicao = refeicaoDAO.save(refeicao);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Refeição confirmada com sucesso!",null);              
            this.retorno="/terminal/refeicoes?faces-redirect=true&id="+refeicao.getId();  
            //
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgAcaoConcluida').show()");            
            //            
            } catch (Exception e) {
                                   message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Refeição não registrada."+e.getMessage(),null);
                                  }finally{
                                           FacesContext.getCurrentInstance().addMessage(null, message);
                                           }

        
    }    
    
    public void pesquisaRefeicoes(){
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
    }    
    
    public void pesquisaOperacoes(){}    
    
    
    
}
