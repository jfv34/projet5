package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        String query = gettingQuery();


    }

    private String gettingQuery() {

        EditText editText = findViewById(R.id.activity_notifications_query);
        return editText.getText().toString();
    }


}
