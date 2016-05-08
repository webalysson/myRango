
package com.alysson.myrango.model;

import com.alysson.myrango.util.BaseSemDescricao;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Alysson
 */
@Entity
@Table(name = "refeicao")
public class Refeicao extends BaseSemDescricao implements Serializable{
    
    private Usuario usuario;
    
    private Operacao operacao;
    
    private Date dataUso;
    

    @ManyToOne
    @JoinColumn(name="idUsuario",nullable=false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    @OneToOne(cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name="idOperacao")
    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }
    
    
    
    
    
    
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dataUso", length = 15)
    public Date getDataUso() {
        return dataUso;
    }

    public void setDataUso(Date dataUso) {
        this.dataUso = dataUso;
    }  
    
    
    @Transient
    @Override
    public String getNomeStatus(){
            if (this.status != null && this.status == 1)
                    return "Agendada";
            else if (this.status != null && this.status == 2)
                    return "Confirmada";
            return "Indefinido";
    }       
    
    
}
