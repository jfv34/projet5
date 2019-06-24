package com.vincler.jf.projet5;

import android.annotation.SuppressLint;
import android.widget.CheckBox;

import com.vincler.jf.projet5.data.NewsService;
import com.vincler.jf.projet5.models.ArrowClicked;
import com.vincler.jf.projet5.models.ArticlesSearchResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivityPresenter {
    public static Response<ArticlesSearchResponse> resultSearch;

    private String dateBeginFormatAPI = "";
    private String dateEndFormatAPI = "";
    private String dateBeginDisplayed = "";
    private String dateEndDisplayed = "";
    private byte error = 0;

    public static Response<ArticlesSearchResponse> getResultSearch() {
        return resultSearch;
    }

    public String getDateBeginDisplayed() {
        if (dateBeginDisplayed.isEmpty()) {
            return "";
        }
        return dateBeginDisplayed;
    }

    public String getDateEndDisplayed() {
        if (dateEndDisplayed.isEmpty()) {
            return "";
        }
        return dateEndDisplayed;
    }

    public byte getError() {
        return error;
    }

    public void setDateBeginFormatAPI(String dateBeginFormatAPI) {
        this.dateBeginFormatAPI = dateBeginFormatAPI;
    }

    public void setDateEndFormatAPI(String dateEndFormatAPI) {
        this.dateEndFormatAPI = dateEndFormatAPI;
    }

    public String getDateBeginFormatAPI() {
        if (dateBeginFormatAPI == null | dateBeginFormatAPI.isEmpty()) {
            return null;
        }
        return dateBeginFormatAPI;
    }

    public String getDateEndFormatAPI() {
        if (dateEndFormatAPI == null | dateEndFormatAPI.isEmpty()) {
            return null;
        }
        return dateEndFormatAPI;
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


    private String dateToday() {
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }

    public void search(String query, CheckBox arts_check,
                       CheckBox business_check, CheckBox entrepreneurs_check, CheckBox politics_check,
                       CheckBox sports_check, CheckBox travels_check) {

        error = 0;
        if (query.isEmpty()) {
            error = 3;
        } else {
            String categories = selectCategories(arts_check, business_check, entrepreneurs_check,
                    politics_check, sports_check, travels_check);
            if (categories.equals("news_desk:()")) {
                error = 4;
            } else {
                error = verifyDates();
                dateBeginFormatAPI = getDateBeginFormatAPI();
                dateEndFormatAPI = getDateEndFormatAPI();

                if (error == 0) {
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

    public void dates(int yearDP, int monthOfYear, int dayOfMonth, ArrowClicked arrowClicked) {
        String year = "", month = "", day = "";
        year = String.valueOf(yearDP);
        monthOfYear++;
        if (monthOfYear < 10) {
            month = "0";
        }
        month = month + (monthOfYear);
        if (dayOfMonth < 10) {
            day = "0";
        }
        day = day + dayOfMonth;
        String dateDisplayed = day + "/" + month + "/" + year;

        if (arrowClicked == ArrowClicked.LEFT) {
            dateBeginDisplayed = dateDisplayed;
            dateBeginFormatAPI = year + month + day;

        } else {
            dateEndDisplayed = dateDisplayed;
            dateEndFormatAPI = year + month + day;

        }
        error = verifyDates();
    }

    public byte verifyDates() {


        if (!dateBeginFormatAPI.isEmpty() && !dateEndFormatAPI.isEmpty()
                && Integer.valueOf(dateBeginFormatAPI) > Integer.valueOf(dateEndFormatAPI)) {
            return 1;
        }

        String dateTodayFormatAPI = dateToday();
        if (!dateBeginFormatAPI.isEmpty() && Integer.valueOf(dateBeginFormatAPI) > Integer.valueOf(dateTodayFormatAPI)) {
            return  2;
        }
        if (!dateEndFormatAPI.isEmpty() && Integer.valueOf(dateEndFormatAPI) > Integer.valueOf(dateTodayFormatAPI)) {
            return  2;
        }
        return 0;
    }
}

