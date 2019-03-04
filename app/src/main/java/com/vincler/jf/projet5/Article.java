package com.vincler.jf.projet5;

import java.util.Date;

public class Article {
    String picture;
    String title;
    Date date;
    String text;
    ArticleCategory category;


    public Article(String picture, String title, Date date, String text, ArticleCategory category) {
        this.picture = picture;
        this.title = title;
        this.date = date;
        this.text = text;
        this.category = category;
    }
}
