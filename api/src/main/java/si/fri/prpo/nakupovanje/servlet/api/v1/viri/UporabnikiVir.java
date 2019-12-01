package si.fri.prpo.nakupovanje.servlet.api.v1.viri;

import si.fri.prpo.nakupovanje.entitete.Uporabnik;
import si.fri.prpo.nakupovanje.zrno.UporabnikiZrno;
import si.fri.prpo.nakupovanje.zrno.anotacija.BeleziKlice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {

    @Inject
    private UporabnikiZrno uporabnikiZrno;
    @BeleziKlice
    @GET
    public Response vrniUporabnike(){

        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();

        return Response.status(Response.Status.OK).entity(uporabniki).build();
    }

    @GET
    @Path("{id}")
    public Response vrniUporabnika(@PathParam("id") Integer id){

        Uporabnik uporabnik = uporabnikiZrno.pridobiUporabnika(id);

        if(uporabnik != null){
            return Response.ok(uporabnik).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajUporabnika(Uporabnik uporabnik){

        return Response.status(Response.Status.CREATED).entity(uporabnikiZrno.dodajUporabnika(uporabnik)).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@PathParam("id") Integer id, Uporabnik uporabnik){
        return Response.status(Response.Status.CREATED).entity(uporabnikiZrno.posodobiUporabnika(id, uporabnik)).build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@PathParam("id") Integer id){
        return Response.status(Response.Status.OK).entity(uporabnikiZrno.odstraniUporabnika(id)).build();
    }

}