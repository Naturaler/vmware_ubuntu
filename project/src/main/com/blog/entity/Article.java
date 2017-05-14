package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Created by xin on 17-5-13.
 */
public class Article {
    private String title;
    private String content;
    private String author;
    private String type;
    @JsonIgnore
    private Date craeteDatetime;
    @JsonIgnore
    private Date updateDatetime;

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", craeteDatetime=" + craeteDatetime +
                ", updateDatetime=" + updateDatetime +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCraeteDatetime() {
        return craeteDatetime;
    }

    public void setCraeteDatetime(Date craeteDatetime) {
        this.craeteDatetime = craeteDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
