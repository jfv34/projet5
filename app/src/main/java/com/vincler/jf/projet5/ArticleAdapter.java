package com.vincler.jf.projet5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vincler.jf.projet5.models.Article;
import com.vincler.jf.projet5.models.Listable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ArticleAdapter extends RecyclerView.Adapter {

    private List<Listable> listable;
    private Context context;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

    public ArticleAdapter(Context context, List<Listable> listable) {
        this.context = context;
        this.listable = listable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, viewGroup, false);
        return new ViewHolder(context, view);
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

        public void bind(final Listable data) {

            TextView categoryTv = itemView.findViewById(R.id.item_article_category);
            TextView dateTv = itemView.findViewById(R.id.item_article_date);
            TextView titleTv = itemView.findViewById(R.id.item_article_title);
            ImageView imageView = itemView.findViewById(R.id.item_article_image);

//            String date = dateFormat.format(data.date);
            String title = data.title;

                Glide.with(context).load(data.getCover()).into(imageView);



            String categoryAndsubCategory;
            if (data.subCategory.isEmpty()) {
                categoryAndsubCategory = data.category;
            } else {
                categoryAndsubCategory = data.category + " > " + data.subCategory;
            }

            categoryTv.setText(categoryAndsubCategory);
            dateTv.setText(data.getDateString());
            titleTv.setText(title);


        }
    }
}
