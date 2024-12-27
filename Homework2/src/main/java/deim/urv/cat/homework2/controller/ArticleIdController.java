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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

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
    public String getArticleById(@PathParam("id") int id){
        try {
            article = articleService.getArticleById(id);
            models.put("article", article);
            return "/WEB-INF/views/article-details.jsp"; 
        } catch (Exception e) {
            // En caso de error, añadir un mensaje genérico al modelo
            models.put("error", "Hi ha hagut un error al carregar l'article.");
            return "/WEB-INF/Error404.jsp"; // Redirige a una vista de error
        }
    }
}
