/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.ArticleResposta;
import deim.urv.cat.homework2.service.ArticleServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import java.util.Base64;

/**
 *
 * @author USUARIO
 */
@Controller
@Path("article")
public class ArticleIdController {
            
    @Inject
    private ArticleServiceImpl articleService;
    
    @Inject
    private ArticleResposta article;
    
    @Inject
    private Models models;
    
    @GET
    @Path("/{id}")
    public String getArticleById(@PathParam("id") int id, @Context HttpServletRequest request) {
        try {
            
            // Obtener las credenciales de la sesión
            String username = (String) request.getSession().getAttribute("username");
            String password = (String) request.getSession().getAttribute("password");
            
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
            
            // Si las credenciales son null, no se pasa nada, el artículo es público
            if (username != null && password != null) {
                // Si están disponibles, pasarlas al servicio
                article = articleService.getArticleById(id, username, password);
            } else {
                // Si no hay credenciales, simplemente obtener el artículo sin autenticación
                article = articleService.getArticleById(id, null, null);
            }

            models.put("article", article);
            return "/WEB-INF/views/article-details.jsp";
        } catch (Exception e) {
            models.put("error", "Ha ocurrido un error al cargar el artículo.");
            return "/WEB-INF/Error404.jsp"; // Redirigir a error si ocurre algún problema
        }
    }
}
