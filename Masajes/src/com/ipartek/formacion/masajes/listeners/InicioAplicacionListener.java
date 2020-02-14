package com.ipartek.formacion.masajes.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ipartek.formacion.masajes.controladores.Globales;
import com.ipartek.formacion.masajes.repositorios.FabricaDao;
import com.ipartek.formacion.masajes.repositorios.FabricaDaoProperties;

@WebListener
public class InicioAplicacionListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { }

    public void contextInitialized(ServletContextEvent sce)  {
		String pathConfiguracion = sce.getServletContext().getRealPath("/WEB-INF/") + "dao.properties";

		FabricaDao fabricaDao = FabricaDaoProperties.getInstancia(pathConfiguracion);
		
		Globales.daoSesion = fabricaDao.getSesionDao();
		Globales.daoClientes = fabricaDao.getClienteDao();
		Globales.daoServicios = fabricaDao.getServicioDao();
		Globales.daoTrabajadores = fabricaDao.getTrabajadorDao();
    }
	
}
