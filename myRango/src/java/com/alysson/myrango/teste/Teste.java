package com.alysson.myrango.teste;

import com.alysson.myrango.dao.FuncaoDAO;
import com.alysson.myrango.dao.ModuloDAO;
import com.alysson.myrango.dao.OperacaoDAO;
import com.alysson.myrango.dao.UsuarioDAO;
import com.alysson.myrango.model.Funcao;
import com.alysson.myrango.model.Modulo;
import com.alysson.myrango.model.Operacao;
import com.alysson.myrango.model.Usuario;
import com.alysson.myrango.util.Constante;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Alysson
 */
public class Teste {
    public static void main(String[] args) throws Exception {
        
        Modulo modulo1 = new Modulo();
        modulo1.setDescricao("Servidor");
        modulo1.setValorTicket(2.00f);

        
        Modulo modulo2 = new Modulo();
        modulo2.setDescricao("Aluno");
        modulo2.setValorTicket(1.00f);
        
        Funcao funcao1 = new Funcao();
        funcao1.setDescricao("Administrador");
        Funcao funcao2 = new Funcao();
        funcao2.setDescricao("Usuario");
        
        ModuloDAO moduloDAO = new ModuloDAO();
        modulo1 =  (Modulo) moduloDAO.salva(modulo1);
        modulo2 =  (Modulo) moduloDAO.salva(modulo2);   
        
        FuncaoDAO funcaoDAO = new FuncaoDAO();
        funcao1 = (Funcao) funcaoDAO.salva(funcao1);
        funcao2 = (Funcao) funcaoDAO.salva(funcao2);
             
        
        Usuario usuario1 = new Usuario();
        usuario1.setDescricao("Alysson S Sousa");
        usuario1.setMatricula("111");
        usuario1.setSenha("123");
        usuario1.setModulo(modulo1);
        usuario1.setFuncao(funcao1);
        
        Usuario usuario2 = new Usuario();
        usuario2.setDescricao("Usuario de teste");
        usuario2.setMatricula("222");
        usuario1.setSenha("321");        
        usuario2.setModulo(modulo2);        
        usuario2.setFuncao(funcao2);
        
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.save(usuario1);
        usuarioDAO.save(usuario2);
        
        /*
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuario = usuarioDAO.procura(usuario);
        
        Operacao operacao = new Operacao();
        operacao.setQuantidade(2);
        operacao.setTipo(Constante.DEBITO);
        operacao.setUsuario(usuario);
        
        //operacao.setDataCad( new SimpleDateFormat("yyyy-MM-dd").parse("1986-01-02") );
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        operacao.setDataCad( Calendar.getInstance() );
        
        OperacaoDAO operacaoDAO= new OperacaoDAO();
        operacaoDAO.save(operacao);
        */
    }
}
