package com.vincler.jf.projet5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    SearchActivityPresenter presenter = new SearchActivityPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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

        Button searchBt = findViewById(R.id.activity_search_button);
        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText beginDateET = findViewById(R.id.activity_search_date_begin);
                EditText endDateET = findViewById(R.id.activity_search_date_end);
                EditText editText = findViewById(R.id.activity_search_query);
                final CheckBox arts_check = findViewById(R.id.activity_search_checkbox_1);
                final CheckBox business_check = findViewById(R.id.activity_search_checkbox_2);
                final CheckBox entrepreneurs_check = findViewById(R.id.activity_search_checkbox_3);
                final CheckBox politics_check = findViewById(R.id.activity_search_checkbox_4);
                final CheckBox sports_check = findViewById(R.id.activity_search_checkbox_5);
                final CheckBox travels_check = findViewById(R.id.activity_search_checkbox_6);


                presenter.search(beginDateET.getText().toString(), endDateET.getText().toString(),
                        editText.getText().toString(), arts_check, business_check, entrepreneurs_check,
                        politics_check, sports_check, travels_check);

                Intent intent = new Intent(SearchActivity.this, ResultSearchActivity.class);
                intent.putExtra("source", "SearchActivity");
                startActivity(intent);

            }

        });
    }


    public void toast(int text) {
        Toast.makeText(this, getString(text), Toast.LENGTH_LONG).show();
    }


    public String gettingQuery() {

        String txt;
        EditText editText = findViewById(R.id.activity_search_query);
        txt = editText.getText().toString();
        return txt;
    }


    private void datePiker(final String position) {

        /*EditText edittext = (EditText) findViewById(R.id.datePicker_editText);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        edittext = (EditText) findViewById(R.id.datePicker_editText);
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SearchActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

       /* query = gettingQuery();   // save query before change view
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


                if (position.equals("LEFT")) {
                    dateBegin = dateDisplayed;
                    dateBeginFormatAPI = dateFormatAPI;
                }
                if (position.equals("RIGHT")) {
                    dateEnd = dateDisplayed;
                    dateEndFormatAPI = dateFormatAPI;
                }


                if (!dateBeginFormatAPI.isEmpty() && !dateEndFormatAPI.isEmpty() && Integer.valueOf(dateBeginFormatAPI) > Integer.valueOf(dateEndFormatAPI)) {
                    dateBegin = dateEnd;
                    dateBeginFormatAPI = dateEndFormatAPI;
                    toast(R.string.errorDate1);
                }

                if (!dateBeginFormatAPI.isEmpty() && Integer.valueOf(dateBeginFormatAPI) > Integer.valueOf(dateTodayFormatAPI)) {
                    dateBegin = dateEnd;
                    dateBeginFormatAPI = dateEndFormatAPI;
                    toast(R.string.errorDate2);
                }

                if (!dateEndFormatAPI.isEmpty() && Integer.valueOf(dateEndFormatAPI) > Integer.valueOf(dateTodayFormatAPI)) {
                    dateEnd = dateBegin;
                    dateEndFormatAPI = dateBeginFormatAPI;
                    toast(R.string.errorDate2);
                }


                EditText beginDateET = findViewById(R.id.activity_search_date_begin);
                EditText endDateET = findViewById(R.id.activity_search_date_end);

                beginDateET.setText(dateBegin);
                endDateET.setText(dateEnd);

                datePikerButton();
                search();

            }
        });*/


    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        EditText edittext = (EditText) findViewById(R.id.datePicker_editText);
        //edittext.setText(sdf.format(myCalendar.getTime()));
    }
}