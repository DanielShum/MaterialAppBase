package com.daililol.material.appbase.example;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daililol.material.appbase.base.BaseCollapsingActionbarActivity;
import com.daililol.material.appbase.utility.Converter;
import com.daililol.material.appbase.utility.DrawableUtil;

/**
 * Created by DennyShum on 8/23/15.
 */
public class ExampleCollapsingViewActivity extends BaseCollapsingActionbarActivity{

    public static void launch(Context context){
        Intent intent = new Intent(context, ExampleCollapsingViewActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentReplacement, new ExampleListedRecyclerViewFragment()).commit();
        this.requestBackIcon(DrawableUtil.getDrawable(this, R.drawable.ic_arrow_back_white_24dp));
    }

    @Override
    protected int setupThemeColor() {
        return 0;
    }

    @Override
    public View setupCollapsingView() {
        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, Converter.dp2px(this, 280));
        ImageView imageVIew = new ImageView(this);
        imageVIew.setLayoutParams(params);
        imageVIew.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageVIew.setImageResource(R.drawable.collapse_slash);


        return imageVIew;
    }

    @Override
    public View setupContentView() {
        return LayoutInflater.from(this).inflate(R.layout.example_collapsing_view_activity, null);
    }

    @Override
    public String setupTransictionName() {
        return null;
    }

    @Override
    protected void onMenuItemSelected(MenuItem menu) {

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
