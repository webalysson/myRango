
package com.alysson.myrango.controller;

import com.alysson.myrango.dao.ModuloDAO;
import com.alysson.myrango.model.Modulo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alysson
 */
@ManagedBean
@RequestScoped
public class moduloController implements Serializable{
    
    private ModuloDAO dao = new ModuloDAO();
    private List<Modulo> modulos;
    private Modulo modulo = new Modulo();
    
    private String retorno;

    public List<Modulo> getModulos() {
        this.modulos =  this.dao.listaTudo();
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    
    public String lista(){
        return "/modulo/lista?faces-redirect=true";
    }     
    
    public String editar(){
        return "/modulo/cadastro?faces-redirect=false";
    }  
    
    
    public String atualiza() throws Exception{
        FacesMessage message = null;
        try {
            this.dao.salva(modulo);
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Editar", "Sucesso!");              
            this.retorno="/modulo/cadastro?faces-redirect=false";            
            } catch (Exception e) {
                                   message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar", "Erro ao editar.");
                                  }finally{
                                           FacesContext.getCurrentInstance().addMessage(null, message);
                                           return this.retorno;
                                           }

        
    }     
    
    
    
}
