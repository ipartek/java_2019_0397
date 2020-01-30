package com.ipartek.formacion.ejemplomvc.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ipartek.formacion.ejemplomvc.modelos.Video;
import com.ipartek.formacion.ejemplomvc.repositorios.Dao;
import com.ipartek.formacion.ejemplomvc.repositorios.FabricaDao;

@WebListener
public class InicioListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {}

    public void contextInitialized(ServletContextEvent sce)  { 
        String pathConfiguracion = sce.getServletContext().getRealPath("/WEB-INF/") + "dao.properties";
    	
        FabricaDao fabricaDao = FabricaDao.getInstancia(pathConfiguracion);
    	
        Dao<Video> dao = fabricaDao.getInstanciaVideo();
    	
        sce.getServletContext().setAttribute("dao", dao);
    }	
}
