/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Article;
import deim.urv.cat.homework2.model.ArticleResposta;
import deim.urv.cat.homework2.model.Topic;
import deim.urv.cat.homework2.model.Usuari;
import deim.urv.cat.homework2.service.ArticleServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
@Controller
@Path("filtrar")
public class filterController {
    @Inject
    private ArticleServiceImpl articleService;
    
    @Inject
    private ArticleResposta article;
    
    @Inject
    private Models models;
    
    
    @GET
    public String showForm() {
       return "/WEB-INF/views/filterForm.jsp";
    }
    
    @POST
    public String saveArticle(@FormParam("autor") String autor,
                              @FormParam("topics") String topics) {

        String[] separats = topics.split(","); 
        String[] llistaTopics = new String[separats.length];

        for (int i = 0; i < 2; i++) {
            llistaTopics[i] = separats[i].trim();
        }

        // Llamar al servicio para obtener los artículos filtrados
        List<ArticleResposta> resposta = articleService.getByTopicAndUser(autor, llistaTopics);

        // Agregar los artículos filtrados al modelo
        models.put("articles", resposta);

        if (!resposta.isEmpty()) {
            return "/WEB-INF/views/Filtrats.jsp";
        } else {
            return "/WEB-INF/Error404.jsp";
        }
    }

    
}
