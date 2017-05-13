package com.blog.dao.impl;

import com.blog.dao.IArticleDao;
import com.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xin on 17-5-13.
 */
@Component
public class ArticleDao implements IArticleDao {
    private final String ARTICLE_TABLE = "article";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Article> listArticles() {
        final String sql = "SELECT * FROM " + ARTICLE_TABLE;
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    private class ArticleRowMapper implements RowMapper<Article> {
        public Article mapRow(ResultSet resultSet, int i) throws SQLException {
            Article article = new Article();
            article.setTitle(resultSet.getString("title"));
            article.setContent(resultSet.getString("content"));
            article.setAuthor(resultSet.getString("author"));
            article.setType(resultSet.getString("type"));
            article.setCraeteDatetime(resultSet.getDate("create_datetime"));
            return article;
        }
    }
}
