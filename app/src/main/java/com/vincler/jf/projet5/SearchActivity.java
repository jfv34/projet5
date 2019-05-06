package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {
    int categoriesSelected[] = new int[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Button searchBt = findViewById(R.id.activity_search_button);
        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = gettingQuery();
                selectCategories();

                StringBuilder txtSearch = new StringBuilder();
                txtSearch.append("search/v2/articlesearch.json?");


                if (categoriesSelected[0] == 1) {
                    txtSearch.append("q=arts&");
                }
                if (categoriesSelected[1] == 1) {
                    txtSearch.append("q=business&");
                }
                if (categoriesSelected[2] == 1) {
                    txtSearch.append("q=entrepreneurs&");
                }
                if (categoriesSelected[3] == 1) {
                    txtSearch.append("q=politics&");
                }
                if (categoriesSelected[4] == 1) {
                    txtSearch.append("q=sports&");
                }
                if (categoriesSelected[5] == 1) {
                    txtSearch.append("q=travels&");
                }

                txtSearch.append("api-key=jGQidx72NOVdW62AOG2f61ITRG2Gmsbx");


            }
        });

        ImageButton leftArrowBt = findViewById(R.id.activity_search_arrowdown_left_bt);
        leftArrowBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();

            }
        });

        ImageButton rightArrowBt = findViewById(R.id.activity_search_arrowdown_right_bt);
        rightArrowBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
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


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This is AlertDialog");
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}



