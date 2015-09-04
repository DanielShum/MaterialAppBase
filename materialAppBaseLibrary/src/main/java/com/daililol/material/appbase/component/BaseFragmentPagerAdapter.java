package com.daililol.material.appbase.component;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public  class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();
    
    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
    	addFragment(fragment, title, true);
    }
    
    public void addFragment(Fragment fragment, String title, boolean hasOptionsMenu) {
    	fragment.setHasOptionsMenu(hasOptionsMenu);
        mFragments.add(fragment);
        mFragmentTitles.add(title);

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}