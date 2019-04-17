package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.Date;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String query = gettingQuery();
        Date dateBegin = gettingDateBegin();
        Date dateEnd = gettingDateEnd();


    }

    private String gettingQuery() {

        String query = null;
      /*  Button button = findViewById(R.id.activity_search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                EditText editText = findViewById(R.id.activity_search_query);
                query= editText.getText().toString();
            }
        });*/
        return query;

    }

    private Date gettingDateBegin() {

        Date dateBegin = new Date();
        ImageButton imageButton = findViewById(R.id.activity_search_arrowdown_left_bt);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return dateBegin;
    }

    private Date gettingDateEnd() {

        Date dateEnd = new Date();
        ImageButton imageButton = findViewById(R.id.activity_search_arrowdown_left_bt);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return dateEnd;
    }
}


