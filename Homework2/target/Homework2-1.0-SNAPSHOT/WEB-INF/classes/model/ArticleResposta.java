/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Genís i Jan
 */

//Clase auxiliar per a només retornar certes coses de Article
public class ArticleResposta {
    private String titol;
    private String imatge;
    private boolean privat;
    private String nom_Aut;
    private int n_views;
    private Date data_publi;
    private String descripcio;
    private List<String> topics;
    private String lastArticle;
    private int id;

    public ArticleResposta() {
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void setNom_Aut(String nom_Aut) {
        this.nom_Aut = nom_Aut;
    }

    public void setN_views(int n_views) {
        this.n_views = n_views;
    }

    public void setData_publi(Date data_publi) {
        this.data_publi = data_publi;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getTitol() {
        return titol;
    }

    public String getNom_Aut() {
        return nom_Aut;
    }

    public int getN_views() {
        return n_views;
    }

    public Date getData_publi() {
        return data_publi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public List<String> getTopics() {
        return topics;
    }

    public String getLastArticle() {
        return lastArticle;
    }

    public void setLastArticle(String lastArticle) {
        this.lastArticle = lastArticle;
    }

    public String getImatge() {
        return imatge;
    }

    public boolean isPrivat() {
        return privat;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public void setPrivat(boolean privat) {
        this.privat = privat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
