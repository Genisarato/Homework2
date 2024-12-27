/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Article;
import deim.urv.cat.homework2.model.ArticleResposta;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ArticleServiceImpl implements ArticleService{
    
    
    private final WebTarget webTarget;
    private final jakarta.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/Homework1/rest/api/v1/article";
    
    public ArticleServiceImpl() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }
   
    @Override
    public List<ArticleResposta> getByTopicAndUser(String authorId, String... topicsId) {
        try {
            System.out.println("Realizando llamada REST al servidor en: " + webTarget.getUri());

            // Crear el webTarget con los parámetros
            WebTarget targetWithParams = webTarget.queryParam("author", authorId);

            // Si existen topics, añadirlos como parámetros adicionales
            if (topicsId != null && topicsId.length > 0) {
                targetWithParams = targetWithParams.queryParam("topics", String.join(",", topicsId));
            }

            // Realizar la llamada GET con los parámetros
            Response response = targetWithParams.request(MediaType.APPLICATION_JSON).get();

            System.out.println("Código de estado de la respuesta: " + response.getStatus());

            if (response.getStatus() == 200) {
                List<ArticleResposta> articles = response.readEntity(new jakarta.ws.rs.core.GenericType<List<ArticleResposta>>() {});
                System.out.println("Artículos recuperados: " + articles);
                return articles;
            } else {
                System.err.println("Error al realizar la llamada: Código de respuesta = " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public List<ArticleResposta> getAllArticle() {
        try {
            System.out.println("Realizando llamada REST al servidor en: " + webTarget.getUri());
            System.out.println("Realizando llamada REST al servidor en: " + webTarget.getUri());
            // Añadimos el path("/all") para acceder al endpoint correcto
            Response response = webTarget.path("/all").request(MediaType.APPLICATION_JSON).get();

            System.out.println("Código de estado de la respuesta: " + response.getStatus());

            if (response.getStatus() == 200) {
                List<ArticleResposta> articles = response.readEntity(new jakarta.ws.rs.core.GenericType<List<ArticleResposta>>() {});
                System.out.println("Artículos recuperados: " + articles);
                return articles;
            } else {
                System.err.println("Error al realizar la llamada: Código de respuesta = " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArticleResposta getArticleById(int id) {
        try {
            System.out.println("Realizando llamada REST al servidor en: " + webTarget.getUri());
            System.out.println("Realizando llamada REST al servidor en: " + webTarget.getUri());
            // Añadimos el path("/all") para acceder al endpoint correcto
            Response response = webTarget.path("/"+ id).request(MediaType.APPLICATION_JSON).get();

            System.out.println("Código de estado de la respuesta: " + response.getStatus());

            if (response.getStatus() == 200) {
                ArticleResposta article = response.readEntity(ArticleResposta.class);
                return article;
            } else {
                System.err.println("Error al realizar la llamada: Código de respuesta = " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int crearArticle(Article nou) {
                try {
            System.out.println("Realizando llamada REST al servidor en: " + webTarget.getUri());
            Response response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(nou, MediaType.APPLICATION_JSON));
            System.out.println("Código de estado de la respuesta: " + response.getStatus());

            if (response.getStatus() == 201) { 
                int idArticle = response.readEntity(Integer.class);
                System.out.println("Artículo creado con ID: " + idArticle);
                return idArticle;
             } else {
            // Manejar errores
            System.err.println("Error al realizar la llamada: Código de respuesta = " + response.getStatus());
            System.err.println("Mensaje del servidor: " + response.readEntity(String.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
}
