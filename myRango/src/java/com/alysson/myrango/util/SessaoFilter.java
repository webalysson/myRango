
package com.alysson.myrango.util;

import com.alysson.myrango.model.Usuario;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.FilterDefinition;

/**
 *
 * @author Alysson
 */
public class SessaoFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //
        String uri = ((HttpServletRequest)request).getRequestURL().toString();
        int index = uri.lastIndexOf("/");  
        String path = uri.substring(index);         
        //
        //Verifica se na sessao possui um objeto tipo Usuario
        Usuario usuarioSessao = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("logado");    
        //Condicao para o objeto capturado
        if (usuarioSessao==null && !path.equals("/login.xhtml") ) {
            //captura o path da aplicao
            String contextPath = ((HttpServletRequest) request).getContextPath();
            //redireciona para pagina de login
            ((HttpServletResponse) response).sendRedirect(contextPath + "/usuario/login.xhtml");
        }else{
            //apenas continua o fluxo
              chain.doFilter(request, response);
             }
    
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
