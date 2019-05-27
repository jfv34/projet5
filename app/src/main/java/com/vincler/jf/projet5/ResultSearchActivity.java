package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vincler.jf.projet5.models.ArticleSearch;
import com.vincler.jf.projet5.models.ArticlesSearchResponse;

import java.util.List;

import retrofit2.Response;

public class ResultSearchActivity extends AppCompatActivity {

    Response<ArticlesSearchResponse> resultSearch = SearchActivity.resultSearch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resultsearch);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.resultSearch_recyclerView);

        rv.setLayoutManager(new LinearLayoutManager(this));
        if (resultSearch != null) {
            List<ArticleSearch> articleSearch = resultSearch.body().getResults();
            rv.setAdapter(new ArticleAdapter(this, articleSearch));


        }

    }
}
