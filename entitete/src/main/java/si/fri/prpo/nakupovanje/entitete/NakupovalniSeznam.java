package si.fri.prpo.nakupovanje.entitete;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name="nakupovalni_seznam")
@NamedQueries(value =
        {
                @NamedQuery(name = "NakupovalniSeznam.getAll", query = "SELECT n FROM NakupovalniSeznam n"),
                @NamedQuery(name= "NakupovalniSeznam.getSeznamOfUporabnik",
                        query="SELECT n FROM NakupovalniSeznam n WHERE n.uporabnik=uporabnik"),
                @NamedQuery(name= "NakupovalniSeznam.getNazivi",
                        query="SELECT n FROM NakupovalniSeznam n WHERE n.naziv=naziv"),
                @NamedQuery(name= "NakupovalniSeznam.getArtikli",
                        query="SELECT n FROM NakupovalniSeznam n WHERE n.artikli=artikli")

        })
public class NakupovalniSeznam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "ustvarjen")
    private Instant ustvarjen;

    public NakupovalniSeznam(Instant ustvarjen, String naziv) {
        this.ustvarjen = ustvarjen;
        this.naziv=naziv;

    }

    @ManyToOne
    @JoinColumn(name = "uporabnik_id")
    private Uporabnik uporabnik;
    @OneToMany(mappedBy = "seznam")
    private List<Artikel> artikli;
  /*  @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE

    })
    @JoinTable(name="nakupovalni_seznam_oznaka",
    joinColumns=@JoinColumn(name="nakupovalni_seznam_id"),
    inverseJoinColumns = @JoinColumn(name="oznaka_id"))
    private List<Oznaka> oznake;*/
    // getter in setter metode


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public Instant getUstvarjen() {
        return ustvarjen;
    }

    public void setUstvarjen(Instant ustvarjen) {
        this.ustvarjen = ustvarjen;
    }

    public List<Artikel> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<Artikel> artikli) {
        this.artikli = artikli;
    }


}
