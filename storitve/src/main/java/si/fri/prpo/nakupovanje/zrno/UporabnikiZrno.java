package si.fri.prpo.nakupovanje.zrno;


//import org.eclipse.persistence.internal.oxm.schema.model.List;
//import com.sun.tools.javac.util.List;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.nakupovanje.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped
public class UporabnikiZrno {

   private Logger log=Logger.getLogger(UporabnikiZrno.class.getName());

   private String idZrna;

    @PostConstruct
    private void init(){
      log.info("Inicializacija Zrna"+UporabnikiZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicializacija  Zrna"+ UporabnikiZrno.class.getSimpleName());
    }
    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Uporabnik> getUporabniki(QueryParameters query) {

        List<Uporabnik> uporabniki = JPAUtils.queryEntities(em, Uporabnik.class, query);

        return uporabniki;
    }

    public Long pridobiUporabnikeCount(QueryParameters query) {

        return JPAUtils.queryEntitiesCount(em, Uporabnik.class, query);
    }
    public Uporabnik pridobiUporabnika (int uporabnikId){
        Uporabnik uporabnik =em.find(Uporabnik.class,uporabnikId);

            return uporabnik;

    }
    @Transactional
    public Integer odstraniUporabnika(int uporabnikId) {
        Uporabnik uporabnik=pridobiUporabnika(uporabnikId);
        if(uporabnik!=null){
            em.remove(uporabnik);
        }
        return uporabnikId;
    }
    @Transactional
    public Uporabnik dodajUporabnika (Uporabnik uporabnik){
        if(uporabnik!=null){
            em.persist(uporabnik);
        }
        return uporabnik;
    }
    @Transactional
    public Uporabnik posodobiUporabnika (int uporabnikId, Uporabnik uporabnik){
        Uporabnik u =em.find(Uporabnik.class,uporabnikId);
        uporabnik.setId(u.getId());
        em.merge(uporabnik);
        return uporabnik;
    }


}