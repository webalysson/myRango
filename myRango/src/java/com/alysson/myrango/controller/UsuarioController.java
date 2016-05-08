
package com.alysson.myrango.controller;

import com.alysson.myrango.dao.UsuarioDAO;
import com.alysson.myrango.model.Funcao;
import com.alysson.myrango.model.Modulo;
import com.alysson.myrango.model.Usuario;
import com.alysson.myrango.util.Constante;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alysson
 */
@ManagedBean
@RequestScoped
public class UsuarioController implements Serializable{

    private Usuario usuario = new Usuario();
    private Modulo modulo = new Modulo();
    private Funcao funcao = new Funcao();
    private UsuarioDAO dao = new UsuarioDAO();
    private List<Usuario> usuarios;
    
    private String retorno;
    private Usuario usuarioLogado;

    public UsuarioController() {}
    
    public String login(){
        return "/usuario/login?faces-redirect=true";
    }
    
    public String lista(){
        return "/usuario/lista?faces-redirect=true";
    } 
    
    public String cadastro(){
        return "/usuario/cadastro?faces-redirect=true";
    }
    
    public String editar(){
        return "/usuario/cadastro?faces-redirect=false";
    }    
    
    public String detalhes(){
        return "/usuario/detalhes?faces-redirect=false";
    } 
    
    /*
      ## Inicio da sequencia de procedimentos do terminal
    */
    public String meusDados(){
       this.usuario = this.getUsuarioLogado();
       return "/terminal/meusDados?faces-redirect=true";
    }  
    
    public String listaOperacoes(){
       this.usuario = this.getUsuarioLogado();
       return "/terminal/meusDados?faces-redirect=true";
    }     
    
    public void pesquisa(){
       //return "/usuario/lista?faces-redirect=true&matricula="+usuario.getMatricula();
        //return "/usuario/lista?faces-redirect=false";
    }    
    
    /*
     ## Fim da sequencia de procedimentos do terminal 
    */
    
    /*
    public void pesquisa() throws Exception{
        this.usuarios = this.dao.lista(usuario);
        //return usuarios;
    }
    */
    
    
    
    public List<Usuario> getUsuarios() throws Exception{
        usuario.setModulo(modulo);
        this.usuarios = this.dao.lista(usuario);
        return usuarios;
    }

    public Usuario getUsuario() {
        if (usuario!=null && usuario.getId()!=null) {
            usuario  = this.dao.procura(usuario);
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

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
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

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    
    
    
    
    
    public String autentica(){
        FacesMessage message = null;
        try {
            if(this.usuario.getMatricula()!=null && this.usuario.getSenha()!=null){
               this.usuarioLogado = this.dao.existe(usuario);
                    if(usuarioLogado!=null){
                         //insere o usuario na sessao e retorna o redirecionamento de acordo com o modulo
                         this.retorno  = this.criaSessao(usuarioLogado);
                         }else{
                                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Não encontrado", "Acesso negado!");                   
                              }
                }else{
                     message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "preencha corretamente!");
                     }
           
            } catch (Exception e) {
                               message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro no sistema "+e.getCause());           
                               }
            finally{
             if(message!=null)
                 FacesContext.getCurrentInstance().addMessage(null, message);
             return retorno;             
            }

    }
    
    
    
    public String criaSessao(Usuario usuario){
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
         session.setAttribute("logado", usuario);
         String home="/terminal/home?faces-redirect=true";
         if (usuario.getFuncao().getId()==Constante.ADMINISTRADOR) {
            home="/admin/home?faces-redirect=true";
            }
         return home;
    }
    
    public String logout(){
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
         session.setAttribute("logado",null);
         session.removeAttribute("logado");
         session.invalidate();
         return "/usuario/login?faces-redirect=true";
    }    
      
    
    public void salva() throws Exception{
        FacesMessage message = null;
        try {
            //this.usuario.setModulo(modulo);
            usuario = this.dao.save(usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Sucesso!");              
            this.retorno="/usuario/detalhes?faces-redirect=true&user="+usuario.getId();            
            } catch (Exception e) {
                                   message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", "Erro ao salvar.");
                                  }finally{
                                           FacesContext.getCurrentInstance().addMessage(null, message);
                                           //return this.retorno;
                                           }

        
    }
    
    public String atualiza() throws Exception{
        FacesMessage message = null;
        try {
            //this.usuario.setModulo(modulo);
            this.dao.save(usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Editar", "Sucesso!");              
            this.retorno="/usuario/cadastro?faces-redirect=false";            
            } catch (Exception e) {
                                   message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar", "Erro ao editar salvar.");
                                  }finally{
                                           FacesContext.getCurrentInstance().addMessage(null, message);
                                           return this.retorno;
                                           }

        
    }    
    
    
}
