package com.vincler.jf.projet5.models;

import java.util.List;

public class ArticlesListResponse implements ArticlesResponse<Article> {

    public List<Article> results;

    @Override
    public List<Article> getResults() {
        return results;
    }
}


