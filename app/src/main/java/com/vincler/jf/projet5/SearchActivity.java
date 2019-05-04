package com.vincler.jf.projet5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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

        String txt;
        Button button = findViewById(R.id.activity_search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.activity_search_query);
                // txt = editText.getText().toString();
            }
        });
        return ""; // return txt;

    }

    private Date gettingDateBegin() {

        Date dateBegin = new Date();

        ImageButton imageButton = findViewById(R.id.activity_search_arrowdown_left_bt);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();

            }
        });


        return dateBegin;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This is AlertDialog");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new OkOnClickListener());
        builder.setNegativeButton("No", new CancelOnClickListener());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "Yes button",
                    Toast.LENGTH_LONG).show();
        }
    }

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "Cancel button",
                    Toast.LENGTH_LONG).show();
        }
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


