package com.vincler.jf.projet5.models;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Article extends Listable {

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

    @SerializedName("geo_facet")
    public List<String> test;

    @Override
    public String getCover() {
        if (multimedia !=null && multimedia.size() > 0) {
            String imageStr = multimedia.get(0).url;
         return imageStr;
        }
        return null;
    }


}
