package com.vincler.jf.projet5.models;

import java.util.List;

public class ArticlesSearchResponse implements ArticlesResponse<ArticleSearch>  {

    ArticlesDocs response;


    @Override
    public List<ArticleSearch> getResults() {
        return response.docs;
    }
}
