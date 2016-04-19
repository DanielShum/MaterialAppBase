package com.daililol.material.appbase.utility;

import android.content.Context;


public class Converter {
	
	public static int dp2px(Context context, float dp){
		return (int)(dp * context.getResources().getDisplayMetrics().density);
	}

	public static int px2dp(Context context, float px){
		return (int)(px / context.getResources().getDisplayMetrics().density);
	}
}
