package si.fri.prpo.nakupovanje.servlet;

import org.slf4j.Logger;
import si.fri.prpo.nakupovanje.entitete.Uporabnik;
import si.fri.prpo.nakupovanje.zrno.UporabnikiZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    private Logger log = Logger.getLogger(JPAServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // izpis uporabnikov na spletno stran
        write.append("<br/><br/>Uporabniki:<br/>");
        uporabnikiZrno.pridobiUporabnike().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>");
    }
}