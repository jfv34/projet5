package com.vincler.jf.projet5;

import android.annotation.SuppressLint;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.vincler.jf.projet5.data.NewsService;
import com.vincler.jf.projet5.models.ArticleSearch;
import com.vincler.jf.projet5.models.ArticlesSearchResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NotificationsActivityPresenter {

    private final int REPEAT_INTERVAL_NOTIFICATIONS = 24;
    private String query;
    private String categories;
    private byte error = 0;

    final PeriodicWorkRequest periodicWorkRequest =
            new PeriodicWorkRequest.Builder(NotificationsWorker.class, REPEAT_INTERVAL_NOTIFICATIONS, TimeUnit.HOURS)
                    .addTag("periodic_work")
                    .build();

    public byte getError() {
        return error;
    }

    public String getCategories() {
        return categories;
    }

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

                    List<ArticleSearch> articleSearch24hours = selectArticles(response);
                }
            }

            @Override
            public void onFailure(Call<ArticlesSearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public List<ArticleSearch> selectArticles(Response<ArticlesSearchResponse> response) {
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
        return articleSearch24hours;
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

    public String selectCategories(boolean arts_check, boolean business_check, boolean entrepreneurs_check,
                                   boolean politics_check, boolean sports_check, boolean travels_check) {
        StringBuilder txtSearch = new StringBuilder();
        txtSearch.append("news_desk:(");

        if (arts_check) {
            txtSearch.append("\"arts\"");
        }
        if (business_check) {
            txtSearch.append("\"business\"");
        }
        if (entrepreneurs_check) {
            txtSearch.append("\"entrepreneurs\"");
        }
        if (politics_check) {
            txtSearch.append("\"politics\"");
        }
        if (sports_check) {
            txtSearch.append("\"sports\"");
        }
        if (travels_check) {
            txtSearch.append("\"travels\"");
        }

        txtSearch.append(")");

        return txtSearch.toString();
    }


    public void okNotificationsChecked(String query, boolean arts_check,
                                       boolean business_check, boolean entrepreneurs_check, boolean politics_check,
                                       boolean sports_check, boolean travels_check) {

        error = 0;
        if (query.isEmpty()) {
            error = 3;
        } else {
            categories = selectCategories(arts_check, business_check, entrepreneurs_check,
                    politics_check, sports_check, travels_check);
            if (categories.equals("news_desk:()")) {
                error = 4;
            }


        }
    }

    public void sendPeriodicsNotifications() {
        WorkManager.getInstance().cancelAllWork();
        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }

    public void stopNotifications() {
        WorkManager.getInstance().cancelAllWork();
    }
}
