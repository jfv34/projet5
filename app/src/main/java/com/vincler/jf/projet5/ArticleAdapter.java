package com.vincler.jf.projet5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vincler.jf.projet5.models.Listable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ArticleAdapter<T extends Listable> extends RecyclerView.Adapter {

    private static final String KEY_URL_WEBVIEW = "url";
    private List<T> listable;
    private Context context;

    Bundle args = new Bundle();

    public ArticleAdapter(Context context, List<T> listable) {
        this.context = context;
        this.listable = listable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(context, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).bind(listable.get(i));
    }

    @Override
    public int getItemCount() {
        return listable.size() > 20 ? 20 : listable.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Context context;

        public ViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
        }

        public void bind(final T data) {
            TextView categoryTv = itemView.findViewById(R.id.item_article_category);
            TextView dateTv = itemView.findViewById(R.id.item_article_date);
            TextView titleTv = itemView.findViewById(R.id.item_article_title);
            ImageView imageView = itemView.findViewById(R.id.item_article_image);

            String cover = data.getCover();

            Log.e("TAG", "cover ->" + cover);

            Glide.with(context).load(cover).into(imageView);

            String categoryAndsubCategory;

            if (data.getSubcategory() != null && data.getSubcategory().isEmpty()) {
                categoryAndsubCategory = data.getCategory();
            } else if (data.getSubcategory() != null && !data.getSubcategory().isEmpty()) {
                categoryAndsubCategory = data.getCategory() + " > " + data.getSubcategory();
            } else {
                categoryAndsubCategory = data.getCategory();
            }

            String title;
            if (data.getTitle().isEmpty()) {
                title = context.getString(R.string.noExtract);
            } else {
                title = data.getTitle();
            }

            categoryTv.setText(categoryAndsubCategory);

            dateTv.setText(data.getDateString());
            titleTv.setText(title);

            titleTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DisplayWebView(data.getUrl());
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DisplayWebView(data.getUrl());
                }
            });
            categoryTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DisplayWebView(data.getUrl());
                }
            });
            dateTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DisplayWebView(data.getUrl());
                }
            });

        }
    }

    private void DisplayWebView(String url) {

        args.putString(KEY_URL_WEBVIEW, url);
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtras(args);
        context.startActivity(intent);


    }
}
