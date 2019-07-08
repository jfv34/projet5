package com.vincler.jf.projet5.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ArticleMedia implements Parcelable {


    @SerializedName("url")
    public String url;

    protected ArticleMedia(Parcel in) {
        url = in.readString();
    }

    public static final Creator<ArticleMedia> CREATOR = new Creator<ArticleMedia>() {
        @Override
        public ArticleMedia createFromParcel(Parcel in) {
            return new ArticleMedia(in);
        }

        @Override
        public ArticleMedia[] newArray(int size) {
            return new ArticleMedia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);

    }
}
