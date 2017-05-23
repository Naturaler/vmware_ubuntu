package com.blog.dao;

import com.blog.entity.Article;

import java.util.List;

/**
 * Created by xin on 17-5-13.
 */
public interface IArticleDao {
    List<Article> listArticles();

    List<Article> listByPagination(Integer pagination);

    Integer getSumPagination();

    Article getArticleById(Integer id);

    Integer countArticles();

    void add(Article article);

    void update(Article article);

    void delete(Integer id);
}