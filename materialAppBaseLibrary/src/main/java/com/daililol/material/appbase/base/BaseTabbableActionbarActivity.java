package com.daililol.material.appbase.base;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.daililol.material.appbase.R;
import com.daililol.material.appbase.utility.DrawableUtil;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class BaseTabbableActionbarActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener{


    /**
     * An activity will be created like this
     * --------------------------------------
     * |                           _    _   |
     * |  <-   Action bar title   |_|  |_|  |
     * |                                    |
     * |------------------------------------|
     * |        |         |        |        |
     * |  Tab 1 |  Tab 2  |  Tab 3 |  Tab 4 |
     * |------------------------------------|
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
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
     */
	
	private RelativeLayout statusBar;
	private Toolbar toolbar;
	private ActionBar actionBar;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private View versionAdaptiveActionbarShadow;
	private FloatingActionButton fabButton;


	@Override 
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_tabbable_actionbar_activity);
		
		statusBar = (RelativeLayout) findViewById(R.id.baseStatusBar);
		toolbar = (Toolbar) findViewById(R.id.baseToolbar);
        tabLayout = (TabLayout) findViewById(R.id.baseTabLayout);
        viewPager = (ViewPager) findViewById(R.id.baseViewPager);
        versionAdaptiveActionbarShadow = (View) findViewById(R.id.baseVersionAdaptiveActionbarShadow);
        fabButton = (FloatingActionButton) findViewById(R.id.baseFab);
		
		if (Build.VERSION.SDK_INT >= 21) versionAdaptiveActionbarShadow.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        

        getViewPager().addOnPageChangeListener(this);
        setupViewPager(getViewPager());
        getTabbar().setupWithViewPager(getViewPager());
        setActionbarThemeColor();
        setTabbarThemeColor();
		setTabIcons();
		setTabIndicatorColor(setupTabIndicatorColor());
		setTabIndicatorHeight(setupTabIndicatorHeight());

		onActivityCreated(savedInstanceState);
		
		
		
		
	}
	
	/**
	 * This will be called after onCreate 
	 * @param savedInstanceState
	 */
	abstract protected void onActivityCreated(Bundle savedInstanceState);
	abstract protected void setupViewPager(ViewPager viewPager);
	
	/**
	 * 
	 * @param position
	 * @return icon resourceId
	 */
	abstract protected Drawable setupTabIcon(int position);
	
	/**
	 * @return drawable
	 */
	abstract protected Drawable setupThemeColor();
	
	/**
	 * Should return the actual color, can not be the color resource Id.
	 * @return
	 */
	abstract protected int setupTabIndicatorColor();
	abstract protected int setupTabIndicatorHeight();
	
	/**
	 * 
	 * @return
	 */
	abstract protected Drawable setupTabbarColor();
	
	abstract protected void onMenuItemSelected(MenuItem menu);
	
	/**
	 * 
	 * @param menu
	 * @param inflater
	 * @return return false will show no menu items.
	 */
	abstract protected boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater);

	
	protected Toolbar getToolBar(){
		return toolbar;
	}
	
	protected ActionBar getActionbar(){
		return actionBar;
	}
	
	protected TabLayout getTabbar(){
		return tabLayout;
	}
	
	protected ViewPager getViewPager(){
		return viewPager;
	}
	
	
	protected FloatingActionButton getFabButton(){
		return fabButton;
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
	
	
	protected void requestHomeIcon(Drawable drawable){
		getActionbar().setHomeAsUpIndicator(drawable);
		getActionbar().setDisplayHomeAsUpEnabled(true);
	}
	
	protected void requestBackIcon(Drawable drawable){
		getActionbar().setHomeAsUpIndicator(drawable);
		getActionbar().setDisplayHomeAsUpEnabled(true);
	}
	
	
	@SuppressLint("NewApi") 
	@SuppressWarnings("deprecation")
	private void setActionbarThemeColor(){
		Drawable drawable = setupThemeColor();
		if (drawable == null) drawable = DrawableUtil.getDrawable(this, R.color.base_theme_blue);;
		if (Build.VERSION.SDK_INT >= 16){
			getToolBar().setBackground(drawable);
			statusBar.setBackground(drawable);
		}else{
			getToolBar().setBackgroundDrawable(drawable);
			statusBar.setBackgroundDrawable(drawable);
		}
	}
	
	@SuppressLint("NewApi") 
	@SuppressWarnings("deprecation")
	private void setTabbarThemeColor(){
		Drawable drawable = setupTabbarColor();
		if (drawable == null) drawable = DrawableUtil.getDrawable(this, R.color.base_theme_blue);;
		if (Build.VERSION.SDK_INT >= 16){
			getTabbar().setBackground(drawable);
		}else{
			getTabbar().setBackgroundDrawable(drawable);
		}
	}
	
	
	private void setTabIcons(){
		for(int i = 0; i < getViewPager().getAdapter().getCount(); i++){
			Drawable drawable = setupTabIcon(i);
			if (drawable == null) continue;
			getTabbar().getTabAt(i).setIcon(drawable);
		}
	}
	
	
	private void setTabIndicatorHeight(int height){
		
		if (height == 0) return;
		
		try {
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);
            Object value = field.get(tabLayout);

            Method method = value.getClass().getDeclaredMethod("setSelectedIndicatorHeight", Integer.TYPE);
            method.setAccessible(true);
            method.invoke(value, height);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
	}
	
	private void setTabIndicatorColor(int color){
		
		if (color == 0) return;
		
		try {
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);
            Object value = field.get(tabLayout);

            Method method = value.getClass().getDeclaredMethod("setSelectedIndicatorColor", Integer.TYPE);
            method.setAccessible(true);
            method.invoke(value, color);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
	}

	
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	onMenuItemSelected(item);

        return super.onOptionsItemSelected(item);
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       return onCreateOptionsMenu(menu, getMenuInflater());
    }

}
