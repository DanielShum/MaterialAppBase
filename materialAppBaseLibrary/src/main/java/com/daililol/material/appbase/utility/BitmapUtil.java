package com.daililol.material.appbase.utility;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by DennyShum on 8/28/15.
 */
public class BitmapUtil {

    public static Bitmap convertViewToBitmap(View view){

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        return bitmap;
    }
}
