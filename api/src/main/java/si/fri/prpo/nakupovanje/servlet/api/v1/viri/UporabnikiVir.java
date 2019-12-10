package si.fri.prpo.nakupovanje.servlet.api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
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

    @Operation(description="Vrne Seznam Uporabnikov.", summary="Seznam Uporabnikov",tags="uporabniki",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Seznam uporabnikov",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Uporabnik.class))),
                            headers = {@Header(name = "X-Total-Count", description="Vrne število uporabnikov")}
                            )});

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


    @Operation(description="Vrne podrobnosti Uporabnika.", summary="Podrobnosti Uporabnika",tags="uporabnik",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Podrobnosti uporabnika",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Uporabnik.class)))

                    )});



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


    @Operation(description="Dodajanje Uporabnika.", summary="Dodaj Uporabnika",tags="uporabnik",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Uporabnik uspešno dodan"

                    ),
                    @ApiResponse(responseCode = "405",
                            description = "Validacija napake")

                            });

    @POST
    public Response dodajUporabnika(@RequestBody(description ="DTO objekt za dodajanje uporabnika",
    required=true, content=@Content(schema = @Schema(implementation = Uporabnik.class)))Uporabnik uporabnik){

        return Response.status(Response.Status.CREATED).entity(uporabnikiZrno.dodajUporabnika(uporabnik)).build();
    }

    @Operation(description="Posodobi Uporabnika.", summary="Posodobi Uporabnika",tags="uporabnik",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Uporabnik uspešno posodobljen"

                    ),

            });
    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@Parameter(description="Identifikator",
            required=true)@RequestBody(description ="DTO objekt za dposodobljanje uporabnika",
            required=true, content=@Content(schema = @Schema(implementation = Uporabnik.class)))
         @PathParam("id") Integer id, Uporabnik uporabnik){
        return Response.status(Response.Status.CREATED).entity(uporabnikiZrno.posodobiUporabnika(id, uporabnik)).build();
    }

    @Operation(description="Odstrani Uporabnika.", summary="Odstrani Uporabnika",tags="uporabnik",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Uporabnik uspešno odstranjen"

                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Uporabnik ne obstaja"

                    )

            });
    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@Parameter(description="Identifikator",
            required=true)@PathParam("id") Integer id){

            return Response.status(Response.Status.OK).entity(uporabnikiZrno.odstraniUporabnika(id)).build();

        }
    }

}