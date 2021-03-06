package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class HelpActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        configureToolbar();
        displayText();

    }

    private void displayText() {
        TextView tv = findViewById(R.id.activity_help_textView);
        tv.setText(getString(R.string.helptext));
    }

    void configureToolbar() {

        this.toolbar = (findViewById(R.id.activity_help_toolbar));
        toolbar.setTitle(R.string.help);


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}