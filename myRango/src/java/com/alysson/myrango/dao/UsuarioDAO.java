package com.alysson.myrango.dao;

import com.alysson.myrango.model.Operacao;
import com.alysson.myrango.model.Refeicao;
import com.alysson.myrango.model.Usuario;
import com.alysson.myrango.util.Constante;
import com.alysson.myrango.util.DAO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.alysson.myrango.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @author Alysson
 */
public class UsuarioDAO {
    
    private DAO dao;

    public UsuarioDAO() {
        this.dao = new DAO();
    }
    
    public Usuario save(Usuario usuario) throws Exception{
        try {
            return (Usuario) this.dao.salva(usuario);
            } catch (Exception e) {
             throw new Exception("Erro ao salvar Usuario");
            }
    }
    
    public Usuario procura(Usuario usuario){
        return (Usuario) this.dao.procura(usuario.getId(),Usuario.class);
    }
    
    public List<Usuario> listaTudo(){
        return  this.dao.listaUsuario(Usuario.class);
    }
    
    public List<Usuario> lista(Usuario dto)throws Exception {
            Criteria sa = this.dao.getSession().createCriteria(Usuario.class);
            sa.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  
            if(dto.getModulo()!=null && dto.getModulo().getId()!=null)
                    sa.add(Restrictions.eq("modulo.id",dto.getModulo().getId()));
            if(dto.getDescricao()!=null && !dto.getDescricao().equals(""))
                    sa.add(Restrictions.like("descricao",dto.getDescricao(),MatchMode.ANYWHERE).ignoreCase());
            if(dto.getMatricula()!=null && !dto.getMatricula().equals(""))
                    sa.add(Restrictions.like("matricula",dto.getMatricula(),MatchMode.ANYWHERE).ignoreCase());            
            return sa.list();		
    }      
    
    public void atualiza(Usuario usuario) throws Exception{

    }
    
    public Usuario existe (Usuario procurado) throws Exception {
            Usuario existente=null;
            try{
            Criteria sa = this.dao.getSession().createCriteria(Usuario.class);
            if(procurado.getMatricula()!=null && procurado.getSenha()!=null){
                    sa.add(Restrictions.eq("matricula",procurado.getMatricula()));
                    sa.add(Restrictions.eq("senha",procurado.getSenha()));
                    List<Usuario> list = (List<Usuario>)sa.list();
                    if(list.size()!=0)
                            existente =(Usuario)list.get(0);
            }
            }catch (Exception e) {
                    throw new Exception("Erro ao efetuar login: "+ e.getCause());
            }
            return existente;
    }
    
    
    public Operacao updateSaldo(Operacao operacao) throws Exception{
            Usuario usuario = this.procura(operacao.getUsuario());
            float valor = operacao.getQuantidade() * usuario.getModulo().getValorTicket();
            operacao.setValor(valor);
            if (operacao.getTipo().equals(Constante.CREDITO)) {
                operacao.setSaldo( usuario.getSaldo()+valor );
                usuario.setSaldo( usuario.getSaldo()+valor );
            }else if( saldoPermite(usuario, valor) ) {
                   operacao.setSaldo( usuario.getSaldo()- valor );
                   usuario.setDebito(usuario.getDebito() + valor );
                  }else{
                       throw new Exception("Saldo insuficiente!"); 
                       };  
            usuario  = this.save(usuario);
            operacao.setUsuario(usuario);
            return operacao;
    } 
    
    
    public boolean saldoPermite(Usuario usuario, float valor){
        //se for debitado imediatamente 
        //if (usuario.getSaldo()>=valor)
        //se for debito agendado
        if ( (usuario.getDebito()+valor) <= usuario.getSaldo() ) 
            return true;
        else 
            return false; 
    }    
    
    public void salvaLista(ArrayList<Usuario> usuarios) throws Exception{
        this.dao.salvaLista(usuarios);
    }  
    
    public void processaDebitoAgendado(ArrayList<Usuario> usuarios) throws Exception{
        for (Usuario usuario : usuarios) {
                usuario.setSaldo(usuario.getSaldo() - usuario.getDebitoDoDia());
                usuario.setDebito( usuario.getDebito() - usuario.getDebitoDoDia() );
        }
        this.dao.salvaLista(usuarios);
    }    
    
    
}
