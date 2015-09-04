package com.daililol.material.appbase.base;



import com.daililol.material.appbase.R;
import com.daililol.material.appbase.utility.DrawableUtil;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class BaseActionbarActivity extends BaseFragmentActivity implements View.OnClickListener{
	
	private RelativeLayout statusBar;
	private Toolbar toolbar;
	private ActionBar actionBar;
	private RelativeLayout customizedContentViewHolder;
	private View versionAdaptiveActionbarShadow;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_actionbar_activity);
		
		statusBar = (RelativeLayout) findViewById(R.id.baseStatusBar);
		toolbar = (Toolbar) findViewById(R.id.baseToolbar);
		customizedContentViewHolder = (RelativeLayout) findViewById(R.id.customizedContentViewHolder);
		versionAdaptiveActionbarShadow = (View) findViewById(R.id.baseVersionAdaptiveActionbarShadow);

        //The AppCompat toolbar does not show a shadow in SDK below 21, so I add a shadow to fix
        //the problem.
		if (Build.VERSION.SDK_INT >= 21) versionAdaptiveActionbarShadow.setVisibility(View.GONE);

        //Set the AppCompat toolbar as the activity actionbar.
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        
       //Set up user content view
        setupContentView(customizedContentViewHolder, setupContentVew());

        //Set the actionbar bar background color.
        //This can be an image drawable
        setActionbarThemeColor();
        
        //User task will begin there.
        onActivityCreated(savedInstanceState);
        
	}
	
	
	/**
	 * This will be called after onCreate 
	 * @param savedInstanceState
	 */
	abstract protected void onActivityCreated(Bundle savedInstanceState);


    /**
     * Return the content view layout resource Id.
     * @return
     */
	abstract protected int setupContentVew();

    /**
     * @return ColorDrawable, BitmapDrawable and any kind of drawable are accepted
     */
	abstract protected Drawable setupThemeColor();

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


	private void setupContentView(RelativeLayout customizedContentViewHolder, int customizedContentView){
        if (customizedContentView == 0 ) return;
		View view = LayoutInflater.from(this).inflate(customizedContentView, null);
		customizedContentViewHolder.addView(view);
	}


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
	
	protected Toolbar getToolBar(){
		return toolbar;
	}
	
	protected ActionBar getActionbar(){
		return actionBar;
	}

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
		getActionbar().setTitle(title);
		getToolBar().setTitleTextColor(color);
	}
	
	
	@SuppressLint("NewApi") 
	@SuppressWarnings("deprecation")
	private void setActionbarThemeColor(){
		Drawable drawable = setupThemeColor();
		if (drawable == null) drawable = DrawableUtil.getDrawable(this, R.color.base_theme_blue);
		if (Build.VERSION.SDK_INT >= 16){
			getToolBar().setBackground(drawable);
			statusBar.setBackground(drawable);
		}else{
			getToolBar().setBackgroundDrawable(drawable);
			statusBar.setBackgroundDrawable(drawable);
		}
	}
	
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	onMenuItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       return onCreateOptionsMenu(menu, getMenuInflater());
    }
    
    

}
