package com.daililol.material.appbase.example;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    public static void launch(AppCompatActivity activity){
        Intent intent = new Intent(activity, ExampleCollapsingViewActivity.class);
        if (Build.VERSION.SDK_INT >= 21){
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle();
            ActivityCompat.startActivity(activity, intent, bundle);
        }else{
            activity.startActivity(intent);
        }

    }

    public static void launchWithSharedElementTransition(Context context, View view){
        Intent intent = new Intent(context, ExampleCollapsingViewActivity.class);

        if (Build.VERSION.SDK_INT >= 21){
            ViewCompat.setTransitionName(view, "collapsingView");
            intent.putExtra("TRANSITION_NAME", "collapsingView");
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((AppCompatActivity)context, view, "collapsingView");
            context.startActivity(intent, options.toBundle());
        }else{
            context.startActivity(intent);
        }


    }


    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("TRANSITION_NAME")){
            String transitionName = getIntent().getExtras().getString("TRANSITION_NAME");
            setTransitionName(android.R.id.icon1, transitionName);
        }

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
        imageVIew.setId(android.R.id.icon1);


        return imageVIew;
    }

    @Override
    public View setupContentView() {
        return LayoutInflater.from(this).inflate(R.layout.example_collapsing_view_activity, null);
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
