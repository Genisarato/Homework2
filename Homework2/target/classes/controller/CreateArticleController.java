/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Article;
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
@Path("createArticle")
public class CreateArticleController {
    
    @Inject
    private ArticleServiceImpl articleService;
    
    @Inject
    private Models models;
    
    @GET
    public String showForm() {
       return "/WEB-INF/views/createArticle.jsp";
    }
    
    @POST
    public String saveArticle(@FormParam("titol") String titol,
                              @FormParam("imatge") String imatge,
                              @FormParam("autor") String autor,
                              @FormParam("topics") String topics,
                              @FormParam("descripcio") String descripcio,
                              @FormParam("privat") String privat) {

        // Crear el objeto 'Usuari' a partir del nombre del autor
        Usuari nou = new Usuari();
        nou.setNom(autor);

        // Separar los tópicos por coma y añadirlos a la lista
        List<Topic> llista = new ArrayList<>();
        String[] separats = topics.split(",");
        for (String topic : separats) {
            llista.add(new Topic(topic.trim())); // Trim para eliminar espacios adicionales
        }

        // Convertir la cadena "Sí" o "No" a un valor booleano
        boolean esPrivado = "Sí".equalsIgnoreCase(privat);  // Si es "Sí", es true, sino es false

        // Crear el objeto 'Article' con los datos recibidos
        Article nuevoArticulo = new Article();
        nuevoArticulo.setTitol(titol);
        nuevoArticulo.setImatge(imatge);
        nuevoArticulo.setAutor(nou);
        nuevoArticulo.setTopics(llista); // Añadir los tópicos a la lista
        nuevoArticulo.setDescripcio(descripcio);
        nuevoArticulo.setPrivat(esPrivado);

        // Llamar al servicio para crear el artículo
        int articleId = articleService.crearArticle(nuevoArticulo);

        // Redirigir a la página principal después de la creación del artículo
        if (articleId > 0) {
            models.put("successMessage", "Artículo creado correctamente!");
        } else {
            return"/WEB-INF/Error404.jsp";
        }
        return "/WEB-INF/views/Principal.jsp"; // Esto redirige a la lista de artículos o página principal
    }
    
}
