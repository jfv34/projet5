package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class PageFragment extends Fragment {

    private static final String KEY_POSITION = "position";


    public static PageFragment newInstance(int position) {

        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = result.findViewById(R.id.fragment_page_title);

        int position = 0;
        if (getArguments() != null) {
            position = 1 + getArguments().getInt(KEY_POSITION, -1);
        }
        String text = "Page numéro " + position;
        textView.setText(text);
        textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        ArrayList articles = new ArrayList();
        articles.add(new Article(
                "", "titre 1", new Date(), "", new ArticleCategory("", null)
        ));
        return result;
    }

}
