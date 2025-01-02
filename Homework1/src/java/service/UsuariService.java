/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Credentials;
import authn.Secured;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.entities.Article;
import model.entities.CredentialsAux;
import model.entities.UserForm;
import model.entities.Usuari;


/**
 *
 * @author USUARIO
 */
@Path("/customer")
public class UsuariService extends AbstractFacade<Usuari>{
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    private Map<Integer, Article> articles = new HashMap<>();
    
    @Context
    private HttpHeaders headers;
    
    public UsuariService() {
        super(Usuari.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllCustomers(){
        List<Usuari> usuaris = new ArrayList<>();
        Collection<Usuari> result = new ArrayList<>();
        String query = "SELECT u FROM Usuari u";
        usuaris = em.createQuery(query, Usuari.class).getResultList();
        for(Usuari u : usuaris){
            /*if (u.getArticles() != null && !u.getArticles().isEmpty()) {
                Article a = u.getArticles().get(u.getArticles().size() - 1);  // Sol accedim si la llista no está buida
                u.setLinkArticle("/article" + a.getId());
            }*/
            result.add(u);
        }
        return Response.status(Response.Status.OK)
               .entity(result)
               .build();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCustomerById(@PathParam ("id") long id){
       Usuari result = em.find(Usuari.class, id);
       if (result != null) return Response.status(Response.Status.OK).entity(result).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @POST
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addUser(UserForm user) {
        try {
            //Es divideix el contingut de UserForm per a crear dues instàncies noves, una d'usuari i una de credentials
            Usuari u = new Usuari();
            u.setDni(user.getDni());
            u.setNom(user.getNom());
            u.setEmail(user.getEmail());
            u.setTelef(user.getTelf());
            u.setUsername(user.getUsername());
            Credentials c = new Credentials();
            c.setPassword(user.getPassword());
            c.setUsername(user.getUsername());
            
            String dni = u.getDni();
            String correu = u.getEmail();

            // Consulta combinada per a verificar si el DNI o el correu electrònic ja existeixen
            String existQuery = "SELECT u.nom FROM Usuari u WHERE u.dni LIKE :dni OR u.email LIKE :correu";
            List<String> resultats = em.createQuery(existQuery, String.class)
                                       .setParameter("dni", dni)
                                       .setParameter("correu", correu)
                                       .getResultList();

            if (!resultats.isEmpty()) {
                // Retornar missatge de conflicte si ja existeix
                return Response.status(Response.Status.CONFLICT)
                               .entity("Ja existeix un usuari amb aquest DNI o correu electrònic.")
                               .build();
            }

            // Persistir l'usuari i credencials si no hi ha conflicte
            em.persist(u);
            em.persist(c);
            return Response.status(Response.Status.CREATED)
                           .entity(u.getId())
                           .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("S'ha produït un error al servidor.")
                           .build();
        }
    }


    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modifyCustomerById(@PathParam ("id") long id, Usuari u2){
       Usuari u = em.find(Usuari.class, id);
       if (u == null) return Response.status(Response.Status.NOT_FOUND).build();
       String queryText = "UPDATE Usuari SET nom = :nom, dni = :dni, telef = :telef, email = :email WHERE id = :id";
       Query queryMod = em.createQuery(queryText);
       queryMod.setParameter("nom", u2.getNom());
       queryMod.setParameter("dni", u2.getDni());
       queryMod.setParameter("telef", u2.getTelef());
       queryMod.setParameter("email", u2.getEmail());
       queryMod.setParameter("id", id);
       if (queryMod.executeUpdate() == 1) return Response.status(Response.Status.OK).build();
       else return Response.status(Response.Status.UNAUTHORIZED).build();
    }
    
    
    @POST
    @Path("/LoginVerification")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response loginCorrecte(CredentialsAux cAux){
        try{
           String username = cAux.getUsername();
           String password = cAux.getPassword();
           // Validar credenciales contra la base de datos
           TypedQuery<Credentials> query = em.createNamedQuery("Credentials.findUser", Credentials.class);
           Credentials c = query.setParameter("username", username).getSingleResult();

           // Comprobar si las credenciales son válidas
           if (c.getPassword().equals(password)) return Response.status(Response.Status.OK).build();
           else return Response.status(Response.Status.UNAUTHORIZED).build();
        }catch(Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    @GET
    @Path("/email")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response byEmail(@QueryParam("email") String email){
        try{
            String existQuery = "SELECT u FROM Usuari u WHERE u.email = :correu";
            Usuari resultat = em.createQuery(existQuery, Usuari.class)
                                           .setParameter("correu", email)
                                           .getSingleResult();
            if (resultat == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.status(Response.Status.OK).entity(resultat).build();
        }catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/username")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response byUsername(@QueryParam("username") String username){
        try{
            String existQuery = "SELECT u FROM Usuari u WHERE u.username LIKE :username";
            Usuari resultat = em.createQuery(existQuery, Usuari.class)
                                           .setParameter("username", username)
                                           .getSingleResult();
            if (resultat == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.status(Response.Status.OK).entity(resultat).build();
        }catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

