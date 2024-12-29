package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.controller.UserForm;
import deim.urv.cat.homework2.model.Usuari;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.client.Entity;
import java.util.List;
import deim.urv.cat.homework2.model.CredentialsAux;

public class UserServiceImpl implements UserService {

    private final WebTarget webTarget;
    private final jakarta.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/UserService/rest/api";

    public UserServiceImpl() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("user");
    }

    /**
     * Buscar usuario por email
     * @param email
     */
    public User findUserByEmail(String email) {
        try {
            Response response = webTarget.path(email)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == 200) {
                return response.readEntity(User.class);
            } else {
                System.err.println("Error al buscar el usuario: " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addUser(UserForm user) {
        try {
            Response response = webTarget.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(user, MediaType.APPLICATION_JSON));
            
            // Comprobación de la respuesta
            if (response.getStatus() == 201) {
                System.out.println("Usuario añadido exitosamente.");
                return true;
            } else {
                System.err.println("Error al agregar el usuario: " + response.getStatus());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuari getCustomerById(long id) {
        try{
            System.out.println("Realizando llamada REST al servidor en: " + webTarget.getUri());
            // Añadimos el path("/id") para acceder al endpoint correcto
            Response response = webTarget.path("/"+ id).request(MediaType.APPLICATION_JSON).get();

            System.out.println("Código de estado de la respuesta: " + response.getStatus());

            if (response.getStatus() == 200) {
                Usuari usuari = response.readEntity(Usuari.class);
                return usuari;
            } else {
                System.err.println("Error al realizar la llamada: Código de respuesta = " + response.getStatus());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Usuari> getAllCustomers() {
        try{
            System.out.println("Realizando llamada REST al servidor en: " + webTarget.getUri());
            // Añadimos el path("/customer") para acceder al endpoint correcto
            Response response = webTarget.path("/customer").request(MediaType.APPLICATION_JSON).get();

            System.out.println("Código de estado de la respuesta: " + response.getStatus());

            if (response.getStatus() == 200) {
                List<Usuari> usuaris = response.readEntity(new jakarta.ws.rs.core.GenericType<List<Usuari>>() {});
                System.out.println("Artículos recuperados: " + usuaris);
                return usuaris;
            } else {
                System.err.println("Error al realizar la llamada: Código de respuesta = " + response.getStatus());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean modifyCustomerById(long id, Usuari nou) {
        try {
            System.out.println("Realizando llamada REST al servidor en: " + webTarget.getUri());
            Response response = webTarget.path("/"+ id).request(MediaType.APPLICATION_JSON).put(Entity.entity(nou, MediaType.APPLICATION_JSON));
            System.out.println("Código de estado de la respuesta: " + response.getStatus());

            if (response.getStatus() == 200) {
                return true;
             } else {
            // Manejar errores
            System.err.println("Error al realizar la llamada: Código de respuesta = " + response.getStatus());
            System.err.println("Mensaje del servidor: " + response.readEntity(String.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean loginCorrecte(String username, String password) {
        try {
            CredentialsAux c = new CredentialsAux(username, password);
            Response response = webTarget.path("/LoginVerification").request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(c, MediaType.APPLICATION_JSON));
            
            // Comprobación de la respuesta
            if (response.getStatus() == 200) {
                System.out.println("Credencials correctes.");
                return true;
            } else {
                System.err.println("Credencials incorrectes");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
