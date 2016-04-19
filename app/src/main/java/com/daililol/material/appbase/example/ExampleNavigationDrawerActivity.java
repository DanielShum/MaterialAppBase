package com.daililol.material.appbase.example;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.daililol.material.appbase.base.BaseNavigationDrawerActivity;
import com.daililol.material.appbase.component.BaseNavigationDrawerListAdapter;
import com.daililol.material.appbase.utility.Converter;
import com.daililol.material.appbase.utility.DrawableUtil;
import com.daililol.material.appbase.widget.ExtendableListView;

/**
 * Created by DennyShum on 8/20/15.
 */
public class ExampleNavigationDrawerActivity extends BaseNavigationDrawerActivity{


    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentReplacement, new ExampleListViewFragment()).commit();
    }

    @Override
    protected int setupContentVew() {

        return R.layout.example_navigation_drawer_activity;
    }

    @Override
    protected Drawable setupThemeColor() {
        return null;
    }

    @Override
    protected void onMenuItemSelected(MenuItem menu) {

    }

    @Override
    protected void setupNavigationDrawerItem(ExtendableListView listView, BaseNavigationDrawerListAdapter navigationListAdapter) {
        listView.setBackgroundColor(Color.WHITE);

        Drawable normalState = DrawableUtil.getDrawable(this, R.drawable.ic_chat_grey600_24dp);
        Drawable selectedState = DrawableUtil.getDrawable(this, R.drawable.ic_chat_green_24dp);
        StateListDrawable iconDrawable1 = DrawableUtil.createStateListDrawable(
                this, normalState, selectedState, selectedState, selectedState, normalState);
        StateListDrawable iconDrawable2 = DrawableUtil.createStateListDrawable(
                this, normalState, selectedState, selectedState, selectedState, normalState);
        StateListDrawable iconDrawable3 = DrawableUtil.createStateListDrawable(
                this, normalState, selectedState, selectedState, selectedState, normalState);

        navigationListAdapter.addItem("item 1", iconDrawable1, BaseNavigationDrawerListAdapter.MenuItem.Type.MENU_ITEM);
        navigationListAdapter.addItem("item 2", iconDrawable2, BaseNavigationDrawerListAdapter.MenuItem.Type.MENU_ITEM);
        navigationListAdapter.addItem("item 3", iconDrawable3, BaseNavigationDrawerListAdapter.MenuItem.Type.MENU_ITEM);
        navigationListAdapter.addItem(null, null, BaseNavigationDrawerListAdapter.MenuItem.Type.DIVIDER);
        navigationListAdapter.addItem("item 4", null, BaseNavigationDrawerListAdapter.MenuItem.Type.MENU_ITEM);
        navigationListAdapter.addItem("item 5", null, BaseNavigationDrawerListAdapter.MenuItem.Type.MENU_ITEM);
        navigationListAdapter.addItem("item 6", null, BaseNavigationDrawerListAdapter.MenuItem.Type.MENU_ITEM);
    }

    @Override
    protected View setupNavigationDrawerHeader() {
        AbsListView.LayoutParams params;
        params = new AbsListView.LayoutParams(AdapterView.LayoutParams.MATCH_PARENT, Converter.dp2px(this, 220));
        LinearLayout header = new LinearLayout(this);
        header.setLayoutParams(params);
        header.setBackgroundColor(this.getResources().getColor(com.daililol.material.appbase.R.color.base_theme_blue));
        return header;
    }

    @Override
    protected boolean navigationOnItemClickListener(AdapterView<?> adapterView, View itemView, int position) {
        return true;
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
    public void onDrawerSlide(View view, float v) {

    }

    @Override
    public void onDrawerOpened(View view) {

    }

    @Override
    public void onDrawerClosed(View view) {

    }

    @Override
    public void onDrawerStateChanged(int i) {

    }
}
