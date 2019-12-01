package si.fri.prpo.nakupovanje.zrno;

import org.jboss.logging.Logger;
import si.fri.prpo.nakupovanje.entitete.Artikel;
import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznamDto;
import si.fri.prpo.nakupovanje.entitete.Uporabnik;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class UpravljanjeNakupovalnihSeznamovZrno {

    @Inject
    private ArtikelZrno artikelZrno;
    @Inject
    private UporabnikiZrno uporabnikiZrno;
    @Inject
    private NakupovalniSeznamZrno NakupovalniseznamZrno;

    private EntityManager em;

    @Transactional
    public boolean preveriPolja(Uporabnik u){
        if(u.getIme()!=null && u.getPriimek()!=null && u.getUporabnisko_ime()!=null){
            return true;
        }
        return false;
    }

    @Transactional
    public Integer racunSeznama(NakupovalniSeznamDto ns){
        List<Artikel> list = ns.getArtikli();
        if(list == null) return null;
        Integer racun = 0;
        for(Artikel a: list){
            racun += a.getCena();
        }

        return racun;
    }

    @Transactional
    public Integer cenaSkoziNaziva(String naziv){
        Artikel artikel=em.find(Artikel.class,naziv);

        if(artikel != null) return artikel.getCena();
        return null;
    }
}
