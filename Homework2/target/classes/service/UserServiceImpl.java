package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.controller.UserForm;
import deim.urv.cat.homework2.model.Usuari;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.client.Entity;
import java.util.List;

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
    /*public User findUserByEmail(String email) {
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
    }*/

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Usuari> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean modifyCustomerById(long id, Usuari nou) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
