/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Article;
import deim.urv.cat.homework2.model.Topic;
import deim.urv.cat.homework2.model.Usuari;
import deim.urv.cat.homework2.service.ArticleServiceImpl;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
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
    private UserServiceImpl userService;
    
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

       return "/WEB-INF/views/createArticle.jsp";
    }
    
    @POST
    public String saveArticle(@FormParam("titol") String titol,
                              @FormParam("imatge") String imatge,
                              @FormParam("autor") String autor,
                              @FormParam("topics") String topics,
                              @FormParam("descripcio") String descripcio,
                              @FormParam("privat") String privat,
                              @Context HttpServletRequest request) {

        // Crear el objeto 'Usuari' a partir del nombre del autor
        Usuari nou = userService.findUserByUsername(autor);
        
        // Separar los tópicos por coma y añadirlos a la lista
        List<Topic> llista = new ArrayList<>();
        String[] separats = topics.split(",");
        for (String topic : separats) {
            llista.add(new Topic(topic.trim())); // Trim para eliminar espacios adicionales
        }

        // Convertir la cadena "Sí" o "No" a un valor booleano
        boolean esPrivado = "true".equalsIgnoreCase(privat);  // Si es "Sí", es true, sino es false

        // Crear el objeto 'Article' con los datos recibidos
        Article nuevoArticulo = new Article();
        nuevoArticulo.setTitol(titol);
        nuevoArticulo.setImatge(imatge);
        nuevoArticulo.setAutor(nou);
        nuevoArticulo.setTopics(llista); // Añadir los tópicos a la lista
        nuevoArticulo.setDescripcio(descripcio);
        nuevoArticulo.setPrivat(esPrivado);
        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");
        int articleId;
        if (username != null && password != null) {
            // Si están disponibles, pasarlas al servicio
            articleId = articleService.crearArticle(nuevoArticulo, username, password);
        } else {
            // Si no hay credenciales, simplemente obtener el artículo sin autenticación
           articleId = articleService.crearArticle(nuevoArticulo, null, null);
        }
        // Llamar al servicio para crear el artículo
        //int articleId = articleService.crearArticle(nuevoArticulo);

        // Redirigir a la página principal después de la creación del artículo
        if (articleId > 0) {
            models.put("successMessage", "Artículo creado correctamente!");
        } else {
            return"/WEB-INF/Error404.jsp";
        }
        return "redirect:/Principal"; // Esto redirige a la lista de artículos o página principal
        //redirect porta al controlador de la pagina principal a diferencia del WEB-INF... que portava a la vista.
        //es necessari aixi per a que faci be lo del boto iniciar sessio/tancar sessio
    }
    
}
