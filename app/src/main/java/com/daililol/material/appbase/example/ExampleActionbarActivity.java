package com.daililol.material.appbase.example;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.daililol.material.appbase.base.BaseActionbarActivity;
import com.daililol.material.appbase.utility.DrawableUtil;

/**
 * Created by DennyShum on 8/23/15.
 */
public class ExampleActionbarActivity extends BaseActionbarActivity{


    public static void launch(Context context){
        Intent intent = new Intent(context, ExampleActionbarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        this.requestBackIcon(DrawableUtil.getDrawable(this, R.drawable.ic_arrow_back_black_24dp));
        this.setActionbarTitle("ActionBar activity", Color.BLACK);
    }

    @Override
    protected int setupContentVew() {
        return R.layout.example_actionbar_activity;
    }

    @Override
    protected Drawable setupThemeColor() {
        return null;
    }

    @Override
    protected void onMenuItemSelected(MenuItem menu) {
        switch (menu.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
    }

    @Override
    protected boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    protected Object onDoTaskInBackground(Object... params) {
        return null;
    }

    @Override
    protected void handleUiMessage(Message msg) {

    }

    @Override
    public void onClick(View view) {

    }
}
