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

public class PageFragment<T extends ArticlesResponse> extends Fragment {

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
                .baseUrl("https://api.nytimes.com/svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsService service = retrofit.create(NewsService.class);
        Call<T> call;
        switch (type) {
            case TOP_STORIES:
                call = (Call<T>) service.listTopstories();
                break;
            case MOST_POPULAR:
                call = (Call<T>) service.listMostPopular();
                break;
            default:
            case BUSINESS:
                call = (Call<T>) service.listBusiness();
                break;
        }
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                T results = response.body();
                final RecyclerView rv = fragmentView.findViewById(R.id.fragment_page_articles);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                if (results != null) {
                    rv.setAdapter(new ArticleAdapter(getContext(), results.getResults()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return fragmentView;
    }
}
