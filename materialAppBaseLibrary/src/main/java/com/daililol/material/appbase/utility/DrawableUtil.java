package com.daililol.material.appbase.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;

public class DrawableUtil {
	
	public static Drawable changeDrawableColor(Drawable drawable, int destinationColor){
		drawable.setColorFilter(destinationColor, android.graphics.PorterDuff.Mode.MULTIPLY);
		return drawable;
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static Drawable getDrawable(Context context, int resourceId){

		if (Build.VERSION.SDK_INT >= 21){
			return context.getDrawable(resourceId);
		}else{
			return context.getResources().getDrawable(resourceId);
		}
	}
	
	public static Drawable getDrawable(Context context, int resourceId, int convertToColor){
		Drawable drawable = getDrawable(context, resourceId);
		return changeDrawableColor(drawable, convertToColor);
		
	}
	
	public static ColorStateList createColorStateListAPI21(int normal) {  
        int[] colors = new int[] {normal};  
        int[][] states = new int[1][];  
        states[0] = new int[] {};  
        ColorStateList colorList = new ColorStateList(states, colors);  
        return colorList;  
    }
	
	public static ColorStateList createColorStateList(int normal, int selected, int pressed, int focused, int unable) {  
        int[] colors = new int[] { pressed, focused, selected, focused, unable, normal, normal };  
        int[][] states = new int[7][];  
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };  
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };   
        states[2] = new int[] { android.R.attr.state_selected };
        states[3] = new int[] { android.R.attr.state_focused };  
        states[4] = new int[] { android.R.attr.state_window_focused };  
        states[5] = new int[] { android.R.attr.state_enabled }; 
        states[6] = new int[] {};  
        ColorStateList colorList = new ColorStateList(states, colors); 
        return colorList;  
    }

	public static StateListDrawable createStateListDrawable(Context context, int normalResourceId, int activeResourceId){
		Drawable drawableNormal = getDrawable(context, normalResourceId);
		Drawable drawableActive = getDrawable(context, activeResourceId);
		return createStateListDrawable(context, drawableNormal, drawableActive,
				drawableActive, drawableActive, drawableNormal);
	}

	public static StateListDrawable createStateListDrawable(Context contet, 
			Drawable normal, Drawable selected, Drawable pressed, Drawable focused, Drawable unable){
		
		StateListDrawable drawableList = new StateListDrawable();
		drawableList.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, pressed);  
		drawableList.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, focused);
		drawableList.addState(new int[] { android.R.attr.state_selected }, selected);  
		drawableList.addState(new int[] { android.R.attr.state_focused }, focused);  
		drawableList.addState(new int[] { android.R.attr.state_window_focused }, unable); 
		drawableList.addState(new int[] { android.R.attr.state_enabled }, normal); 
		drawableList.addState(new int[] {}, normal);  
        
		return drawableList;
		

	}

}
