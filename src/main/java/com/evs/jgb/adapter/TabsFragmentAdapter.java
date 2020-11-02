package com.evs.jgb.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.evs.jgb.ui.fragment.TabsContestFragment;

import java.util.ArrayList;
import java.util.List;

public class TabsFragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<TabsContestFragment> fragments;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.fragments = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    public void update(List<TabsContestFragment> fragments) {
        this.fragments.clear();
        this.fragments.addAll(fragments);
        notifyDataSetChanged();
    }

}
