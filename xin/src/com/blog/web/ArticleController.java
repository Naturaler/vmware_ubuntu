package com.blog.web;

import com.blog.dto.ArticleDto;
import com.blog.dto.DtoWrapper;
import com.blog.dto.ResponseDto;
import com.blog.entity.Article;
import com.blog.exception.IllegalArgumentException;
import com.blog.global.StatusFactory;
import com.blog.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

import static com.blog.global.StatusFactory.STATUS_SUCCESS;

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
        responseDto.setStatus(StatusFactory.getStatusByCode(STATUS_SUCCESS));
        responseDto.setData(ArticleDto.getInstances(articleService.listArticles()));
        logger.info("responseDto" + responseDto.toString());
        return responseDto;
    }

    @GetMapping("/listByPagination")
    public ResponseDto listByPagination(@RequestParam Integer pagination) {
        if (pagination < 1) {
            throw new IllegalArgumentException("illegal argument exception");
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(StatusFactory.getStatusByCode(STATUS_SUCCESS));
        responseDto.setData(ArticleDto.getInstances(articleService.listByPagination(pagination)));
        return responseDto;
    }

    @GetMapping("/getSumPagination")
    public ResponseDto getSumPagination() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(StatusFactory.getStatusByCode(STATUS_SUCCESS));
        responseDto.setData(DtoWrapper.getInstances(articleService.getSumPagination()));
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

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String illegalArgumentError(IllegalArgumentException e) {
        return e.getMessage();
    }
}
