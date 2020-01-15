package si.fri.prpo.nakupovanje.servlet.api.v1.viri;


import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import si.fri.prpo.nakupovanje.entitete.Artikel;
import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovanje.zrno.NakupovalniSeznamZrno;
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
    // @BeleziKlice

    @Operation(description = "Pridobi artikle",
            summary = "Pridobi uporabnike", tags = "artikel", responses = {
            @ApiResponse(responseCode = "200", description = "poizvedba uspešna", content = @Content(schema = @Schema(implementation = Artikel.class))),
            @ApiResponse(responseCode = "500", description = "Napaka 500 - Error on backend")
    })

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
    public Response vrniSeznam(@Parameter(description="Identifikator",
            required=true)@PathParam("id") Integer id){

        NakupovalniSeznam seznam = nakupovalniSeznamZrno.pridobiSeznam(id);

        if(seznam != null){
            return Response.ok(seznam).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajUporabnika(@RequestBody(description ="DTO objekt za dodajanje seznama",
            required=true, content=@Content(schema = @Schema(implementation = NakupovalniSeznam.class)))NakupovalniSeznam seznam){

        return Response.status(Response.Status.CREATED).entity(nakupovalniSeznamZrno.dodajSeznam(seznam)).build();
    }


    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@Parameter(description="Identifikator",
            required=true)@RequestBody(description ="DTO objekt za posodobljanje seznama",
            required=true, content=@Content(schema = @Schema(implementation = NakupovalniSeznam.class)))@PathParam("id") Integer id, NakupovalniSeznam seznam){
        return Response.status(Response.Status.CREATED).entity(nakupovalniSeznamZrno.posodobiSeznam(id, seznam)).build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@Parameter(description="Identifikator",
            required=true)@PathParam("id") Integer id){
        return Response.status(Response.Status.OK).entity(nakupovalniSeznamZrno.odstraniSeznam(id)).build();
    }

}
