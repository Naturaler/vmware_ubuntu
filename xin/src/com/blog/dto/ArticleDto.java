package com.blog.dto;

import com.blog.entity.Article;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xin on 17-5-14.
 */
public class ArticleDto implements Dto {
    @JsonUnwrapped
    private Article article;

    public static List<ArticleDto> getInstances(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setArticle(article);
        return Collections.singletonList(articleDto);
    }

    public static List<ArticleDto> getInstances(List<Article> articles) {
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.forEach(article1 -> articleDtos.add(getInstance(article1)));
        return articleDtos;
    }

    public static ArticleDto getInstance(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setArticle(article);
        return articleDto;
    }

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
