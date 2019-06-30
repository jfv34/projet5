package com.vincler.jf.projet5;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ArticleParcelable implements Parcelable {
    private List result;


    public ArticleParcelable(List results) {
        this.result = results;
    }


    public static final Creator<ArticleParcelable> CREATOR = new Creator<ArticleParcelable>() {
        @Override
        public ArticleParcelable createFromParcel(Parcel in) {
            // in.readList(result);
            // return new ArticleParcelable(in);
            return null;

        }

        @Override
        public ArticleParcelable[] newArray(int size) {
            return new ArticleParcelable[size];
        }
    };

    public List getResponse() {
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeList(result);


    }
}
