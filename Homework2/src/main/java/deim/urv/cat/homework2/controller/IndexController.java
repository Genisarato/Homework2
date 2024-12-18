/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import deim.urv.cat.homework2.service.ArticleServiceImpl;
import deim.urv.cat.homework2.model.ArticleResposta;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

/**
 *
 * @author USUARIO
 */
@Controller
@Path("Principal")
public class IndexController {
    
    
    @Inject
    private ArticleServiceImpl articleService;
    
    @Inject
    private Models models;
    
    @GET
    public String showForm() {
        return "Principal.jsp";
    }
    

}
