
package com.alysson.myrango.model;

import com.alysson.myrango.util.Base;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alysson
 */
@Entity
@Table(name = "modulo")
public class Modulo extends Base implements Serializable{

    public Modulo() {
        this.status=1;
    }
    
    
    
    private List<Usuario> usuarios;
    
    private Float valorTicket;
    

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "modulo")	
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }       

    public Float getValorTicket() {
        return valorTicket;
    }

    public void setValorTicket(Float valorTicket) {
        this.valorTicket = valorTicket;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Modulo other = (Modulo) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;       
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
    

    
    
    
}
