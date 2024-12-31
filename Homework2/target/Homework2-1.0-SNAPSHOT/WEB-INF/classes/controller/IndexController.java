package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import deim.urv.cat.homework2.service.ArticleServiceImpl;
import deim.urv.cat.homework2.model.ArticleResposta;
import jakarta.mvc.Models;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.util.List;

/**
 *
 * @author USUARIO
 */
@Controller
@Path("Principal")
public class IndexController {

    @Inject
    private ArticleServiceImpl articleService;

    @Inject
    private Models models;

    @Context
    private HttpServletRequest request;

    @GET
    public String showForm() {
        // Comprovar si l'usuari ha iniciat sessió
        HttpSession session = request.getSession(false); // No crear una nova sessió si no existeix
        if (session != null && session.getAttribute("username") != null) {
            // Usuari loggejat
            models.put("isLoggedIn", true);
            models.put("username", session.getAttribute("username"));
        } else {
            // Usuari no loggejat
            models.put("isLoggedIn", false);
        }

        // Retorna el JSP de la pàgina principal
        return "/WEB-INF/views/Principal.jsp";
    }
}
