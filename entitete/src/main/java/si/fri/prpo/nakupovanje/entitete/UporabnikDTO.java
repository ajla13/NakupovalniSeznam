package si.fri.prpo.nakupovanje.entitete;

public class UporabnikDTO {
    private Integer Id;
    private String ime;
    private String priimek;
    private String uporabnisko_ime;

    public String getUporabnisko_ime() {
        return uporabnisko_ime;
    }

    public void setUporabnisko_ime(String uporabnisko_ime) {
        this.uporabnisko_ime = uporabnisko_ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
