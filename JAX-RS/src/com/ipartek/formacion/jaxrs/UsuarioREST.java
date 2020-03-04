package com.ipartek.formacion.jaxrs;

import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces("application/json")
@Consumes("application/json")
public class UsuarioREST {
	private static TreeMap<Long, Usuario> usuarios = new TreeMap<>();
	
	static {
		usuarios.put(1L, new Usuario(1L, "javier@email.net", "contra"));
		usuarios.put(2L, new Usuario(2L, "pepe@email.net", "perez"));
	}
	
	@GET
	public Iterable<Usuario> getAll() {
		return usuarios.values();
	}
	
	@GET
	@Path("/{id}")
	public Usuario getById(@PathParam("id") Long id) {
		return usuarios.get(id);
	}
	
	@POST
	public Usuario insert(Usuario usuario) {
		Long id = usuarios.size() == 0 ? 1 : usuarios.lastKey() + 1;
		usuario.setId(id);
		
		usuarios.put(id, usuario);
		
		return usuario;
	}
	
	@PUT
	@Path("/{id}")
	public Usuario update(@PathParam("id") Long id, Usuario usuario) {
		if(id != usuario.getId()) {
			throw new WebApplicationException("No concuerdan los id", Status.BAD_REQUEST);
		}

		if(!usuarios.containsKey(id)) {
			throw new WebApplicationException("No se ha encontrado el id a modificar", Status.NOT_FOUND);
		}
		
		usuarios.put(id, usuario);
		
		return usuario;
	}
	
	@DELETE
	@Path("/{id}")
	public String delete(@PathParam("id") Long id) {
		usuarios.remove(id);
		
		return "{}";
	}
}
