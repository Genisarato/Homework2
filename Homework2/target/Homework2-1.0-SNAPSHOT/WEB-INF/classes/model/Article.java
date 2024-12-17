
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Genis i Jan
 */

public class Article {
    private int id;

    private String titol;
    private String imatge;

    private Usuari autor;

    private int num_views;

    private List<Topic> topics;

    private String descripcio;
    private Date data_publi;

    private boolean privat;
    
    
    public Article() {
    }
    
    public void setId(int id){
        this.id = id;
    }
    

    public void setTitol(String titol) {
        this.titol = titol;
    }
    

    public void setAutor(Usuari Autor) {
        this.autor = Autor;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setData_publi(Date data_publi) {
        this.data_publi = data_publi;
    }

    public int getId() {
        return id;
    }

    public String getTitol() {
        return titol;
    }

    public Usuari getAutor() {
        return autor;
    }

    public int getNum_views() {
        return num_views;
    }
    
    public List<Topic> getTopics() {
        if(topics == null) return new ArrayList<>();
        else return topics;
    }

    public Date getData_publi() {
        return data_publi;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public String getDescripcio() {
        return descripcio;
    }


    public void setNum_views(int num_views) {
        this.num_views = num_views;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public boolean isPrivat() {
        return privat;
    }

    public void setPrivat(boolean privat) {
        this.privat = privat;
    }
    
    public void sumarViews(){
        num_views++;
    }
}
