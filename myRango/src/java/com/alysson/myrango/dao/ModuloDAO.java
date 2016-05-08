
package com.alysson.myrango.dao;

import com.alysson.myrango.model.Modulo;
import com.alysson.myrango.model.Usuario;
import com.alysson.myrango.util.DAO;
import com.alysson.myrango.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alysson
 */
public class ModuloDAO {
    
    private DAO dao;

    public ModuloDAO() {
        this.dao=new DAO();
    }
    
    
    
    public Object salva(Modulo modulo) throws Exception{
      return this.dao.salva(modulo);
    } 
    
    public Modulo procura(){
        return (Modulo) this.dao.procura(1l,Modulo.class);
    }
    
    public List<Modulo> listaTudo(){
        return this.dao.listaModulo(Modulo.class);
         
    }    
    
}
