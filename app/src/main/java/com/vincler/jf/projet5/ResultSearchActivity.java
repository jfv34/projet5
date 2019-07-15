package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.vincler.jf.projet5.models.ArticleSearch;

import java.util.List;
import java.util.Objects;

public class ResultSearchActivity extends AppCompatActivity {

    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //configureToolbar();
        List<ArticleSearch> resultSearch = getIntent().getExtras().getParcelableArrayList("data");


        setContentView(R.layout.activity_resultsearch);

        final RecyclerView rv = findViewById(R.id.resultSearch_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));


        if (resultSearch.isEmpty()) {
            final TextView tv = findViewById(R.id.resultSearch_textView);
            tv.setText(R.string.noresults);

        } else {
            rv.setAdapter(new ArticleAdapter(this, resultSearch));

        }


    }

    private void configureToolbar() {
        this.toolbar = (findViewById(R.id.resultSearch_toolbar));
        toolbar.setTitle(R.string.searchresults);
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
