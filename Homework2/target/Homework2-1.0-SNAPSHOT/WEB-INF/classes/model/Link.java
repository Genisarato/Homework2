/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

/**
 *
 * @author USUARIO
 */

public class Link {
    
    private long id;
    
    private String lastArticle;
    
    public Link(){
    }
    
    public void setLastArticle(int id){
        lastArticle = "/article/" + id;
    }
    
    public String getLastArticle(){
        return lastArticle;
    }
}

