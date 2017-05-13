package com.blog.web;

import com.blog.dao.IArticleDao;
import com.blog.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xin on 17-5-13.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    private Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Resource(name = "articleDao")
    private IArticleDao articleDao;

    @GetMapping("/list")
    public List<Article> listArticles() {
        logger.info("== info list articles ==");
        logger.debug("== debug list articles ==");
        return articleDao.listArticles();
    }
}
