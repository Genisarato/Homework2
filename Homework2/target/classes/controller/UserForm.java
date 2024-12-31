package deim.urv.cat.homework2.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

@Named("userForm")
@RequestScoped
public class UserForm implements Serializable {
    private static final long serialVersionUID = 1L;
        
    // JSR 303 validation
    @NotBlank
    @FormParam("nom")
    @MvcBinding
    @Size(min=2, max=30, message = "El nom ha de tenir entre 2 i 30 caràcters")
    private String nom;
    
    // JSR 303 validation
    @NotBlank
    @FormParam("username")
    @MvcBinding
    @Size(min=2, max=30, message = "Last name must be between 2 and 30 characters")
    private String username;
    
    // JSR 303 validation
    @NotBlank
    @FormParam("telf")
    @MvcBinding
    @Size(min=9, max=9, message = "Ha de tenir 9 dígits")
    private String telf;
    
    // JSR 303 validation
    @NotBlank
    @FormParam("dni")
    @MvcBinding
    @Pattern(regexp = "\\d{8}[A-Za-z]", message = "El DNI ha de tenir 8 números seguits d'una lletra")
    private String dni;

    @NotBlank
    @FormParam("email")
    @Email(message = "Email should be valid")
    @MvcBinding
    private String email;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return fixNull(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }
}
