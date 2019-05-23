package com.vincler.jf.projet5;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.vincler.jf.projet5.data.NewsService;
import com.vincler.jf.projet5.models.ArticlesResponse;
import com.vincler.jf.projet5.models.ArticlesSearchResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity<T extends ArticlesResponse> extends AppCompatActivity {

    int[] categoriesSelected = new int[6];
    String dateBegin = "", dateEnd = "", dateFormatAPI = "", dateDisplayed, query = "";
    String dateBeginFormatApi = "20190101", dateEndFormatAPI = "20190101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        datePiker();
        search();
    }

    private void search() {
        Button searchBt = findViewById(R.id.activity_search_button);
        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = gettingQuery();
                selectCategories();

                StringBuilder txtSearch = new StringBuilder();
                txtSearch.append("news_desk:(");

                if (categoriesSelected[0] == 1) {
                    txtSearch.append("\"arts\"");
                }
                if (categoriesSelected[1] == 1) {
                    txtSearch.append("\"business\"");
                }
                if (categoriesSelected[2] == 1) {
                    txtSearch.append("\"entrepreneurs\"");
                }
                if (categoriesSelected[3] == 1) {
                    txtSearch.append("\"politics\"");
                }
                if (categoriesSelected[4] == 1) {
                    txtSearch.append("\"sports\"");
                }
                if (categoriesSelected[5] == 1) {
                    txtSearch.append("\"travels\"");
                }

                txtSearch.append(")");


                Log.i("TAG-URL", txtSearch.toString());

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient.Builder builder = UnsafeOkHttpClient.getUnsafeOkHttpClient().addInterceptor(interceptor);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.nytimes.com/svc/")
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                NewsService service = retrofit.create(NewsService.class);

                service.listSearch(query, txtSearch.toString(), dateBeginFormatApi, dateEndFormatAPI).enqueue(new Callback<ArticlesSearchResponse>() {
                    @Override
                    public void onResponse(Call<ArticlesSearchResponse> call, Response<ArticlesSearchResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<ArticlesSearchResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });
    }

    private void datePiker() {
        ImageButton leftArrowBt = findViewById(R.id.activity_search_arrowdown_left_bt);
        leftArrowBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePiker("LEFT");
            }
        });

        ImageButton rightArrowBt = findViewById(R.id.activity_search_arrowdown_right_bt);
        rightArrowBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePiker("RIGHT");
            }
        });
    }

    private Void selectCategories() {

        final CheckBox arts_check = findViewById(R.id.activity_search_checkbox_1);
        final CheckBox business_check = findViewById(R.id.activity_search_checkbox_2);
        final CheckBox entrepreneurs_check = findViewById(R.id.activity_search_checkbox_3);
        final CheckBox politics_check = findViewById(R.id.activity_search_checkbox_4);
        final CheckBox sports_check = findViewById(R.id.activity_search_checkbox_5);
        final CheckBox travels_check = findViewById(R.id.activity_search_checkbox_6);


        for (int i = 0; i < 5; i++) {
            categoriesSelected[i] = 0;
        }

        if (arts_check.isChecked()) {
            categoriesSelected[0] = 1;
        }
        if (business_check.isChecked()) {
            categoriesSelected[1] = 1;
        }
        if (entrepreneurs_check.isChecked()) {
            categoriesSelected[2] = 1;
        }
        if (politics_check.isChecked()) {
            categoriesSelected[3] = 1;
        }
        if (sports_check.isChecked()) {
            categoriesSelected[4] = 1;
        }
        if (travels_check.isChecked()) {
            categoriesSelected[5] = 1;
        }


        return null;
    }

    private String gettingQuery() {

        String txt;
        EditText editText = findViewById(R.id.activity_search_query);
        txt = editText.getText().toString();
        return txt;
    }


    private void datePiker(final String position) {

        setContentView(R.layout.datepicker);
        Button button = findViewById(R.id.datePicker_button);
        final DatePicker datePicker = findViewById(R.id.datePicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String year = "", month = "", day = "";
                year = String.valueOf(datePicker.getYear());
                if (datePicker.getMonth() < 9) {
                    month = "0";
                }
                month = month + (datePicker.getMonth() + 1);
                if (datePicker.getDayOfMonth() < 10) {
                    day = "0";
                }
                day = day + datePicker.getDayOfMonth();
                dateFormatAPI = year + month + day;
                dateDisplayed = day + "/" + month + "/" + year;

                setContentView(R.layout.activity_search);
                TextView beginDateTV = findViewById(R.id.activity_search_date_begin);
                TextView endDateTV = findViewById(R.id.activity_search_date_end);

                if (position == "LEFT") {
                    dateBegin = dateDisplayed;
                    dateBeginFormatApi = dateFormatAPI;
                }
                if (position == "RIGHT") {
                    dateEnd = dateDisplayed;
                    dateEndFormatAPI = dateFormatAPI;
                }

                if (dateBegin.isEmpty()) {
                    dateBegin = dateEnd;
                    dateBeginFormatApi = dateEndFormatAPI;
                }
                if (dateEnd.isEmpty()) {
                    dateEnd = dateBegin;
                    dateEndFormatAPI = dateBeginFormatApi;
                }
                if (Integer.valueOf(dateBeginFormatApi) > Integer.valueOf(dateEndFormatAPI)) {
                    dateBegin = dateEnd;
                    dateBeginFormatApi = dateEndFormatAPI;
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, getString(R.string.errorDate1), Toast.LENGTH_LONG);
                    toast.show();
                }


                beginDateTV.setText(dateBegin);
                endDateTV.setText(dateEnd);

                datePiker();
                search();

            }
        });


    }
}