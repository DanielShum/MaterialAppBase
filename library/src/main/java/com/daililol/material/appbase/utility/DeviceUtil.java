package com.daililol.material.appbase.utility;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

public class DeviceUtil {
	
	public static Point getScreenSize(Context context){
		Point size = new Point();
		DisplayMetrics display = context.getResources().getDisplayMetrics();
		size.x = display.widthPixels;
		size.y = display.heightPixels;

		return size;
	}

}
