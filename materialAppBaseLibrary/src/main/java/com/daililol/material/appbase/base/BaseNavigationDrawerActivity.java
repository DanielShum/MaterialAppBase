package com.daililol.material.appbase.base;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.AdapterView;

import com.daililol.material.appbase.R;
import com.daililol.material.appbase.component.BaseNavigationDrawerListAdapter;
import com.daililol.material.appbase.utility.Converter;
import com.daililol.material.appbase.utility.DeviceUtil;
import com.daililol.material.appbase.utility.DrawableUtil;
import com.daililol.material.appbase.widget.ExtendableListView;

public abstract class BaseNavigationDrawerActivity extends BaseFragmentActivity implements DrawerListener,
AdapterView.OnItemClickListener{


    /**
     * An activity will be created like this
     * --------------------------------------          ---------------------------------------
     * |  __                                |          |                             |       |
     * |  ==    Action bar                  |          |                             |       |
     * |                                    |          |                             |       |
     * |------------------------------------|          |                             |-------|
     * |                                    |          |    Navigation Drawer        |       |
     * |                                    |          |    Header                   |       |
     * |                                    |          |                             |       |
     * |                                    |   >      |                             |       |
     * |                                    |   >      |-----------------------------|       |
     * |                                    |   >      |   _                         |       |
     * |                                    |   >      |  |_|   Navigation item 1    |       |
     * |                                    |          |_____________________________|       |
     * |                                    |          |   _                         |       |
     * |                THE                 |          |  |_|   Navigation item 2    |       |
     * |              CONTENT               |          |_____________________________|       |
     * |                VIEW                |          |   _                         |       |
     * |                                    |          |  |_|   Navigation item 3    |       |
     * |                                    |          |_____________________________|       |
     * |                                    |          |                             |       |
     * |                                    |          |                             |       |
     * |                                    |          |                             |       |
     * |                                    |          |                             |       |
     * |                                    |          |                             |       |
     * |                                    |          |                             |       |
     * |                                    |          |                             |       |
     * |                                    |          |                             |       |
     * |                                    |          |                             |       |
     * --------------------------------------          --------------------------------------
     */

	private RelativeLayout statusBar;
	private Toolbar toolbar;
	private ActionBar actionBar;
	private RelativeLayout customizedContentViewHolder;
	private View versionAdaptiveActionbarShawdow;
	
	private DrawerLayout navigationDrawer;
	private ExtendableListView navigationListView;
	private BaseNavigationDrawerListAdapter navigationListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.base_navigation_drawer_activity);
		
		navigationDrawer = (DrawerLayout) findViewById(R.id.baseNavigationDrawer);
		navigationListView = (ExtendableListView) findViewById(R.id.baseNavigationList);
		
		statusBar = (RelativeLayout) findViewById(R.id.baseStatusBar);
		toolbar = (Toolbar) findViewById(R.id.baseToolbar);
		customizedContentViewHolder = (RelativeLayout) findViewById(R.id.customizedContentViewHolder);
		versionAdaptiveActionbarShawdow = (View) findViewById(R.id.baseVersionAdaptiveActionbarShadow);
		
		navigationListView.getLayoutParams().width = DeviceUtil.getScreenSize(this).x - Converter.dp2px(this, 56);
		navigationDrawer.setDrawerListener(this);
		navigationDrawer.setDrawerShadow(R.drawable.rectangle_left_to_right_shadaw, Gravity.LEFT);
		navigationListView.setOnItemClickListener(this);

		View navigationDrawerHeaderView = setupNavigationDrawerHeader();
		if (navigationDrawerHeaderView != null) navigationListView.addHeaderView(navigationDrawerHeaderView);

        navigationListAdapter = new BaseNavigationDrawerListAdapter(this);
        navigationListView.setAdapter(navigationListAdapter);
        setupNavigationDrawerItem(navigationListView, navigationListAdapter);

		if (Build.VERSION.SDK_INT >= 21) versionAdaptiveActionbarShawdow.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
     
        
        setActionbarThemeColor();
		this.requestHomeIcon(DrawableUtil.getDrawable(this, R.drawable.ic_menu_black_24dp));
        
        
		setupContentView(customizedContentViewHolder, setupContentVew());
        onActivityCreated(savedInstanceState);
	}
	
	
	/**
	 * This will be called after onCreate 
	 * @param savedInstanceState
	 */
	abstract protected void onActivityCreated(Bundle savedInstanceState);
	
	abstract protected int setupContentVew();
	abstract protected Drawable setupThemeColor();
	abstract protected void onMenuItemSelected(MenuItem menu);
	abstract protected void setupNavigationDrawerItem(ExtendableListView listView, BaseNavigationDrawerListAdapter navigationListAdapter);

    /**
     *
     * @return the returned view will be set to the navigation head.
     */
    abstract protected View setupNavigationDrawerHeader();

    /**
     *
     * @param adapterView
     * @param itemView
     * @param position
     * @return  true the selected item highlight, false otherwise.
     */
	abstract protected boolean navigationOnItemClickListener(AdapterView<?> adapterView, View itemView, int position);
	
	/**
	 * 
	 * @param menu
	 * @param inflater
	 * @return return false will show no menu items.
	 */
	abstract protected boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater);
	

	public void toggleNavigation(){
		if (navigationDrawer.isDrawerOpen(Gravity.LEFT)){
			navigationDrawer.closeDrawer(Gravity.LEFT);
		}else{
			navigationDrawer.openDrawer(Gravity.LEFT);
		}
	}
	
	private void setupContentView(RelativeLayout customizedContentViewHolder, int customizedContentView){
        if (customizedContentView == 0) return;
		View view = LayoutInflater.from(this).inflate(customizedContentView, null);
		customizedContentViewHolder.addView(view);
	}
	
	protected void requestHomeIcon(Drawable drawable){
		getActionbar().setHomeAsUpIndicator(drawable);
		
		getActionbar().setDisplayHomeAsUpEnabled(true);
	}
	
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		if (navigationOnItemClickListener(arg0, arg1, arg2)){
            navigationListAdapter.setSelectedPosition(arg2 - navigationListView.getHeaderViewsCount());
        }

        toggleNavigation();
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
    	if (item.getItemId() == android.R.id.home){
    		toggleNavigation();
    	}
    	onMenuItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       onCreateOptionsMenu(menu, getMenuInflater());
       return true;
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
    	if (keyCode == KeyEvent.KEYCODE_BACK){
    		super.onKeyDown(keyCode, event);
    		if (navigationDrawer.isDrawerOpen(Gravity.LEFT)) {
    			navigationDrawer.closeDrawer(Gravity.LEFT);
    			return false;
    		}else{
    			return true;
    		}
    		
    	}
    	
    	return super.onKeyDown(keyCode, event);
    }

}
