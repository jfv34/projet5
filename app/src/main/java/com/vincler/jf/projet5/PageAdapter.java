package com.vincler.jf.projet5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.vincler.jf.projet5.models.ArticleListType;

import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {

    List<ArticleListType> pages;

    public PageAdapter(FragmentManager fm, List<ArticleListType> pages) {
        super(fm);
        this.pages=pages;
    }


    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public Fragment getItem(int position) {
        return (PageFragment.newInstance(pages.get(position)));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        final CharSequence title[] = {"TOP STORIES", "MOST POPULAR", "BUSINESS"};
        return title[position];
    }
}