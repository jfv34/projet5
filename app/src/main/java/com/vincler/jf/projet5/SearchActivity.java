package com.vincler.jf.projet5;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.vincler.jf.projet5.models.ArrowClicked;
import com.vincler.jf.projet5.models.ArticlesSearchResponse;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    SearchActivityPresenter presenter = new SearchActivityPresenter();
    Toolbar toolbar;
    ArrowClicked arrowClicked;
    Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        configureToolbar("Search");


        ImageButton leftArrowBt = findViewById(R.id.activity_search_arrowdown_left_bt);
        leftArrowBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrowClicked = ArrowClicked.LEFT;
                datePiker();
            }
        });

        ImageButton rightArrowBt = findViewById(R.id.activity_search_arrowdown_right_bt);
        rightArrowBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowClicked = ArrowClicked.RIGHT;
                datePiker();
            }
        });

        Button searchBt = findViewById(R.id.activity_search_button);
        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = findViewById(R.id.activity_search_query);
                final CheckBox arts_check = findViewById(R.id.activity_search_checkbox_1);
                final CheckBox business_check = findViewById(R.id.activity_search_checkbox_2);
                final CheckBox entrepreneurs_check = findViewById(R.id.activity_search_checkbox_3);
                final CheckBox politics_check = findViewById(R.id.activity_search_checkbox_4);
                final CheckBox sports_check = findViewById(R.id.activity_search_checkbox_5);
                final CheckBox travels_check = findViewById(R.id.activity_search_checkbox_6);


                presenter.search(editText.getText().toString(), arts_check.isChecked(), business_check.isChecked(),
                        entrepreneurs_check.isChecked(), politics_check.isChecked(), sports_check.isChecked(),
                        travels_check.isChecked(), new Callback<ArticlesSearchResponse>() {
                            @Override
                            public void onResponse(Call<ArticlesSearchResponse> call, Response<ArticlesSearchResponse> response) {

                                if (response.body() != null) {


                                    Intent intent = new Intent(SearchActivity.this, ResultSearchActivity.class);
                                    Bundle b = new Bundle();
                                    b.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) response.body().getResults()); //Your id
                                    intent.putExtras(b); //Put your id to your next Intent
                                    startActivity(intent);

                                }

                            }

                            @Override
                            public void onFailure(Call<ArticlesSearchResponse> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                byte error = presenter.getError();
                if (error != 0) {
                    toast(error);
                }
            }


        });
    }

    void configureToolbar(String activity) {
        if (activity.equals("Search")) {
            this.toolbar = (findViewById(R.id.activity_search_toolbar));
            toolbar.setTitle(R.string.searchArticles);
        } else {
            this.toolbar = (findViewById(R.id.activity_notifications_toolbar));
            toolbar.setTitle("Notifications");
        }

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void displayDate(ArrowClicked arrowClicked) {

        if (arrowClicked == ArrowClicked.RIGHT) {
            final TextView dateEndTV = findViewById(R.id.activity_search_date_end);
            String dateEndDisplayed = presenter.getDateEndDisplayed();
            dateEndTV.setText(dateEndDisplayed);
        }
        if (arrowClicked == ArrowClicked.LEFT) {
            final TextView dateBeginTV = findViewById(R.id.activity_search_date_begin);
            String dateBeginDisplayed = presenter.getDateBeginDisplayed();
            dateBeginTV.setText(dateBeginDisplayed);

        }
        byte error = presenter.getError();
        if (error == 1 | error == 2) {
            toast(error);
        }
    }

    public void toast(int message) {

        switch (message) {
            case (1):
                toast(R.string.errorDate1);
                break;
            case (2):
                toast(R.string.errorDate2);
                break;
            case (3):
                toast(R.string.enterAtLeastOneKeyWord);
                break;
            case (4):
                toast(R.string.checkAtLeastOneCategory);
                break;
            case (5):
                toast(R.string.stopNotifications);
                break;
            case (6):
                toast(R.string.startNotifications);
                break;
            default:
                if (toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(this, getString(message), Toast.LENGTH_LONG);
                toast.show();
        }
    }

    public String gettingQuery() {

        EditText editText = findViewById(R.id.activity_search_query);
        return editText.getText().toString();
    }

    private void datePiker() {

        DatePickerDialog dialog = new DatePickerDialog(this, this, 2019, 0, 1);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int yearDP, int monthOfYear, int dayOfMonth) {

        presenter.dates(yearDP, monthOfYear, dayOfMonth, arrowClicked);
        displayDate(arrowClicked);

    }

    @Override
    public void onClick(View v) {
    }
}


