package si.fri.prpo.nakupovanje.entitete;

import java.time.Instant;
import java.util.List;

public class ArtikelDTO {
    private Integer Id;
    private String naziv;
    private Integer kolicina;
    private Integer cena;

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
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
