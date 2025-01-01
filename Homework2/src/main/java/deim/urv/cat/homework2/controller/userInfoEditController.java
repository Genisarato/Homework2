/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Usuari;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

/**
 *
 * @author janto
 */
@Path("edit")
@Controller
public class userInfoEditController {
    @Context
    private HttpServletRequest request;

    @Inject
    private UserServiceImpl userService;

    @GET
    public String showEditForm() {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            // Obtenim dades de l'usuari des del servei REST
            Usuari user = userService.findUserByUsername(username);
            request.setAttribute("user", user);
            return "/WEB-INF/views/edit-info-user.jsp";
        } else {
            return "redirect:/login";
        }
    }

    @POST
    public String saveChanges(
            @FormParam("nom") String nom,
            @FormParam("email") String email,
            @FormParam("dni") String dni,
            @FormParam("telf") int telf) {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            Usuari updatedUser = new Usuari();
            updatedUser.setUsername(username);

            // Només actualitza els camps no buits
            if (nom != null && !nom.isEmpty()) updatedUser.setNom(nom);
            if (email != null && !email.isEmpty()) updatedUser.setEmail(email);
            if (dni != null && !dni.isEmpty()) updatedUser.setDni(dni);
            if (telf != 0) updatedUser.setTelef(telf);

            // Enviar dades al servei REST
            long id = userService.findUserByUsername(username).getId();
            boolean success = userService.modifyCustomerById(id, updatedUser);

            if (success) {
                return "redirect:/userInfo";
            } else {
                request.setAttribute("error", "Error al modificar les dades. Torna a intentar-ho.");
                return "/WEB-INF/views/info-user.jsp";
            }
        } else {
            return "redirect:/login";
        }
    }
}
