package com.vincler.jf.projet5;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.CheckBox;

import com.vincler.jf.projet5.data.NewsService;
import com.vincler.jf.projet5.models.ArticleSearch;
import com.vincler.jf.projet5.models.ArticlesSearchResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NotificationsPresenter {


    private String query;
    private String categories;

    public void retrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = UnsafeOkHttpClient.getUnsafeOkHttpClient().addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nytimes.com/svc/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final NewsService service = retrofit.create(NewsService.class);


        String dateBeginFormatAPI = dateYesterday();
        String dateEndFormatAPI = dateToday();
        service.listSearch(query, categories, dateBeginFormatAPI, dateEndFormatAPI).enqueue(new Callback<ArticlesSearchResponse>() {

            @Override
            public void onResponse(Call<ArticlesSearchResponse> call, Response<ArticlesSearchResponse> response) {

                if (!response.body().getResults().isEmpty()) {

                    List<ArticleSearch> articleSearch = response.body().getResults();
                    List<ArticleSearch> articleSearch24hours = new ArrayList<>();
                    long dateTodayMillisecond = new Date().getTime();
                    long dateArticleMillisecond;
                    long periode;

                    for (int i = 0; i < articleSearch.size() - 1; i++) {

                        dateArticleMillisecond = articleSearch.get(i).date.getTime();
                        periode = dateTodayMillisecond - dateArticleMillisecond;
                        if (periode < 86400000) {

                            articleSearch24hours.add(articleSearch.get(i));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticlesSearchResponse> call, Throwable t) {
                Log.d("TAG-error", "10");
                t.printStackTrace();
            }
        });
    }

    private String dateYesterday() {

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }


    private String dateToday() {

        Date date = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }

    public void data(String query, String categories) {
        this.query = query;
        this.categories = categories;
    }

    public String selectCategories(CheckBox arts_check, CheckBox business_check, CheckBox entrepreneurs_check,
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


}
