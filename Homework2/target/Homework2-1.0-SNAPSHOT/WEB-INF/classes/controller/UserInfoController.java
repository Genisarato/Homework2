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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

/**
 *
 * @author janto
 */

@Path("/userInfo")
@Controller
public class UserInfoController {
    @Context
    private HttpServletRequest request;
    
    @Inject
    private UserServiceImpl service;
    
    @GET
    public String showProfile() {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            Usuari user = service.findUserByUsername(username);
            request.setAttribute("username", username);
            request.setAttribute("email", user.getEmail());
            request.setAttribute("dni", user.getDni());
            request.setAttribute("telf", user.getTelef());

            return "/WEB-INF/views/profile.jsp";
        } else {
            return "redirect:/login";
        }
    }
}
