package com.vincler.jf.projet5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.vincler.jf.projet5.models.ArticleSearch;
import com.vincler.jf.projet5.models.ArticlesSearchResponse;

import java.util.List;

import retrofit2.Response;

public class ResultSearchActivity extends AppCompatActivity {


    Response<ArticlesSearchResponse> resultSearch;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            String source = "";
            if (intent.hasExtra("source")) {
                source = intent.getStringExtra("source");
            }
            if (source.equals("MainActivity")) {
                resultSearch = MainActivity.resultSearch;
            }
            if (source.equals("SearchActivity")) {
                resultSearch = SearchActivity.resultSearch;
            }
        }

        setContentView(R.layout.activity_resultsearch);
        final RecyclerView rv = findViewById(R.id.resultSearch_recyclerView);

        rv.setLayoutManager(new LinearLayoutManager(this));
        if (resultSearch != null) {
            if (resultSearch.body().getResults().isEmpty()) {
                Log.i("TAG-search", "VIDE");
                final TextView tv = findViewById(R.id.resultSearch_textView);
                tv.setText("Aucun résultat trouvé");

            } else {
                List<ArticleSearch> articleSearch = resultSearch.body().getResults();
                rv.setAdapter(new ArticleAdapter(this, articleSearch));

            }
        }

    }
}
