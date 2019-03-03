package com.vincler.jf.projet5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }


    @Override
    public int getCount() {
        return (3);
    }

    @Override
    public Fragment getItem(int position) {
        return (PageFragment.newInstance(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        final CharSequence title[] = {"TOP STORIES", "MOST POPULAR", "BUSINESS"};
        return title[position];
    }
}