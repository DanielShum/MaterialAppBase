package com.daililol.material.appbase.base;

import com.daililol.material.appbase.R;
import com.daililol.material.appbase.utility.DrawableUtil;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class BaseCollapsingActionbarActivity extends BaseFragmentActivity{
	
	private AppBarLayout appbarLayout;
	private ActionBar actionBar;
	private CollapsingToolbarLayout toolbarLayout;
	private RelativeLayout customizedCollapsingViewHolder;
	private Toolbar toolbar;
	private RelativeLayout customizedContentViewHolder;
    //private FloatingActionButton floatingActionButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_collapsing_actionbar_activity);
		
		appbarLayout = (AppBarLayout) findViewById(R.id.baseAppbarLayout);
		toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.baseCollapsingToolbar);
        customizedCollapsingViewHolder = (RelativeLayout) findViewById(R.id.baseCustomizedCollapsingViewHolder);
		toolbar = (Toolbar) findViewById(R.id.baseToolbar);
		customizedContentViewHolder = (RelativeLayout) findViewById(R.id.customizedContentViewHolder);
        //floatingActionButton = (FloatingActionButton) findViewById(R.id.baseFab);

        toolbar.setBackgroundColor(Color.TRANSPARENT);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        
        
        setActionbarThemeColor();
		setupCustomizedViews();
		
		onActivityCreated(savedInstanceState);
		
	}
	
	private void setupCustomizedViews(){
		View collapsingView = setupCollapsingView();
		View contentView = setupContentView();
		
		if (collapsingView != null) customizedCollapsingViewHolder.addView(collapsingView);
		if (contentView != null) customizedContentViewHolder.addView(contentView);
	}
	

	/**
	 * 
	 * This will be called after onCreate 
	 * @param savedInstanceState
	 */
	abstract protected void onActivityCreated(Bundle savedInstanceState);

	
	/**
	 * Here should return specific color, not the color resource id.
	 * @return Color
	 */
	abstract protected int setupThemeColor();


    /**
     * --------------------------------------
     * |                                    |
     * |  <-                                |
     * |                                    |
     * |                                    |
     * |                THE                 |
     * |            COLLAPSING              |
     * |               VIEW                 |
     * |                                    |
     * |                                    |
     * |      Actionbar title      |------| |
     * |                           | THE  | |
     * |---------------------------| FAB  |-|
     * |                           |------| |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                THE                 |
     * |              CONTENT               |
     * |                VIEW                |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * --------------------------------------
     * @return
     */
	abstract public View setupCollapsingView();
	abstract public View setupContentView();



    /**
     * The method will be involved when the user click an actionbar menu item
     * @param menu
     */
	abstract protected void onMenuItemSelected(MenuItem menu);
	
	/**
	 * 
	 * @param menu
	 * @param inflater
	 * @return return false will show no menu items.
	 */
	abstract protected boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater);
	




    /**
     * Below is an actionbar
     * -------------------------------------------------------
     * |  ------                                             |
     * |  |Home|      Action bar title                       |
     * |  ------                                             |
     * -------------------------------------------------------
     *   ------
     *   |Home|   <- this is the returned drawable
     *   ------
     * @param drawable
     */
	protected void requestHomeIcon(Drawable drawable){
		getActionbar().setHomeAsUpIndicator(drawable);
		getActionbar().setDisplayHomeAsUpEnabled(true);
	}


    /**
     * Below is an actionbar
     * -------------------------------------------------------
     * |  ------                                             |
     * |  |Here|      Action bar title                       |
     * |  ------                                             |
     * -------------------------------------------------------
     *   ------
     *   |Here|   <- this is the returned drawable
     *   ------
     * @param drawable
     */
	protected void requestBackIcon(Drawable drawable){
		getActionbar().setHomeAsUpIndicator(drawable);
		getActionbar().setDisplayHomeAsUpEnabled(true);
	}
	
	protected CollapsingToolbarLayout getToolbarLayout(){

        return toolbarLayout;
	}
	
	protected Toolbar getToolBar(){

        return toolbar;
	}
	
	protected ActionBar getActionbar(){

        return actionBar;
	}


    /**
     * --------------------------------------
     * |                                    |
     * |  <-                                |
     * |                                    |
     * |                                    |
     * |                THE                 |
     * |            COLLAPSING              |
     * |               VIEW                 |
     * |                                    |
     * |                                    |
     * |      Actionbar title      |------| |
     * |                           | THE  | |
     * |---------------------------| FAB  |-|  <<- The floating action button
     * |                           |------| |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                THE                 |
     * |              CONTENT               |
     * |                VIEW                |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * --------------------------------------
     * @return FloatingActionButton
     */
    /*protected FloatingActionButton getFloatingActionButton(){

        return floatingActionButton;
    }*/

    /**
     *
     * Below is an actionbar
     * -------------------------------------------------------
     * |  -----                                              |
     * |  |<--|      Action bar title                        |
     * |  -----                                              |
     * -------------------------------------------------------
     * @param title
     * @param color specify the actionbar actual title color, can not be color resourceId.
     */
	protected void setActionbarTitle(CharSequence title, int color){
		getToolbarLayout().setTitle(title);
		getToolbarLayout().setExpandedTitleColor(color);
		getToolBar().setTitleTextColor(color);
	}

	protected void setTransitionName(int viewId, String transitionName){
		if (transitionName != null) {
            ViewCompat.setTransitionName(findViewById(viewId), transitionName);
        }
	}
	

	private void setActionbarThemeColor(){
		int color = setupThemeColor();
		if (color == 0) color = getResources().getColor(R.color.base_theme_blue);
		getToolbarLayout().setContentScrimColor(color);
		getToolbarLayout().setStatusBarScrimColor(color);

        ColorStateList colorStateList = DrawableUtil.createColorStateList(color, color, color, color, color);
        //getFloatingActionButton().setBackgroundTintList(colorStateList);
	}
	
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    	if (item.getItemId() == android.R.id.home){
    		finish();
    	}
    	
    	onMenuItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       return onCreateOptionsMenu(menu, getMenuInflater());
    }
    

	

}
