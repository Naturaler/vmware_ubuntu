package com.blog.dao.impl;

import com.blog.dao.IArticleDao;
import com.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xin on 17-5-13.
 */
@Repository
public class ArticleDao implements IArticleDao {
    private final String ARTICLE_TABLE = "article";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Article> listArticles() {
        final String sql = "SELECT * FROM " + ARTICLE_TABLE;
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    @Override
    public List<Article> listByPagination(Integer pagination) {
        final int index = (pagination - 1) * 10;
        final String sql = "SELECT * FROM " + ARTICLE_TABLE + " LIMIT ?,10";
        return jdbcTemplate.query(sql, new ArticleRowMapper(), index);
    }

    @Override
    public Integer getSumPagination() {
        // 向上取整（每页固定显示10篇文章）
        final String sql = "SELECT ceil(count(title)/10) FROM " + ARTICLE_TABLE;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Article getArticleById(Integer id) {
        final String sql = "SELECT * FROM " + ARTICLE_TABLE + " WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ArticleRowMapper(), id);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer countArticles() {
        final String sql = "SELECT COUNT(title) FROM " + ARTICLE_TABLE;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void add(Article article) {
        final String sql = "INSERT INTO " + ARTICLE_TABLE +
                "(title, content, author, type, create_datetime, update_datetime) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getAuthor(),
                article.getType(), article.getCreateDatetime(), article.getUpdateDatetime());
    }

    public void update(Article article) {
        final String sql = "UPDATE " + ARTICLE_TABLE + " SET title = ?, content = ?, author = ?, " +
                "type = ?, update_datetime = ?";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getAuthor(),
                article.getType(), article.getUpdateDatetime());
    }

    public void delete(Integer id) {
        final String sql = "DELETE FROM " + ARTICLE_TABLE + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private class ArticleRowMapper implements RowMapper<Article> {
        public Article mapRow(ResultSet resultSet, int i) throws SQLException {
            Article article = new Article();
            article.setId(resultSet.getInt("id"));
            article.setTitle(resultSet.getString("title"));
            article.setContent(resultSet.getString("content"));
            article.setAuthor(resultSet.getString("author"));
            article.setType(resultSet.getString("type"));
            article.setCreateDatetime(resultSet.getDate("create_datetime"));
            article.setUpdateDatetime(resultSet.getDate("update_datetime"));
            return article;
        }
    }
}
