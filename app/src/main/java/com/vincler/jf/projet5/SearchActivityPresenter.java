package com.vincler.jf.projet5;

import android.annotation.SuppressLint;

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
        if(dateBeginFormatAPI.isEmpty()){return null;}
        return dateBeginFormatAPI;
    }

    public void setDateBeginFormatAPI(String dateBeginFormatAPI) {
        this.dateBeginFormatAPI = dateBeginFormatAPI;
    }

    public String getDateEndFormatAPI() {
        if(dateEndFormatAPI.isEmpty()){return null;}
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

    public void search() {

    /*    EditText beginDateET = findViewById(R.id.activity_search_date_begin);
        EditText endDateET = findViewById(R.id.activity_search_date_end);
        dateBeginFormatAPI = formatDate(beginDateET.getText());
        dateEndFormatAPI = formatDate(endDateET.getText());*/


        if (dateBeginFormatAPI.equals("bad_date") | dateEndFormatAPI.equals("bad_date")) {
            //toast(R.string.badFormatDate);
        } else {

            if (query.isEmpty()) {
                //toast(R.string.enterAtLeastOneKeyWord);
            } else {
                //categories = selectCategories();

                if (categories.equals("news_desk:()")) {
                    //toast(R.string.checkAtLeastOneCategory);
                } else {

                    //ifDateEmpty();


                    service.listSearch(query, categories, dateBeginFormatAPI, dateEndFormatAPI).enqueue(new Callback<ArticlesSearchResponse>() {
                        @Override
                        public void onResponse(Call<ArticlesSearchResponse> call, Response<ArticlesSearchResponse> response) {

                            resultSearch = response;
                /*Intent intent = new Intent(SearchActivity.this, ResultSearchActivity.class);
                intent.putExtra("source", "SearchActivity");
                startActivity(intent);*/
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


}
