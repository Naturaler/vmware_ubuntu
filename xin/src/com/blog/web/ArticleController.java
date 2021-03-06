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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

import static com.blog.global.StatusFactory.STATUS_BADREQUEST;
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

    @GetMapping("/getArticleById")
    public ResponseDto getArticleById(@RequestParam Integer id) {
        ResponseDto responseDto = new ResponseDto();
        if (id < 0) {
            responseDto.setStatus(StatusFactory.getStatusByCode(STATUS_BADREQUEST));
            return responseDto;
        }
        Article article = articleService.getArticleById(id);
        responseDto.setData(DtoWrapper.getInstances(ArticleDto.getInstance(article)));
        responseDto.setStatus(StatusFactory.getStatusByCode(STATUS_SUCCESS));
        return responseDto;
    }

    // 后台不控制页面跳转
    /*@GetMapping("/id")
    public ModelAndView getArticle(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("article");
        Article article = articleService.getArticleById(id);
        // 保存到modelandview中的数据，将会自动保存到request中
        modelAndView.addObject("article", article);
        return modelAndView;
    }*/

    @PostMapping("/add")
    public void addArticle(@RequestBody Article article) {
        logger.info("add article:" + article);
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
