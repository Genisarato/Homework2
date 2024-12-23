/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Credentials;
import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import model.entities.Article;
import model.entities.ArticleResposta;
import model.entities.Topic;
import model.entities.Usuari;

/**
 *
 * @author USUARIO
 */

@Stateless
@Path("/article")
public class ArticleService extends AbstractFacade<Article>{
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    private Map<Integer, Article> articles = new HashMap<>();
    
    @Context
    private HttpHeaders headers;
    
    public ArticleService() {
        super(Article.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    //Acabar de revisar si ha de retorna tot, 
    /*
    Yo suposo que només a de retorna titol, data, Nom_Escritor, Descripció i imatge
    Tot lo demés no fa falta, mirar-meu millor
    haure de fer que me busque segons lo nom i no segons lo id pq sino no aclarim res
    llavors haure de canviar la query
    pa pugue fer lo find
    i implementar que al fer lo post de usuaris ps no se puguen repiti noms q sino caguem
    
    */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getByTopicAndUser(@QueryParam("author") long author, @QueryParam("topic") long... topics) {

        Usuari autorBD = null;
        List<Topic> resultatNoms = null;
        String query = "SELECT a FROM Article a WHERE 1=1";

        //Recuperem autor per a fer l'=
        if (author > 0) {
            try {
                autorBD = em.find(Usuari.class, author);  // Comparar el objeto 'autorBD' en la consulta
                query += " AND a.autor=:author";
            
            } catch (NoResultException ex) {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuai no registrat").build();
            }
        }

        // Validem tòpics com en el post i capem a 2
        //Decicions de disseny
        if (topics != null && topics.length > 0) {
            try {
                List<Long> primers2 = Arrays.stream(topics)  // Com passem ids hem de agafar i limitar a 2 i volem la llista
                                   .limit(2)       
                                   .boxed()        
                                   .collect(Collectors.toList());
                // Mateix procediment que en el post, busquem que la size que serà 1 o 2 sigue igual ja que ens indicara que els tòpics existeixen
                String existQuery = "SELECT t FROM Topic t WHERE t.id IN :ids";
                resultatNoms = em.createQuery(existQuery, Topic.class)
                                 .setParameter("ids", primers2)  // Usamos los IDs directamente
                                 .getResultList();
                //Comprovació de la size
                if (resultatNoms.size() != primers2.size()) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Un o més tòpics no són vàlids").build();
                }
                query += " AND a.topics IN :topics";  //I ficar la part de la consulta necessària
            } catch (NoResultException ex) {
                return Response.status(Response.Status.NOT_FOUND).entity("Topics no existents").build();
            }
        }
        
        //Ordenem segons les views
        query += " ORDER BY a.num_views DESC";

        // Crear la consulta final
        TypedQuery<Article> consulta = em.createQuery(query, Article.class);

        // Fiquem autor si hi ha
        if (author > 0) {
            consulta.setParameter("author", autorBD);  // Pasamos el objeto completo 'autorBD'
        }
        
        //Fiquem la llista de topics a la consulta
        if (topics != null && topics.length > 0) {
            consulta.setParameter("topics", resultatNoms);  // Pasamos la lista completa de objetos 'Topic'
        }

        //Executem la query
        List<Article> articlesList = consulta.getResultList();

        // Si no trobem cap llençem un 404
        if (articlesList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("No hi ha articles d'aquest autor/amb aquest tòpics.")
                           .build();
        }
        List<ArticleResposta> result = new ArrayList<>();
        for(Article a : articlesList){
            List<String> nomTopics = new ArrayList<>();
            Collection<Topic> topicsresult = a.getTopics();
            ArticleResposta nou = new ArticleResposta();
            nou.setTitol(a.getTitol());
            nou.setDescripcio(a.getDescripcio());
            nou.setN_views(a.getNum_views());
            nou.setNom_Aut(a.getAutor().getNom());
            nou.setData_publi(a.getData_publi());
            nou.setImatge(a.getImatge());
            nou.setPrivat(a.isPrivat());
            for(Topic t : topicsresult){
                nomTopics.add(t.getName());
            }
            nou.setTopics(nomTopics);
            result.add(nou);
        }
        GenericEntity<List<ArticleResposta>> resultatxml = new GenericEntity<List<ArticleResposta>>(result) {};
        //Es retorna la llista de articles
        return Response.status(Response.Status.OK)
                       .entity(resultatxml)
                       .build();
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllArticles(){
        String query = "SELECT a FROM Article a";
        TypedQuery<Article> consulta = em.createQuery(query, Article.class);
        List<Article> articleList = consulta.getResultList();
        if (articleList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Encara no hi ha articles a la web")
                           .build();
        }
        
        List<ArticleResposta> result = new ArrayList<>();
        for(Article a : articleList){
            List<String> nomTopics = new ArrayList<>();
            Collection<Topic> topicsresult = a.getTopics();
            ArticleResposta nou = new ArticleResposta();
            nou.setTitol(a.getTitol());
            nou.setDescripcio(a.getDescripcio());
            nou.setN_views(a.getNum_views());
            nou.setNom_Aut(a.getAutor().getNom());
            nou.setData_publi(a.getData_publi());
            nou.setImatge(a.getImatge());
            nou.setPrivat(a.isPrivat());
            for(Topic t : topicsresult){
                nomTopics.add(t.getName());
            }
            nou.setTopics(nomTopics);
            result.add(nou);
        }
        GenericEntity<List<ArticleResposta>> resultatxml = new GenericEntity<List<ArticleResposta>>(result) {};
        return Response.status(Response.Status.OK)
               .entity(resultatxml)
               .build();
    }
   
    //Fer últim mètode de article
    //Acabar
    //Headers params
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getArticleById(@PathParam("id") int id){
        //Obtenim l'article amb el find ja que anem per id
        Article a = em.find(Article.class, id);
        if(a != null){
            //Comprovem si no es privat o no
            if (a.isPrivat()){
                //Codi del RESTRequestFilter per mirar el header de la petició
                boolean registrat = validarRegistrat();
                //So està registrat enviar l'article, sinó un 401(unothorized)
                if(registrat){
                    a.sumarViews();
                    Collection<Topic> topicsresult = a.getTopics();
                    List<String> nomTopics = new ArrayList<>();
                    ArticleResposta nou = new ArticleResposta();
                    nou.setTitol(a.getTitol());
                    nou.setDescripcio(a.getDescripcio());
                    nou.setN_views(a.getNum_views());
                    nou.setNom_Aut(a.getAutor().getNom());
                    nou.setData_publi(a.getData_publi());
                    nou.setImatge(a.getImatge());
                    nou.setPrivat(a.isPrivat());
                    topicsresult = a.getTopics();
                    for(Topic t : topicsresult){
                        nomTopics.add(t.getName());
                    }
                    nou.setTopics(nomTopics);
                    return Response.ok().entity(nou).build();
                }
                else return Response.status(Response.Status.UNAUTHORIZED).entity("Aquest article és privat i has d'estar registrat").build();
            }
            else{
                a.sumarViews();
                Collection<Topic> topicsresult = a.getTopics();
                List<String> nomTopics = new ArrayList<>();
                ArticleResposta nou = new ArticleResposta();
                nou.setTitol(a.getTitol());
                nou.setDescripcio(a.getDescripcio());
                nou.setN_views(a.getNum_views());
                nou.setNom_Aut(a.getAutor().getNom());
                nou.setData_publi(a.getData_publi());
                topicsresult = a.getTopics();
                for(Topic t : topicsresult){
                    nomTopics.add(t.getName());
                }
                nou.setTopics(nomTopics);
                return Response.ok().entity(nou).build();
            }
            //mirar lo de privat i retorna o no i sumar views
        }
        else return Response.status(Response.Status.NOT_FOUND).entity("No hi ha aquest article encara").build();
    }
    
    private boolean validarRegistrat(){
        List<String> headersAuth = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        
        if(headersAuth == null || headersAuth.isEmpty()){
            return false;
        }
        else{
            try{
                // Decodificar y extraer usuario y contraseña
               String auth = headersAuth.get(0).replace("Basic ", "");
               String decode = new String(Base64.getDecoder().decode(auth), StandardCharsets.UTF_8);
               StringTokenizer tokenizer = new StringTokenizer(decode, ":");
               String username = tokenizer.nextToken();
               String password = tokenizer.nextToken();

               // Validar credenciales contra la base de datos
               TypedQuery<Credentials> query = em.createNamedQuery("Credentials.findUser", Credentials.class);
               Credentials c = query.setParameter("username", username).getSingleResult();

               // Comprobar si las credenciales son válidas
               return c.getPassword().equals(password);
            }catch(Exception e){
                return false;
            }
        }
    }
    

    
    //FET i funcional
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response crearArticle(Article e){
        //comprovar que asta registrat
        Usuari autor = e.getAutor();
        //Comprovem que usuari existeix a la BD, sino retornem error per simplificar el disseny, podriem crear-lo.
         //if(autor == null)return Response.status(Response.Status.NOT_FOUND).entity("Usuari no trobat").build();
         Usuari autorBD;
        try {
            // Recuperamos el usuario por su ID desde la base de datos
            String queryAutor = "SELECT u FROM Usuari u WHERE u.nom LIKE :nom";
            autorBD = em.createQuery(queryAutor, Usuari.class)
                        .setParameter("nom", autor.getNom())
                        .getSingleResult();
        } catch (NoResultException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuari no trobat").build();
        }
        //Hem de comprovar que els 2 primers tòpics de l'article existeixein (si li passem més de 2 agafem els 2 primers)
        //Decisions de disseny
        //Obtenim els noms dels 2 primers tòpics per a buscar-los a la BD, com en el cas anterior retornarem error si no hi ha
        //els tòpics, però podriem crear-los.
        List<String> llistaTopics = e.getTopics().stream().map(Topic::getName).limit(2).collect(Collectors.toList());
        //Comprovem que hi ha almenys un tòpic per no haver errors en fer la query
        if(llistaTopics.isEmpty())return Response.status(Response.Status.BAD_REQUEST).entity("Cal proporcionar almenys un tòpic").build();
        
        String existQuery = "Select t FROM Topic t WHERE t.name IN :noms";
        List<Topic> resultatNoms = em.createQuery(existQuery, Topic.class).
                                    setParameter("noms", llistaTopics).
                                    getResultList();
        //Comprovem la existència dels tòpics ja que si la size no coincideix signficia que un dels dos no coincideix
        if(llistaTopics.size() != resultatNoms.size())return Response.status(Response.Status.BAD_REQUEST).entity("Un o més tòpics no són vàlids").build();
        e.setData_publi(new Date());
        e.setTopics(resultatNoms);
        e.setAutor(autorBD);
        //e.setPrivat(false);
        em.persist(e);
        autorBD.addArticle(e);
        autorBD.setLastArticleId(e.getId());
        
        //Una vegada fetes totes les comprovacions es fa la inserció del article a la llista
        articles.put( e.getId(), e);
        return Response.status(Response.Status.CREATED).entity(e.getId()).build();
    }
    
   /*@DELETE
   @Path("/{id}")
   public Response deleteArticle(@PathParam("id") int id){
       Article esborrar = em.find(Article.class, id);
       //Esborrar aquest
       /*
       Però tema FK que fem?
       */
       /*String query = "DELETE FROM "
       
       
   }*/

    
}
