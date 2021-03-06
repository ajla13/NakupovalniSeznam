package si.fri.prpo.nakupovanje.servlet.api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.fri.prpo.nakupovanje.entitete.Uporabnik;
import si.fri.prpo.nakupovanje.zrno.UporabnikiZrno;
import si.fri.prpo.nakupovanje.zrno.anotacija.BeleziKlice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {
    @Context
    protected UriInfo uriInfo;
    @Inject
    private UporabnikiZrno uporabnikiZrno;
    @BeleziKlice



    @GET
    public Response vrniUporabnike(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki(query);
        Long pridobiUporabnikeCount=uporabnikiZrno.pridobiUporabnikeCount(query);
        return Response
                .ok(uporabnikiZrno.getUporabniki(query))
                .header( "X-Total-Count",pridobiUporabnikeCount)
                .build();
    }

    @GET
    @Path("{id}")
    public Response vrniUporabnika(@Parameter(description="Identifikator",
    required=true)@PathParam("id") Integer id){

        Uporabnik uporabnik = uporabnikiZrno.pridobiUporabnika(id);

        if(uporabnik != null){
            return Response.ok(uporabnik).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajUporabnika(@RequestBody(description ="DTO objekt za dodajanje uporabnika",
    required=true, content=@Content(schema = @Schema(implementation = Uporabnik.class)))Uporabnik uporabnik){

        return Response.status(Response.Status.CREATED).entity(uporabnikiZrno.dodajUporabnika(uporabnik)).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@Parameter(description="Identifikator",
            required=true)@RequestBody(description ="DTO objekt za dposodobljanje uporabnika",
            required=true, content=@Content(schema = @Schema(implementation = Uporabnik.class)))
         @PathParam("id") Integer id, Uporabnik uporabnik){
        return Response.status(Response.Status.CREATED).entity(uporabnikiZrno.posodobiUporabnika(id, uporabnik)).build();
    }


    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@Parameter(description="Identifikator",
            required=true)@PathParam("id") Integer id){

            return Response.status(Response.Status.OK).entity(uporabnikiZrno.odstraniUporabnika(id)).build();

        }
    }

