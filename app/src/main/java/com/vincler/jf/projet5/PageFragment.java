package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincler.jf.projet5.data.NewsService;
import com.vincler.jf.projet5.models.ArticleListType;
import com.vincler.jf.projet5.models.ArticlesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PageFragment extends Fragment {

    private static final String KEY_ARTICLELISTTYPE = "position";


    public static PageFragment newInstance(ArticleListType type) {

        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ARTICLELISTTYPE, type.ordinal());
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_page, container, false);

        ArticleListType type = ArticleListType.values()[getArguments().getInt(KEY_ARTICLELISTTYPE)];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nytimes.com/svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsService service = retrofit.create(NewsService.class);
        Call<ArticlesResponse> call;
        switch (type) {
            case TOP_STORIES:
                call = service.listTopstories();
                break;
            case MOST_POPULAR:
                call = service.listMostPopular();
                break;
            default:
            case BUSINESS:
                call = service.listMostPopular(); // À MODIFIER
                break;

        }
        call.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                ArticlesResponse results = response.body();

                final RecyclerView rv = fragmentView.findViewById(R.id.fragment_page_articles);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));

                rv.setAdapter(new ArticleAdapter(getContext(), results.results));
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });


        return fragmentView;
    }

}
