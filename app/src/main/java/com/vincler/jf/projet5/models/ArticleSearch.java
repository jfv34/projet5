package com.vincler.jf.projet5.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ArticleSearch extends Listable implements Parcelable {

    private final Date date_object;

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

    @SerializedName("web_url")
    public String url;


    protected ArticleSearch(Parcel in) {
        // multimedia = new ArticleMedia(in.readString());
        title = in.readString();
        subCategory = in.readString();
        category = in.readString();
        url = in.readString();
        date_object = new Date(in.readLong());


    }

    public static final Creator<ArticleSearch> CREATOR = new Creator<ArticleSearch>() {
        @Override
        public ArticleSearch createFromParcel(Parcel in) {
            return new ArticleSearch(in);
        }

        @Override
        public ArticleSearch[] newArray(int size) {
            return new ArticleSearch[size];
        }
    };

    @Override
    public String getCover() {
        if (multimedia != null && multimedia.size() > 0) {
            String url = multimedia.get(0).url;
            if (url.startsWith("https://static01.nyt.com/")) {
                return url;
            } else {
                return "https://static01.nyt.com/" + url;
            }
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(getCover());
        dest.writeString(title);
        dest.writeLong(date_object.getTime());
        dest.writeString(subCategory);
        dest.writeString(category);
        dest.writeString(url);


    }
}
