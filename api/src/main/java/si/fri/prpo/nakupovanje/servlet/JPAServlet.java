package si.fri.prpo.nakupovanje.servlet;


import java.util.logging.Logger;
import java.util.logging.*;
//import org.slf4j.Logger;
import si.fri.prpo.nakupovanje.zrno.UporabnikiZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;




@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikiZrno uporabnikiZrno;

   private Logger log = Logger.getLogger(JPAServlet.class.getName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // izpis uporabnikov na spletno stran

        writer.append("<br/><br/>Uporabniki:<br/>");
        uporabnikiZrno.getUporabniki().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>"));
    }
}