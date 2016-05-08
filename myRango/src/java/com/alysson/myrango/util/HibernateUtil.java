package com.alysson.myrango.util;

import com.alysson.myrango.model.Usuario;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static SessionFactory factory;
	
	static {
		Configuration conf = new AnnotationConfiguration();
		
		conf.configure();
		
		factory = conf.buildSessionFactory();
	}
	
	public static Session getSession(){
		Session sessao = null;
		try{
			sessao = factory.openSession();
		}catch (Exception e) {
			System.out.println("Erro ao conectar o banco: "+ e.getMessage());
		}
		return sessao;
		
	}
	
}
