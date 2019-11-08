package si.fri.prpo.nakupovanje.zrno;

import org.slf4j.Logger;
import si.fri.prpo.nakupovanje.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UporabnikiZrno {



    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {

        List<Uporabnik> uporabniki = em.createNamedQuery("Uporabnik.getAll").getResultList();

        return uporabniki;
    }
}