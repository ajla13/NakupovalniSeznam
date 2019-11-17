package si.fri.prpo.nakupovanje.entitete;

import java.time.Instant;
import java.util.List;

public class NakupovalniSeznamDto {
    private Integer Id;
    private String naziv;
    private Uporabnik uporabnik;
    private Instant ustvarjen;
    private List<Artikel> artikli;

    public List<Artikel> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<Artikel> artikli) {
        this.artikli = artikli;
    }

    public Instant getUstvarjen() {
        return ustvarjen;
    }

    public void setUstvarjen(Instant ustvarjen) {
        this.ustvarjen = ustvarjen;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
