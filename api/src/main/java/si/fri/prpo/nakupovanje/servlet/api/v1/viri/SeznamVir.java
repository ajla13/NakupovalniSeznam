package si.fri.prpo.nakupovanje.servlet.api.v1.viri;


import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovanje.zrno.NakupovalniSeznamZrno;
import si.fri.prpo.nakupovanje.zrno.anotacija.BeleziKlice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("seznami")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class SeznamVir {
    @Context
    protected UriInfo uriInfo;
    @Inject
    private NakupovalniSeznamZrno nakupovalniSeznamZrno;
    @BeleziKlice
    @GET
    public Response vrniSeznami(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<NakupovalniSeznam> seznami = nakupovalniSeznamZrno.getNakupovalniSeznami(query);
        Long pridobiSeznameCount=nakupovalniSeznamZrno.pridobiSeznameCount(query);
        return Response
                .ok(nakupovalniSeznamZrno.getNakupovalniSeznami(query))
                .header( "X-Total-Count",pridobiSeznameCount)
                .build();
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
