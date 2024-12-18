/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import java.util.List;
import deim.urv.cat.homework2.model.ArticleResposta;
import deim.urv.cat.homework2.model.Article;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author USUARIO
 */
public interface ArticleService {
    public List<ArticleResposta> getByTopicAndUser(long authorId, long... topicsId);
    public List<ArticleResposta> getAllArticle();
    public ArticleResposta getArticleById(int id);
    public int crearArticle(Article nou);
}
