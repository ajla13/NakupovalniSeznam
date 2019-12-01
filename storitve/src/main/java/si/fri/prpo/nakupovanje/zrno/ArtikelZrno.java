package si.fri.prpo.nakupovanje.zrno;

import si.fri.prpo.nakupovanje.entitete.Artikel;
import si.fri.prpo.nakupovanje.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

public class ArtikelZrno {
    private Logger log=Logger.getLogger(ArtikelZrno.class.getName());

    private String idZrna;

    @PostConstruct
    private void init(){
        log.info("Inicializacija Zrna"+ArtikelZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicializacija  Zrna"+ ArtikelZrno.class.getSimpleName());
    }
    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Artikel> getArtikli() {

        List<Artikel> artikli= em.createNamedQuery("Artikel.getAll").getResultList();

        return artikli;
    }

    public Artikel pridobiArtikel (int artikelId){
        Artikel artikel=em.find(Artikel.class,artikelId);

        return artikel;

    }
    @Transactional
    public Integer odstraniArtikel(int artikelId) {
      Artikel artikel=pridobiArtikel(artikelId);
        if(artikel!=null){
            em.remove(artikel);
        }
        return artikelId;
    }
    @Transactional
    public Artikel dodajArtikel(Artikel artikel){
        if(artikel!=null){
            em.persist(artikel);
        }
        return artikel;
    }
    @Transactional
    public Artikel posodobiArtikel(int artikelId, Artikel artikel){
        Artikel a=em.find(Artikel.class,artikelId);
        artikel.setId(a.getId());
        em.merge(artikel);
        return artikel;
    }


}
