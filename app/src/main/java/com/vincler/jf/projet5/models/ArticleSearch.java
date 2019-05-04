package com.vincler.jf.projet5.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ArticleSearch extends Listable {

    @SerializedName("multimedia")
    public List<ArticleMedia> multimedia;

    @SerializedName("snippet")
    public String title;

    @SerializedName("pub_date")
    public Date date;

    @SerializedName("subsection_name")
    public String subCategory;

    @SerializedName("section_name")
    public String category;

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


}
