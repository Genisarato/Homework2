package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.ws.rs.GET;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("login")
@Controller
public class LoginController {
    @Inject
    Logger log;
    
    @Inject
    private UserServiceImpl UserService;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @GET
    public String showForm() {
        // Comprovar si hi ha la cookie "rememberMe"
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMe".equals(cookie.getName())) {
                    request.setAttribute("username", cookie.getValue());
                    break;
                }
            }
        }
        return "/WEB-INF/views/login-form.jsp";
    }
    
    @POST
    public String login(@FormParam("username") String username,
                        @FormParam("password") String password,
                        @FormParam("rememberMe") String rememberMe) {
        // Verificació de les credencials
        if (username != null && password != null &&
            UserService.loginCorrecte(username, password)) {

            // Crear sessió i afegir atributs
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            log.log(Level.INFO, "Usuari autenticat: {0}", username);

            // Si l'opció "Recuérdame" està seleccionada
            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie("rememberMe", username);
                cookie.setMaxAge(7 * 24 * 60 * 60); // Caduca en 7 dies
                cookie.setHttpOnly(true); // Opció per millorar la seguretat
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                // Si no està seleccionat, elimina la cookie si existeix
                Cookie cookie = new Cookie("rememberMe", null);
                cookie.setMaxAge(0); // Eliminar la cookie
                cookie.setPath("/");
                response.addCookie(cookie);
            }

            // Redirigir al controlador de Principal
            return "redirect:/Principal";
        } else {
            // Credencials incorrectes, carregar la pàgina de login amb un missatge d'error
            log.log(Level.WARNING, "Intent fallit d'inici de sessió per l'usuari: {0}", username);
            request.setAttribute("error", "Nom d'usuari o contrasenya incorrectes.");
            return "/WEB-INF/views/login-form.jsp"; // Carregar la pàgina de nou amb l'error
        }
    }
}
