package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.ws.rs.GET;
import java.util.Base64;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("login")
@Controller
public class LoginController {
    @Inject
    Logger log;
    
    @Inject
    private UserServiceImpl UserService;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @GET
    public String showForm() {
        // Comprovar si hi ha la cookie "rememberMe"
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMe".equals(cookie.getName())) {
                    request.setAttribute("username", cookie.getValue());
                    break;
                }
            }
        }
        return "/WEB-INF/views/login-form.jsp";
    }
    
    @POST
    public String login(@FormParam("username") String username,
                        @FormParam("password") String password,
                        @FormParam("rememberMe") String rememberMe) {
        // Verificación de las credenciales
        if (username != null && password != null &&
            UserService.loginCorrecte(username, password)) {

            // Crear sesión y añadir atributos
            HttpSession session = request.getSession();
            session.setAttribute("username", username);  // Guardamos el nombre de usuario en la sesión

            // Codificamos la contraseña en Base64 antes de almacenarla en la sesión
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            session.setAttribute("password", password);  // Guardamos la contraseña codificada en la sesión

            log.log(Level.INFO, "Usuari autenticat: {0}", username);

            // Si la opción "Recuérdame" está seleccionada
            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie("rememberMe", username);  // Guardamos el nombre de usuario en la cookie
                cookie.setMaxAge(7 * 24 * 60 * 60); // Caduca en 7 días
                cookie.setHttpOnly(true);  // Mejora la seguridad
                cookie.setPath("/");  // La cookie será accesible en todo el dominio
                response.addCookie(cookie);
            } else {
                // Si "Recuérdame" no está seleccionado, eliminamos la cookie si existe
                Cookie cookie = new Cookie("rememberMe", null);
                cookie.setMaxAge(0);  // Eliminar la cookie
                cookie.setPath("/");
                response.addCookie(cookie);
            }

            // Redirigir al controlador de Principal (o página principal)
            return "redirect:/Principal";  // Redirige a la página principal tras un inicio de sesión exitoso
        } else {
            // Credenciales incorrectas, mostrar un mensaje de error
            log.log(Level.WARNING, "Intento fallido de inicio de sesión para el usuario: {0}", username);
            request.setAttribute("error", "Nombre de usuario o contraseña incorrectos.");
            return "/WEB-INF/views/login-form.jsp";  // Recargar la página de login con el mensaje de error
        }
    }


}
