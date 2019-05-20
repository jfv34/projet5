package com.vincler.jf.projet5.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Article extends Listable {
    public Article(List<ArticleMedia> multimedia, String title, Date date, String subCategory, String category, String url) {
        this.multimedia = multimedia;
        this.title = title;
        this.date = date;
        this.subCategory = subCategory;
        this.category = category;
        this.url = url;
    }

    @SerializedName("multimedia")
    public List<ArticleMedia> multimedia;

    @SerializedName("title")
    public String title;

    @SerializedName("published_date")
    public Date date;

    @SerializedName("subsection")
    public String subCategory;

    @SerializedName("section")
    public String category;

    @SerializedName("url")
    public String url;

    @Override
    public String getCover() {
        if (multimedia != null && multimedia.size() > 0) {
            return multimedia.get(0).url;
        }
        return null;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subCategory;
    }

    public String getUrl() {
        return url;
    }


}
