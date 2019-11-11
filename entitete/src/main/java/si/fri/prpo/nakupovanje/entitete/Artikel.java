package si.fri.prpo.nakupovanje.entitete;
import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name="artikel")
@NamedQueries(value =
        {
                @NamedQuery(name = "Artikel.getAll", query = "SELECT a FROM  Artikel a"),
                @NamedQuery(name= "Artikel.getSeznamOfCene",
                        query="SELECT a FROM Artikel a WHERE a.cena=:cena"),
                @NamedQuery(name= "Artikel.getSeznamOfKolicina",
                        query="SELECT a FROM Artikel a WHERE a.kolicina=:kolicina"),
                @NamedQuery(name= "Artikel.getSeznamOfNazivi",
                        query="SELECT a FROM Artikel a WHERE a.naziv=:naziv")
        })
public class Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "cena")
    private Integer cena;
    @Column(name="kolicina")
    private Integer kolicina;

    public Artikel() {
    }

    public Artikel(String naziv, int kolicina, int cena) {
        this.naziv = naziv;
        this.kolicina=kolicina;
        this.cena=cena;

    }

    @ManyToOne
    @JoinColumn(name = "seznam_id")
    private NakupovalniSeznam seznam;

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
