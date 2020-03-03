package com.ipartek.formacion.ejemplohibernate;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory entityManagerFactory = 
    			Persistence.createEntityManagerFactory( "com.ipartek.formacion.ejemplohibernate" );
    	
    	EntityManager em = entityManagerFactory.createEntityManager();

    	em.getTransaction().begin();
    	
    	em.persist(new Usuario("javier@email.net", "contra"));
    	em.persist(new Usuario("pepe@email.net", "perez"));
    	
    	em.getTransaction().commit();
    	
    	em.getTransaction().begin();
    	
    	List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();
    	
    	for(Usuario usuario: usuarios) {
    		System.out.println(usuario);
    	}
    	
    	em.getTransaction().commit();
    	
    	//Validaciones
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<Usuario>> errores = 
        		validator.validate(new Usuario(1L, null, null, "a"));
        
        for(ConstraintViolation<Usuario> error: errores) {
        	System.out.println(error.getPropertyPath());
        	System.out.println(error.getMessage());
        }
    }
}
