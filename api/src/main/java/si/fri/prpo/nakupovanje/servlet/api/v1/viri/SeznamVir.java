package si.fri.prpo.nakupovanje.servlet.api.v1.viri;


import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovanje.zrno.NakupovalniSeznamZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("seznami")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class SeznamVir {

    @Inject
    private NakupovalniSeznamZrno nakupovalniSeznamZrno;

    @GET
    public Response vrniSeznami(){

        List<NakupovalniSeznam> seznami = nakupovalniSeznamZrno.getNakupovalniSeznami();

        return Response.status(Response.Status.OK).entity(seznami).build();
    }

    @GET
    @Path("{id}")
    public Response vrniSeznam(@PathParam("id") Integer id){

        NakupovalniSeznam seznam = nakupovalniSeznamZrno.pridobiSeznam(id);

        if(seznam != null){
            return Response.ok(seznam).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajUporabnika(NakupovalniSeznam seznam){

        return Response.status(Response.Status.CREATED).entity(nakupovalniSeznamZrno.dodajSeznam(seznam)).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@PathParam("id") Integer id, NakupovalniSeznam seznam){
        return Response.status(Response.Status.CREATED).entity(nakupovalniSeznamZrno.posodobiSeznam(id, seznam)).build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@PathParam("id") Integer id){
        return Response.status(Response.Status.OK).entity(nakupovalniSeznamZrno.odstraniSeznam(id)).build();
    }

}
