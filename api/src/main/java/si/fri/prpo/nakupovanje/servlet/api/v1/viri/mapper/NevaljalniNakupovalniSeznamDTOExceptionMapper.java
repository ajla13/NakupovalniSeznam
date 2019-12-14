package si.fri.prpo.nakupovanje.servlet.api.v1.viri.mapper;

import si.fri.prpo.nakupovanje.zrno.Izjeme.NevaljalniNakupovalniSeznamDTOIzjema;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class NevaljalniNakupovalniSeznamDTOExceptionMapper implements ExceptionMapper<NevaljalniNakupovalniSeznamDTOIzjema> {
    @Override
    public Response toResponse(NevaljalniNakupovalniSeznamDTOIzjema exception){
       return Response.status(Response.Status.BAD_REQUEST).entity("{\"napaka\":\""+exception.getMessage()+"\"}").build();


}
}
