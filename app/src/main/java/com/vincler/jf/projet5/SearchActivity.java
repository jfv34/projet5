package com.vincler.jf.projet5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class SearchActivity extends AppCompatActivity {

    public static Response<ArticlesSearchResponse> resultSearch;
    private Context context;
    String dateBegin = "", dateEnd = "", dateFormatAPI = "", dateDisplayed, query = "";
    String dateBeginFormatApi = "20190101", dateEndFormatAPI = "20190101";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        datePikerButton();
        search();
    }

    private void search() {

        Button searchBt = findViewById(R.id.activity_search_button);
        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = gettingQuery();

                EditText beginDateET = findViewById(R.id.activity_search_date_begin);
                EditText endDateET = findViewById(R.id.activity_search_date_end);
                dateBeginFormatApi = formatDate(beginDateET.getText());
                dateEndFormatAPI = formatDate(endDateET.getText());
                if (dateBeginFormatApi.equals("bad_date") | dateEndFormatAPI.equals("bad_date")) {
                    toast(R.string.badFormatDate);
                } else {

                    if (query.isEmpty()) {
                        toast(R.string.enterAtLeastOneKeyWord);
                    } else {
                        String categories = selectCategories();

                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        OkHttpClient.Builder builder = UnsafeOkHttpClient.getUnsafeOkHttpClient().addInterceptor(interceptor);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://api.nytimes.com/svc/")
                                .client(builder.build())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        final NewsService service = retrofit.create(NewsService.class);
                        service.listSearch(query, categories, dateBeginFormatApi, dateEndFormatAPI).enqueue(new Callback<ArticlesSearchResponse>() {
                            @Override
                            public void onResponse(Call<ArticlesSearchResponse> call, Response<ArticlesSearchResponse> response) {

                                resultSearch = response;
                                context = SearchActivity.this;
                                Intent intent = new Intent(context, ResultSearchActivity.class);
                                context.startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<ArticlesSearchResponse> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }
                }
            }
        });
    }

    private String formatDate(Editable editable) {

        String textDate = editable.toString();
        if (textDate.length() != 10) {
            return "bad_date";
        }
        if (!textDate.substring(2, 3).equals("/") | !textDate.substring(5, 6).equals("/")) {
            return "bad_date";

        }
        return textDate.substring(6, 10) + textDate.substring(3, 5) + textDate.substring(0, 2);
    }

    public void toast(int text) {
        context = getApplicationContext();
        Toast.makeText(context, context.getString(text), Toast.LENGTH_SHORT).show();
    }

    private void datePikerButton() {
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

    public String selectCategories() {

        final CheckBox arts_check = findViewById(R.id.activity_search_checkbox_1);
        final CheckBox business_check = findViewById(R.id.activity_search_checkbox_2);
        final CheckBox entrepreneurs_check = findViewById(R.id.activity_search_checkbox_3);
        final CheckBox politics_check = findViewById(R.id.activity_search_checkbox_4);
        final CheckBox sports_check = findViewById(R.id.activity_search_checkbox_5);
        final CheckBox travels_check = findViewById(R.id.activity_search_checkbox_6);


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

        // if no categories selected
        if (txtSearch.substring(txtSearch.length() - 2) == "(") {
            txtSearch.delete(txtSearch.length() - 1, txtSearch.length());
            txtSearch.append("\"\")");
        }

        return txtSearch.toString();
    }

    public String gettingQuery() {

        String txt;
        EditText editText = findViewById(R.id.activity_search_query);
        txt = editText.getText().toString();
        return txt;
    }


    private void datePiker(final String position) {

        query = gettingQuery();   // save query before change view
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

                EditText editText = findViewById(R.id.activity_search_query);
                editText.setText(query);


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
                    toast(R.string.errorDate1);
                }

                EditText beginDateET = findViewById(R.id.activity_search_date_begin);
                EditText endDateET = findViewById(R.id.activity_search_date_end);
                beginDateET.setText(dateBegin);
                endDateET.setText(dateEnd);

                datePikerButton();
                search();

            }
        });


    }
}