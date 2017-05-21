package com.blog.service.impl;

import com.blog.dao.IArticleDao;
import com.blog.entity.Article;
import com.blog.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xin on 17-5-14.
 */
@Service
public class ArticleService implements IArticleService {
    @Resource(name = "articleDao")
    private IArticleDao articleDao;

    public List<Article> listArticles() {
        return articleDao.listArticles();
    }

    @Override
    public List<Article> listByPagination(Integer pagination) {
        return articleDao.listByPagination(pagination);
    }

    @Override
    public Integer getSumPagination() {
        return articleDao.getSumPagination();
    }

    public void addArticle(Article article) {
        articleDao.add(article);
    }

    public void updateArticle(Article article) {
        articleDao.update(article);
    }

    public void deleteArticleById(Integer id) {
        articleDao.delete(id);
    }
}
