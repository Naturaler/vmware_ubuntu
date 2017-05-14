package com.blog.dto;

import com.blog.entity.Article;

/**
 * Created by xin on 17-5-14.
 */
public class ArticleDto implements Dto {
    private Article article;

    @Override
    public String toString() {
        return "ArticleDto{" +
                "article=" + article +
                '}';
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
