package si.fri.prpo.nakupovanje.servlet.api.v1.viri;


import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.nakupovanje.entitete.Artikel;
import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovanje.zrno.ArtikelZrno;
import si.fri.prpo.nakupovanje.zrno.anotacija.BeleziKlice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("artikli")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ArtikelVir {
    @Context
    protected UriInfo uriInfo;
    @Inject
    private ArtikelZrno artikelZrno;

    @Operation(description="Vrne Seznam Artiklov.", summary="Seznam Artiklov",tags="artikli",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Seznam artiklov",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Artikel.class))),
                            headers = {@Header(name = "X-Total-Count", description="Vrne število artiklov")}
                    )})


    @GET

    @BeleziKlice
    public Response vrniArtikli(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Artikel> artikli = artikelZrno.getArtikli(query);

        Long pridobiArtikleCount=artikelZrno.pridobiArtikleCount(query);
        return Response
                .ok(artikli)
                .header( "X-Total-Count",pridobiArtikleCount)
                .build();
    }
    @Operation(description="Vrne podrobnosti artikla.", summary="Podrobnosti artikla",tags="artikli",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Podrobnosti artikla",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Artikel.class)))

                    )});

    @GET
    @Path("{id}")
    public Response vrniArtikel(@Parameter(description="Identifikator",
            required=true)@PathParam("id") Integer id){

        Artikel artikel = artikelZrno.pridobiArtikel(id);

        return Response.status(Response.Status.OK).entity(artikel).build();
    }
    @Operation(description="Dodajanje Artikla.", summary="Dodaj artikel",tags="artikli",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Arikel uspešno dodan"

                    ),
                    @ApiResponse(responseCode = "405",
                            description = "Validacija napake")

            });

    @POST
    public Response dodajArtikel(@RequestBody(description ="DTO objekt za dodajanje artikla",
            required=true, content=@Content(schema = @Schema(implementation = Artikel.class)))Artikel artikel){

        return Response.status(Response.Status.CREATED).entity(artikelZrno.dodajArtikel(artikel)).build();
    }
    @Operation(description="Posodobi artikel.", summary="Posodobi artikel",tags="artikel",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Artikel uspešno posodobljen"

                    ),

            });
    @PUT
    @Path("{id}")
    public Response posodobiArtikel(@Parameter(description="Identifikator",
            required=true)@RequestBody(description ="DTO objekt za posodobljanje artikla",
            required=true, content=@Content(schema = @Schema(implementation = Artikel.class)))@PathParam("id") Integer id, Artikel artikel){
        return Response.status(Response.Status.OK).entity(artikelZrno.posodobiArtikel(id, artikel)).build();
    }
    @Operation(description="Odstrani Arikel.", summary="Odstrani artikel",tags="artikli",
            responses={
                    @ApiResponse(responseCode = "200",
                            description = "Arikel uspešno odstranjen"

                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Artikel ne obstaja"

                    )

            });
    @DELETE
    @Path("{id}")
    public Response odstraniArtikel(@Parameter(description="Identifikator",
            required=true)@PathParam("id") Integer id){
        return Response.status(Response.Status.OK).entity(artikelZrno.odstraniArtikel(id)).build();
    }
}
