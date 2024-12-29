/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.ws.rs.GET;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author janto
 */
@Path("login")
@Controller
public class LoginController {
    @Inject
    Logger log;
    
    @Inject
    private UserServiceImpl UserService;

    @Context
    private HttpServletRequest request;

    @GET
    public String showForm() {
       return "/WEB-INF/views/login-form.jsp";
    }
    
    @POST
    public String login(@FormParam("username") String username,
                        @FormParam("password") String password) {
        // Verificació de les credencials
        if (username != null && password != null &&
            UserService.loginCorrecte(username, password)) {

            // Crear sessió i afegir atributs
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            log.log(Level.INFO, "Usuari autenticat: {0}", username);

            // Redirigir a la pàgina principal després de l'inici de sessió
            return "/WEB-INF/views/Principal";
        } else {
            // Credencials incorrectes, carregar la pàgina de login amb un missatge d'error
            log.log(Level.WARNING, "Intent fallit d'inici de sessió per l'usuari: {0}", username);
            request.setAttribute("error", "Nom d'usuari o contrasenya incorrectes.");
            return "/WEB-INF/views/login-form.jsp"; // Carregar la pàgina de nou amb l'error
        }
    }
}
