package si.fri.prpo.nakupovanje.entitete;


import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name="oznake")
@NamedQueries(value =
        {
                @NamedQuery(name = "Oznaka.getAll", query = "SELECT o FROM  Oznaka o"),
                @NamedQuery(name= "Oznaka.getSeznamOfNaslov",
                        query="SELECT o FROM Oznaka o WHERE o.naslov=naslov"),
                @NamedQuery(name= "Oznaka.getSeznamOfOpisi",
                        query="SELECT o FROM Oznaka o WHERE o.opis=opis"),
                @NamedQuery(name= "Oznaka.getNakupovalniseznam",
                        query="SELECT o FROM Oznaka o WHERE o.nakupovalniSeznami=nakupovalniSeznami")
        })
public class Oznaka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String naslov;
    private String opis;

    public Oznaka(String naslov,String opis) {
        this.naslov = naslov;
        this.opis=opis;

    }

    @ManyToMany(mappedBy = "oznake")
private List<NakupovalniSeznam> nakupovalniSeznami;

    public List<NakupovalniSeznam> getNakupovalniSeznami() {
        return nakupovalniSeznami;
    }

    public void setNakupovalniSeznami(List<NakupovalniSeznam> nakupovalniSeznami) {
        this.nakupovalniSeznami = nakupovalniSeznami;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
