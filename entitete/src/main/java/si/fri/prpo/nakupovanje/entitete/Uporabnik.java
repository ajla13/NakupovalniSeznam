package si.fri.prpo.nakupovanje.entitete;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM Uporabnik u"),
                @NamedQuery(name= "Uporabnik.getSeznamOfNames",
                        query="SELECT u FROM Uporabnik u WHERE u.ime=:ime"),
                @NamedQuery(name= "Uporabnik.getSeznamOfSurnames",
                        query="SELECT u FROM Uporabnik u WHERE u.priimek=:priimek"),
                @NamedQuery(name= "Uporabnik.getSeznamOfEmails",
                        query="SELECT u FROM Uporabnik u WHERE u.email=:email")
        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Uporabnik() {
    }

    public Uporabnik(String ime, String priimek, String uporabnisko_ime, String email) {
        this.ime = ime;
        this.priimek = priimek;
        this.uporabnisko_ime = uporabnisko_ime;
        this.email=email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabnisko_ime() {
        return uporabnisko_ime;
    }

    public void setUporabnisko_ime(String uporabnisko_ime) {
        this.uporabnisko_ime = uporabnisko_ime;
    }

    public String toString() {
        return "Uporabnik{" +
                "ime='" + ime + '\'' +
                ", uporabniskoIme='" + uporabnisko_ime + '\'' +
                ", priimek='" + priimek + '\'' +
                '}';
    }
    @Column(name="uporabnisko_ime")
    private String uporabnisko_ime;
    @Column(name = "ime")
    private String ime;
    @Column(name = "priimek")
    private String priimek;
    @Column(name="email")
    private String email;
    @OneToMany(mappedBy = "uporabnik")
    private List<NakupovalniSeznam> nakupovalniSeznami;

    // getter in setter metode

}
