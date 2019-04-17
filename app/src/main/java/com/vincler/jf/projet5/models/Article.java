package com.vincler.jf.projet5.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Article {
    @SerializedName("multimedia")
    public List<ArticleMedia> medias;

    @SerializedName("title")
    public String title;

    @SerializedName("published_date")
    public Date date;

    @SerializedName("subsection")
    public String subCategory;

    @SerializedName("section")
    public String category;

    public Article(List<ArticleMedia> medias, String title, Date date, String subCategory, String category) {
        this.medias = medias;
        this.title = title;
        this.date = date;
        this.subCategory = subCategory;
        this.category = category;
    }
}
