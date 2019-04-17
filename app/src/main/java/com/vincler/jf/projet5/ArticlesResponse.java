package com.vincler.jf.projet5;

import com.vincler.jf.projet5.models.Article;

import java.util.List;

public class ArticlesResponse {
    List<Article> results;


    public ArticlesResponse(List<Article> results) {
        this.results = results;
    }
}
