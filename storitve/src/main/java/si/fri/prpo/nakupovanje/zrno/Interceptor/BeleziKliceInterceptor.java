package si.fri.prpo.nakupovanje.zrno.Interceptor;



import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Map;
import java.util.logging.Logger;
import si.fri.prpo.nakupovanje.zrno.BeleziKliceZrno;
import si.fri.prpo.nakupovanje.zrno.anotacija.BeleziKlice;


@Interceptor
@BeleziKlice

public class BeleziKliceInterceptor {
    @Inject
    private BeleziKliceZrno klici;
    private Logger log=Logger.getLogger(BeleziKliceInterceptor.class.getName());
    private Map<String, Integer> numberOfInvocations=klici.getNumberOfInvocations();

    @AroundInvoke
    public Object methodInterceptors (InvocationContext ctx) throws Exception {
        int invocations = 1;

        if(numberOfInvocations.containsKey(ctx.getMethod().getName())) {
            invocations = numberOfInvocations.get(ctx.getMethod().getName()) + 1;
        }

        numberOfInvocations.put(ctx.getMethod().getName(), invocations);
        klici.setNumberOfInvocations(numberOfInvocations);
        klici.ispis(ctx.getMethod().getName(),invocations);

        return ctx.proceed();
    }
    }




