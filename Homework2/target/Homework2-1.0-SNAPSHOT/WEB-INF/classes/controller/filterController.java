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

        // Si topics está vacío o es solo un único tema, se maneja adecuadamente
        String[] llistaTopics;
        
        // Si el parámetro topics contiene comas, lo dividimos; si no, lo tratamos como un único tema
        if (topics != null && !topics.trim().isEmpty()) {
            // Si hay comas, dividimos los temas; si no, lo ponemos como un solo tema en el array
            llistaTopics = topics.contains(",") ? topics.split(",") : new String[]{topics.trim()};
        } else {
            // Si no se proporciona ningún tema, manejamos el caso según sea necesario
            llistaTopics = new String[0];  // O puedes manejar esto como un caso especial si es necesario
        }

        // Llamar al servicio para obtener los artículos filtrados
        List<ArticleResposta> resposta = articleService.getByTopicAndUser(autor, llistaTopics);

        // Agregar los artículos filtrados al modelo
        models.put("articles", resposta);

        // Retornar la vista según el resultado
        if (!resposta.isEmpty()) {
            return "/WEB-INF/views/Filtrats.jsp";
        } else {
            return "/WEB-INF/Error404.jsp";
        }
    }

    
}
