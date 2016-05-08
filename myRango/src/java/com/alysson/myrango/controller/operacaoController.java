
package com.alysson.myrango.controller;

import com.alysson.myrango.dao.OperacaoDAO;
import com.alysson.myrango.dao.UsuarioDAO;
import com.alysson.myrango.model.Modulo;
import com.alysson.myrango.model.Operacao;
import com.alysson.myrango.model.Usuario;
import com.alysson.myrango.util.Datas;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alysson
 */
@ManagedBean
@RequestScoped
public class operacaoController implements Serializable{
    

    private Operacao operacao = new Operacao();
    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Modulo modulo = new Modulo();
    private OperacaoDAO dao = new OperacaoDAO();
    private List<Operacao> operacoes;
    
    private String retorno;
    FacesMessage message = null;
    
    public String lista(){
        return "/operacao/lista?faces-redirect=true";
    }
    
    public String selecionaUsuario(){
        return "/operacao/selecionaUsuario?faces-redirect=true";
    }    
    
    public void pesquisa(){}
    
    public String listaFiltro(){
        try {
            this.operacao.setUsuario(new UsuarioController().getUsuarioLogado());
            this.operacoes = this.dao.lista(operacao);
            this.retorno = "/terminal/operacoes?faces-redirect=true";
        } catch (Exception e) {
             message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", "Busca por dados falhou!");
             FacesContext.getCurrentInstance().addMessage(null, message);
            
        }
        return retorno;
    }    
    
    public String cadastro(){
        return "/operacao/cadastro?faces-redirect=true";
    }
    
    public String editar(){
        return "/operacao/cadastro?faces-redirect=true";
    }  
    
    public String credito(){
        return "/operacao/credito?faces-redirect=true";
    }  
    
    public String debito(){
        return "/operacao/debito?faces-redirect=true";
    }     

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public List<Operacao> getOperacoes() {
        try {
            operacao.setUsuario(usuario);
            this.operacoes = this.dao.lista(operacao);
        } catch (Exception ex) {
            FacesMessage message = null;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha", "Busca por dados falhou.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return operacoes;
    }

    public void setOperacoes(List<Operacao> operacoes) {
        this.operacoes = operacoes;
    }

    public Usuario getUsuario() {
        if (usuario!=null && usuario.getId()!=null) {
            usuario = this.usuarioDAO.procura(usuario);
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }
    
    
    
    public void openDlgDetalhes() {
        RequestContext requestContext = RequestContext.getCurrentInstance();     
        requestContext.update("form:display");
        requestContext.execute("PF('dlgOperacaoDetalhes').show()");
    }    
    
    
    public void salva() throws Exception{
        FacesMessage message = null;
        try {
            this.operacao.setUsuario(usuario);
            this.operacao.setDataCad(new Datas().getDataHora());
            operacao = this.dao.save(this.operacao);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação: Registro com sucesso!", "Registro com sucesso!");              
            this.retorno="/operacao/lista?faces-redirect=true&id="+operacao.getId(); 
            //
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgAcaoConcluida').show()");            
            //            
            } catch (Exception e) {
                                   message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operação", "Erro ao registrar."+e.getMessage());
                                  }finally{
                                           FacesContext.getCurrentInstance().addMessage(null, message);
                                           //return this.retorno;
                                           }

        
    }    
    
    
    
}
