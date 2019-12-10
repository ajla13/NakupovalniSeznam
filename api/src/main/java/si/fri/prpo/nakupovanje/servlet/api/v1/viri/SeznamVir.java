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
    @Operation(description="Vrne Sezname.", summary="Seznam Seznamov",tags="seznami",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Seznam Nakupovalnih Seznamov",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = NakupovalniSeznam.class))),
                            headers = {@Header(name = "X-Total-Count", description="Vrne število seznamov")}
                    )});


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

    @Operation(description="Vrne podrobnosti Seznama.", summary="Podrobnosti seznama",tags="seznami",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Podrobnosti seznama",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = NakupovalniSeznam.class)))

                    )});



    @GET
    @Path("{id}")
    public Response vrniSeznam((@Parameter(description="Identifikator",
            required=true)@PathParam("id") Integer id){

        NakupovalniSeznam seznam = nakupovalniSeznamZrno.pridobiSeznam(id);

        if(seznam != null){
            return Response.ok(seznam).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description="Dodajanje Seznama.", summary="Dodaj seznam",tags="seznami",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Seznam uspešno dodan"

                    ),
                    @ApiResponse(responseCode = "405",
                            description = "Validacija napake")

            });


    @POST
    public Response dodajUporabnika(@RequestBody(description ="DTO objekt za dodajanje seznama",
            required=true, content=@Content(schema = @Schema(implementation = NakupovalniSeznam.class)))NakupovalniSeznam seznam){

        return Response.status(Response.Status.CREATED).entity(nakupovalniSeznamZrno.dodajSeznam(seznam)).build();
    }

    @Operation(description="Posodobi seznam.", summary="Posodobi Seznam",tags="seznami",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Seznam uspešno posodobljen"

                    ),

            });
    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@Parameter(description="Identifikator",
            required=true)@RequestBody(description ="DTO objekt za posodobljanje seznama",
            required=true, content=@Content(schema = @Schema(implementation = NakupovalniSeznam.class)))@PathParam("id") Integer id, NakupovalniSeznam seznam){
        return Response.status(Response.Status.CREATED).entity(nakupovalniSeznamZrno.posodobiSeznam(id, seznam)).build();
    }
    @Operation(description="Odstrani Seznam.", summary="Odstrani Seznam",tags="seznami",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Seznam uspešno odstranjen"

                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Seznam ne obstaja"

                    )

            });
    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@Parameter(description="Identifikator",
            required=true)@PathParam("id") Integer id){
        return Response.status(Response.Status.OK).entity(nakupovalniSeznamZrno.odstraniSeznam(id)).build();
    }

}
