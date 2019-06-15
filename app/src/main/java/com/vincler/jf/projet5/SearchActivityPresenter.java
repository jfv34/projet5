package com.vincler.jf.projet5;

import android.annotation.SuppressLint;
import android.widget.CheckBox;

import com.vincler.jf.projet5.data.NewsService;
import com.vincler.jf.projet5.models.ArticlesSearchResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivityPresenter {
    public static Response<ArticlesSearchResponse> resultSearch;

    private String query = "";
    private String dateBeginFormatAPI = "";
    private String dateEndFormatAPI = "";
    private String categories = "";

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDateBeginFormatAPI() {
        if (dateBeginFormatAPI.isEmpty()) {
            return null;
        }
        return dateBeginFormatAPI;
    }

    public void setDateBeginFormatAPI(String dateBeginFormatAPI) {
        this.dateBeginFormatAPI = dateBeginFormatAPI;
    }

    public String getDateEndFormatAPI() {
        if (dateEndFormatAPI.isEmpty()) {
            return null;
        }
        return dateEndFormatAPI;
    }

    public void setDateEndFormatAPI(String dateEndFormatAPI) {
        this.dateEndFormatAPI = dateEndFormatAPI;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    final NewsService service = new Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/")
            .client(UnsafeOkHttpClient
                    .getUnsafeOkHttpClient()
                    .addInterceptor(
                            new HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsService.class);
    final Calendar myCalendar = Calendar.getInstance();


    private String dateToday() {
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }

    public void search(String beginDate, String endDate, String query, CheckBox arts_check,
                       CheckBox business_check, CheckBox entrepreneurs_check, CheckBox politics_check,
                       CheckBox sports_check, CheckBox travels_check) {

        String dateBeginFormatAPI = formatDate(beginDate);
        String dateEndFormatAPI = formatDate(endDate);


        if (dateBeginFormatAPI.equals("bad_date") | dateEndFormatAPI.equals("bad_date")) {
            //toast(R.string.badFormatDate);
        } else {

            if (query.isEmpty()) {
                //toast(R.string.enterAtLeastOneKeyWord);
            } else {

                categories = selectCategories(arts_check, business_check, entrepreneurs_check,
                        politics_check, sports_check, travels_check);

                if (categories.equals("news_desk:()")) {
                    //toast(R.string.checkAtLeastOneCategory);
                } else {

                    //ifDateEmpty();


                    service.listSearch(query, categories, dateBeginFormatAPI, dateEndFormatAPI).enqueue(new Callback<ArticlesSearchResponse>() {
                        @Override
                        public void onResponse(Call<ArticlesSearchResponse> call, Response<ArticlesSearchResponse> response) {


                            resultSearch = response;


                        }

                        @Override
                        public void onFailure(Call<ArticlesSearchResponse> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            }
        }
    }

    private String selectCategories(CheckBox arts_check, CheckBox business_check, CheckBox entrepreneurs_check,
                                    CheckBox politics_check, CheckBox sports_check, CheckBox travels_check) {
        StringBuilder txtSearch = new StringBuilder();
        txtSearch.append("news_desk:(");

        if (arts_check.isChecked()) {
            txtSearch.append("\"arts\"");
        }
        if (business_check.isChecked()) {
            txtSearch.append("\"business\"");
        }
        if (entrepreneurs_check.isChecked()) {
            txtSearch.append("\"entrepreneurs\"");
        }
        if (politics_check.isChecked()) {
            txtSearch.append("\"politics\"");
        }
        if (sports_check.isChecked()) {
            txtSearch.append("\"sports\"");
        }
        if (travels_check.isChecked()) {
            txtSearch.append("\"travels\"");
        }

        txtSearch.append(")");

        return txtSearch.toString();
    }

    private String formatDate(String textDate) {

        if (textDate.isEmpty()) {
            return "";
        }
        if (textDate.length() != 10) {
            return "bad_date";
        }
        if (!textDate.substring(2, 3).equals("/") | !textDate.substring(5, 6).equals("/")) {
            return "bad_date";

        }
        return textDate.substring(6, 10) + textDate.substring(3, 5) + textDate.substring(0, 2);
    }


}
