package com.daililol.material.appbase.example;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.daililol.material.appbase.base.BaseSearchBarActivity;
import com.daililol.material.appbase.utility.DrawableUtil;

/**
 * Created by DennyShum on 8/23/15.
 */


public class ExampleSearchBarActivity extends BaseSearchBarActivity{


    public static void launch(Context context){
        Intent intent = new Intent(context, ExampleSearchBarActivity.class);
        context.startActivity(intent);
    }


    /**
     * This method will be involved after the activity is created and before the search bar is attached.
     * @param savedInstanceState
     */
    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        this.requestBackIcon(DrawableUtil.getDrawable(this, R.drawable.ic_arrow_back_black_24dp));
    }


    /**
     * This method will be involved after onActivityCreated(Bundle savedInstanceState);
     */
    @Override
    protected void onSearchBarAttached() {
        //setup the search bar here, for example you may want to set a default keyword here
        //or you may want popup the soft keyboard here.
        //getKeywordEditText().setText("someKeyword");
    }



    @Override
    protected void onSearch(String Keyword) {

    }

    @Override
    protected void onCleanKeyword() {

    }



    @Override
    protected int setupContentVew() {
        return 0;
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
            default:
        }
    }

    @Override
    protected boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        return true;
    }

    @Override
    protected Object onDoTaskInBackground(Object... params) {
        return null;
    }

    @Override
    protected void handleUiMessage(Message msg) {

    }
}
