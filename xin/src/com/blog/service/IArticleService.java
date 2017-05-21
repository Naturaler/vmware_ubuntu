package com.blog.service;

import com.blog.entity.Article;

import java.util.List;

/**
 * Created by xin on 17-5-14.
 */
public interface IArticleService {
    List<Article> listArticles();

    List<Article> listByPagination(Integer pagination);

    Integer getSumPagination();

    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticleById(Integer id);
}
