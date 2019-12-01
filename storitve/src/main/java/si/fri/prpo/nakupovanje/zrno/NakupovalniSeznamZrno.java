package si.fri.prpo.nakupovanje.zrno;


import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class NakupovalniSeznamZrno {
    private Logger log=Logger.getLogger(NakupovalniSeznamZrno.class.getName());

    private String idZrna;

    @PostConstruct
    private void init(){
        log.info("Inicializacija Zrna"+ NakupovalniSeznamZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicializacija  Zrna"+ NakupovalniSeznamZrno.class.getSimpleName());
    }
    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<NakupovalniSeznam> getNakupovalniSeznami() {

        List<NakupovalniSeznam> seznami = em.createNamedQuery("NakupovalniSeznam.getAll").getResultList();

        return seznami;
    }

    public NakupovalniSeznam pridobiSeznam(int seznamId){
       NakupovalniSeznam seznam =em.find(NakupovalniSeznam.class,seznamId);

        return seznam;

    }
    @Transactional
    public Integer odstraniSeznam(int seznamId) {
        NakupovalniSeznam seznam=pridobiSeznam(seznamId);
        if(seznam!=null){
            em.remove(seznam);
        }
        return seznamId;
    }
    @Transactional
    public NakupovalniSeznam dodajSeznam (NakupovalniSeznam seznam){
        if(seznam!=null){
            em.persist(seznam);
        }
        return seznam;
    }
    @Transactional
    public NakupovalniSeznam posodobiSeznam (int seznamId, NakupovalniSeznam seznam){
       NakupovalniSeznam s =em.find(NakupovalniSeznam.class,seznamId);
        seznam.setId(s.getId());
        em.merge(seznam);
        return seznam;
    }
}

