package com.vincler.jf.projet5;

public class ArticleCategory {
    String name;
    ArticleCategory parent;

    public ArticleCategory(String name, ArticleCategory parent) {
        this.name = name;
        this.parent = parent;
    }
}
