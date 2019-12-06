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
    @BeleziKlice
    @GET
    public Response vrniArtikli(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Artikel> artikli = artikelZrno.getArtikli(query);

        Long pridobiArtikleCount=artikelZrno.pridobiArtikleCount(query);
        return Response
                .ok(artikelZrno.getArtikli(query))
                .header( "X-Total-Count",pridobiArtikleCount)
                .build();
    }

    @GET
    @Path("{id}")
    public Response vrniArtikel(@PathParam("id") Integer id){

        Artikel artikel = artikelZrno.pridobiArtikel(id);

        return Response.status(Response.Status.OK).entity(artikel).build();
    }

    @POST
    public Response dodajArtikel(Artikel artikel){

        return Response.status(Response.Status.CREATED).entity(artikelZrno.dodajArtikel(artikel)).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiArtikel(@PathParam("id") Integer id, Artikel artikel){
        return Response.status(Response.Status.CREATED).entity(artikelZrno.posodobiArtikel(id, artikel)).build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniArtikel(@PathParam("id") Integer id){
        return Response.status(Response.Status.OK).entity(artikelZrno.odstraniArtikel(id)).build();
    }
}
