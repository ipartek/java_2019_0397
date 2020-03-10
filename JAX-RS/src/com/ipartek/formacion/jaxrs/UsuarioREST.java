package com.ipartek.formacion.jaxrs;

import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "UsuarioREST", version = "1.0.0", description = "Descripción para Swagger"), servers = {
		@Server(description = "Tomcat", url = "http://localhost:8080/JAX-RS/api/") })

@Path("/usuarios")
@Produces("application/json")
@Consumes("application/json")
public class UsuarioREST {
	private static final Logger LOGGER = Logger.getLogger(UsuarioREST.class.getCanonicalName());

	private static TreeMap<Long, Usuario> usuarios = new TreeMap<>();

	@Context
	private ServletContext context;

	static {
		LOGGER.info("Generando registros de pruebas");

		usuarios.put(1L, new Usuario(1L, "javier@email.net", "contra"));
		usuarios.put(2L, new Usuario(2L, "pepe@email.net", "perez"));
	}

	@GET
	public Iterable<Usuario> getAll() {
		// System.out.println(context);
		LOGGER.info("getAll");
		LOGGER.info(context.toString());

		return usuarios.values();
	}

	@Operation(summary = "Obtener usuario por id", responses = {
			@ApiResponse(responseCode = "200", description = "Devuelve el usuario cuyo id es el que se ha pedido", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "404", description = "No encontrado") })

	@GET
	@Path("/{id: \\d+}")
	public Usuario getById(@PathParam("id") Long id) {
		LOGGER.info("getById(" + id + ")");

		if (!usuarios.containsKey(id)) {
			throw new WebApplicationException("No se ha encontrado el usuario", Status.NOT_FOUND);
		}

		return usuarios.get(id);
	}

	@POST
	public Response insert(Usuario usuario) {
		LOGGER.info("insert(" + usuario + ")");

		Long id = usuarios.size() == 0 ? 1 : usuarios.lastKey() + 1;
		usuario.setId(id);

		usuarios.put(id, usuario);

		return Response.status(Status.CREATED).entity(usuario).build();
	}

	@PUT
	@Path("/{id: \\d+}")
	public Usuario update(@PathParam("id") Long id, Usuario usuario) {
		LOGGER.info("update(" + id + ", " + usuario + ")");

		if (id != usuario.getId()) {
			LOGGER.warning("No concuerdan los id: " + id + ", " + usuario);

			throw new WebApplicationException("No concuerdan los id", Status.BAD_REQUEST);
		}

		if (!usuarios.containsKey(id)) {
			LOGGER.warning("No se ha encontrado el id a modificar: " + id + ", " + usuario);

			throw new WebApplicationException("No se ha encontrado el id a modificar", Status.NOT_FOUND);
		}

		usuarios.put(id, usuario);

		return usuario;
	}

	@DELETE
	@Path("/{id: \\d+}")
	public String delete(@PathParam("id") Long id) {
		LOGGER.info("delete(" + id + ")");

		if (!usuarios.containsKey(id)) {
			throw new WebApplicationException("No se ha encontrado el usuario", Status.NOT_FOUND);
		}

		usuarios.remove(id);

		return "{}";
	}
}
