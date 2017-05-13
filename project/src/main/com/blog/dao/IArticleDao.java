package com.blog.dao;

import com.blog.entity.Article;

import java.util.List;

/**
 * Created by xin on 17-5-13.
 */
public interface IArticleDao {
    List<Article> listArticles();
}
