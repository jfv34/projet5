package com.vincler.jf.projet5.data;

import com.vincler.jf.projet5.ArticlesResponse;
import com.vincler.jf.projet5.models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {
    @GET("topstories/v2/home.json?api-key=jGQidx72NOVdW62AOG2f61ITRG2Gmsbx")
    Call<ArticlesResponse> list();
}





