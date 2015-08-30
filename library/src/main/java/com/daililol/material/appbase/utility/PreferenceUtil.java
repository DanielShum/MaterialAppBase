package com.daililol.material.appbase.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
	
	private static SharedPreferences getPeferences(Context context){
		String PREFS_NAME = context.getPackageName(); 
    	return context.getSharedPreferences(PREFS_NAME, 0); 
	}
	
	public static void putString(Context context, String key, String value){
    	
    	SharedPreferences.Editor editor = getPeferences(context).edit(); 
    	editor.putString(key, value); 
    	editor.commit();
    }
	
	public static String getString(Context context, String key){
    	String result = getPeferences(context).getString(key, "");
    	return result;
    }
	
	
	public static void putInt(Context context, String key, int value){
    	SharedPreferences.Editor editor = getPeferences(context).edit(); 
    	editor.putInt(key, value); 
    	editor.commit();
    }
	
	public static int getInt(Context context, String key){
    	int result = getPeferences(context).getInt(key, -1);
    	return result;
    }
    
	public static void putFloat(Context context, String key, float value){
    	SharedPreferences.Editor editor = getPeferences(context).edit(); 
    	editor.putFloat(key, value); 
    	editor.commit();
    }
	
	public static float getFloat(Context context, String key){
    	float result = getPeferences(context).getFloat(key, -1f);
    	return result;
    }
	
	public static void putBoolean(Context context, String key, boolean value){
    	SharedPreferences.Editor editor = getPeferences(context).edit(); 
    	editor.putBoolean(key, value); 
    	editor.commit();
    }
	
	public static boolean getBoolean(Context context, String key){
    	boolean result = getPeferences(context).getBoolean(key, false);
    	return result;
    }
	

}
