package com.vincler.jf.projet5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincler.jf.projet5.models.Article;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ArticleAdapter extends RecyclerView.Adapter {

    private List<Article> articles;
    private Context context;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

    public ArticleAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, viewGroup, false);
        return new ViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ViewHolder) viewHolder).bind(articles.get(i));
    }

    @Override
    public int getItemCount() {
        return articles.size() > 20 ? 20 : articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Context context;

        public ViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;


        }

        public void bind(final Article data) {

            TextView categoryTv = itemView.findViewById(R.id.item_article_category);
            TextView dateTv = itemView.findViewById(R.id.item_article_date);
            TextView titleTv = itemView.findViewById(R.id.item_article_title);


            String date = dateFormat.format(data.date);
            String title = data.title;

            String categoryAndsubCategory;
            if(data.subCategory.isEmpty()){
                categoryAndsubCategory = data.category;}
            else {
            categoryAndsubCategory = data.category + " > " + data.subCategory;}

            categoryTv.setText(categoryAndsubCategory);
            dateTv.setText(date);
            titleTv.setText(title);


        }
    }
}
