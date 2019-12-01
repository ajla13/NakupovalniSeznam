package si.fri.prpo.nakupovanje.servlet.api.v1.viri;


import si.fri.prpo.nakupovanje.entitete.Artikel;
import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovanje.zrno.ArtikelZrno;
import si.fri.prpo.nakupovanje.zrno.anotacija.BeleziKlice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("artikli")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ArtikelVir {

    @Inject
    private ArtikelZrno artikelZrno;
    @BeleziKlice
    @GET
    public Response vrniArtikli(){

        List<Artikel> artikli = artikelZrno.getArtikli();

        return Response.status(Response.Status.OK).entity(artikli).build();
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
