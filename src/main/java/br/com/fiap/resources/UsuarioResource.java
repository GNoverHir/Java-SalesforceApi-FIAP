package br.com.fiap.resources;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import br.com.fiap.models.Usuario;
import br.com.fiap.respositories.UsuarioRepository;


import java.util.Optional;


@Path("usuario")
public class UsuarioResource {


    private final UsuarioRepository usuarioRepository;

    public UsuarioResource(){
        usuarioRepository = new UsuarioRepository();

    }



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIt() {
        return Response.ok().header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
    }


    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUsuario(Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.verificacaoLogin(usuario.getEmail(), usuario.getSenha());
        if (usuarioOptional.isPresent()) {
            return Response.status(Response.Status.OK).entity(usuarioOptional.get()).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Usuario usuario, @Context UriInfo uriInfo, @Context HttpHeaders headers ){
        usuarioRepository.cadastroUsuario(usuario);
        return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
    }

    @OPTIONS
    public Response handlePreflight(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }
}
