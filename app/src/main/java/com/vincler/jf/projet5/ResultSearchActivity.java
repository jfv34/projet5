package com.vincler.jf.projet5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.vincler.jf.projet5.models.ArticleSearch;

import java.util.List;

public class ResultSearchActivity extends AppCompatActivity {


    List resultSearch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            ArticleParcelable articleParcelable = intent.getParcelableExtra("articleParcelable");
            if (articleParcelable != null) {
           resultSearch = articleParcelable.getResponse();
            }
        }


        setContentView(R.layout.activity_resultsearch);

        final Toolbar toolbar = findViewById(R.id.resultSearch_toolbar);
        toolbar.setTitle("Result search");

        final RecyclerView rv = findViewById(R.id.resultSearch_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));


        if (resultSearch != null) {
            if (resultSearch.isEmpty()) {
                final TextView tv = findViewById(R.id.resultSearch_textView);
                tv.setText(R.string.noresults);

            } else {
                List<ArticleSearch> articleSearch = resultSearch;
                rv.setAdapter(new ArticleAdapter(this, articleSearch));

            }
        }


    }
}
