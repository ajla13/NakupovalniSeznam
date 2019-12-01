package si.fri.prpo.nakupovanje.zrno;

import javax.persistence.PersistenceContext;
import java.util.Map;

public class BeleziKliceZrno {
    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private Map<String, Integer> numberOfInvocations;

    public Map<String, Integer> getNumberOfInvocations() {
        return numberOfInvocations;
    }

    public void setNumberOfInvocations(Map<String, Integer> numberOfInvocations) {
        this.numberOfInvocations = numberOfInvocations;
    }

    public void ispis(String name, int invocations){
        System.out.println("Method invocated: " + name);
        System.out.println("Number of invocations: " + invocations);
    }
}
