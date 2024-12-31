
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Genis i Jan
 */

public class Usuari {
    private long id;

    private String nom;
    private String dni;
    private int telef;
    private String username;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Link links;

    private List<Article> articles = new LinkedList<>();
    
    
    
    public Usuari() {
    }
    
    public Usuari(String nom){
        this.nom = nom;
    }
    

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public void setDni(String dni) {
        this.dni = dni;
    }
    

    public void setId(long id){
        this.id = id;
    }
    

    public void setTelef(int telef) {
        this.telef = telef;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }
    

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDni() {
        return dni;
    }

    public int getTelef() {
        return telef;
    }
    
    public String getUsername() {
        return username;
    }

    public List<Article> getArticles() {
        return articles;
    }
    
    public void setLastArticleId(int lastArticleId) {
        // Actualitzem l'últim article segons fem el post d'articles
        if(links == null){
            links = new Link();
        }
       links.setLastArticle(lastArticleId);
    }

    public Link getLinks() {
        return links;
    }
    
    public void addArticle(Article e){
        articles.add(e);
    }
    
    /*public void removeLastArticle(){
    if (!articles.isEmpty())lastArticle = articles.get(articles.size()).getTitol();
    else lastArticle = null;
    }*/

    public void setLinks(Link links) {
        this.links = links;
    }
    
}
