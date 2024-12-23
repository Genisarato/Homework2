/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Genis i Jan
 */

@XmlRootElement
@Entity
public class Article {
    @Id
    @SequenceGenerator(name="Article_gen", allocationSize=1) 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Article_gen")
    private int id;

    private String titol;
    private String imatge;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuari autor;

    private int num_views;

    @ManyToMany
    @JoinTable(name = "Topics_Article",
            joinColumns = @JoinColumn(name = "Article"),
            inverseJoinColumns = @JoinColumn(name = "Topic"))
    @XmlElementWrapper(name = "topics")  // Contenedor para la lista de topics
    @XmlElement(name = "topic")  // Elemento individual para cada topic
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
