package com.blog.web;

import com.blog.dto.ArticleDto;
import com.blog.dto.ResponseDto;
import com.blog.entity.Article;
import com.blog.exception.IllegalArgumentException;
import com.blog.global.StatusFactory;
import com.blog.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by xin on 17-5-13.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    private Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Resource(name = "articleService")
    private IArticleService articleService;

    @GetMapping("/list")
    public ResponseDto listArticles() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(StatusFactory.getStatusByCode(200));
        responseDto.setData(ArticleDto.getINstances(articleService.listArticles()));
        logger.info("responseDto" + responseDto.toString());
        return responseDto;
    }

    @GetMapping("/listByPagination")
    public ResponseDto listByPagination(@RequestParam Integer pagination) {
        if (pagination < 1) {
            throw new IllegalArgumentException("illegal argument exception");
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(StatusFactory.getStatusByCode(200));
        responseDto.setData(ArticleDto.getINstances(articleService.listByPagination(pagination)));
        return responseDto;
    }

    @PostMapping("/add")
    public void addArticle(@RequestBody Article article) {
        article.setCreateDatetime(new Date());
        article.setUpdateDatetime(new Date());
        articleService.addArticle(article);
    }

    @PostMapping("/update")
    public void updateArticle(@RequestBody Article article) {
        article.setUpdateDatetime(new Date());
        articleService.updateArticle(article);
    }

    @GetMapping("/delete")
    public void deleteArticle(@RequestParam Integer id) {
        articleService.deleteArticleById(id);
    }
}
