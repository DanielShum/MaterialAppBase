package com.daililol.material.appbase.example;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.daililol.material.appbase.base.BaseTabbableActionbarActivity;
import com.daililol.material.appbase.component.BaseFragmentPagerAdapter;
import com.daililol.material.appbase.utility.DrawableUtil;

/**
 * Created by DennyShum on 8/21/15.
 */
public class ExampleTabbarActivity extends BaseTabbableActionbarActivity{

    public static void launch(Context context){
        Intent intent = new Intent(context, ExampleTabbarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getTabbar().getTabAt(0).getIcon().setState(new int[]{android.R.attr.state_selected});
    }

    @Override
    protected void setupViewPager(ViewPager viewPager) {
        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExampleListedRecyclerViewFragment(), "", false);
        adapter.addFragment(new ExampleGridedRecyclerViewFragment(), "Tab 2", false);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected Drawable setupTabIcon(int position) {
        if (position == 0){
            Drawable selectedState = DrawableUtil.getDrawable(this, R.drawable.ic_chat_green_24dp);
            Drawable normalState = DrawableUtil.getDrawable(this, R.drawable.ic_chat_grey600_24dp);
            Drawable tabIcon0 = DrawableUtil.createStateListDrawable(this, normalState, selectedState, selectedState,selectedState, normalState);
            return  tabIcon0;
        }

        return null;
    }

    @Override
    protected Drawable setupThemeColor() {
        return null;
    }

    @Override
    protected int setupTabIndicatorColor() {
        return 0;
    }

    @Override
    protected int setupTabIndicatorHeight() {
        return 0;
    }

    @Override
    protected Drawable setupTabbarColor() {
        return new ColorDrawable(Color.WHITE);
    }

    @Override
    protected void onMenuItemSelected(MenuItem menu) {

    }

    @Override
    protected boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        return false;
    }

    @Override
    protected Object onDoTaskInBackground(Object... params) {
        return null;
    }

    @Override
    protected void handleUiMessage(Message msg) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
