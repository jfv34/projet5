package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.vincler.jf.projet5.models.ArticleSearch;

import java.util.List;

public class ResultSearchActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<ArticleSearch> resultSearch = getIntent().getExtras().getParcelableArrayList("data");


        setContentView(R.layout.activity_resultsearch);

        final Toolbar toolbar = findViewById(R.id.resultSearch_toolbar);
        toolbar.setTitle("Result search");

        final RecyclerView rv = findViewById(R.id.resultSearch_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));


        if (resultSearch.isEmpty()) {
            final TextView tv = findViewById(R.id.resultSearch_textView);
            tv.setText(R.string.noresults);

        } else {
            rv.setAdapter(new ArticleAdapter(this, resultSearch));

        }


    }
}
