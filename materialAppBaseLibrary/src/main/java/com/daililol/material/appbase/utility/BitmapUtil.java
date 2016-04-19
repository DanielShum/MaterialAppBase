package com.daililol.material.appbase.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CalendarView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by DennyShum on 8/28/15.
 */
public class BitmapUtil {

    public static Bitmap convertViewToBitmap(View view){

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        view.draw(canvas);
        return bitmap;
    }

    public static Bitmap createCircularBitmap(int color, int size){

        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setFilterBitmap(false);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(size / 2.0f, size / 2.0f, size / 2.0f, paint);

        return bitmap;
    }

    public static Bitmap setBitmapSaturation(Bitmap inputBitmap, int saturation) {
        int width, height;
        height = inputBitmap.getHeight();
        width = inputBitmap.getWidth();

        Bitmap outputImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(inputBitmap);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(saturation);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        canvas.drawBitmap(inputBitmap, 0, 0, paint);
        inputBitmap.recycle();

        return outputImage;
    }

    public static Bitmap createBlackWhiteBitmap(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        int[] pixels = new int[width * height];

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        bmp.recycle();
        int alpha = 0xFF << 24;

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];



                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);

                if (grey > 128){
                    grey = 255;
                }else{
                    grey = 0;
                }

                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }

        Bitmap outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        outputBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return outputBitmap;
    }

    public static Bitmap decodeImageFile(Context context, String fileName, int width, int height) {

        try {

            File file = new File(fileName);
            if (!file.exists()){
                return null;
            }

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, options);

            int simple_width = (width > 0) ? width : DeviceUtil.getScreenSize(context).x;
            int simple_height = (height > 0)? height : DeviceUtil.getScreenSize(context).y;


            if (options.outWidth <= simple_width){
                options.inSampleSize = 1;
            }else{
                simple_height = (int)(((float)simple_width / (float)options.outWidth) *  (float)options.outHeight);
                options.inSampleSize = calculateInSampleSize(options, simple_width, simple_height);
            }

            options.inJustDecodeBounds = false;
            FileInputStream fis = new FileInputStream(fileName);

            Bitmap bmp = BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);

            if (fis != null)fis.close();
            return bmp;
        } catch (Exception e) {
            return null;
        }
    }

    private static int calculateInSampleSize(
        BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }



    public static Bitmap createRotatedBitmap(Context context, String imageUrl, int rotation){

        Bitmap bitmap = BitmapFactory.decodeFile(imageUrl);
        if (bitmap == null) return null;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix mtx = new Matrix();
        mtx.preRotate(rotation);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false);

        return bitmap;
    }

    public static Bitmap createRotatedBitmap(Context context, Bitmap bitmap, int rotation){

        if (bitmap == null) return null;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix mtx = new Matrix();
        mtx.preRotate(rotation);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false);

        return bitmap;
    }


    public static double getBitmapBrightness(Context context, Bitmap bitmap){

        int localWidth = bitmap.getWidth();
        int y[] = { 0, 4, 9, 13, 18, 23, 28, 33, 38, 43, 48 };
        int x[] = { 0, localWidth / 8, localWidth * 2 / 8, localWidth * 3 / 8,
                localWidth * 4 / 8, localWidth * 5 / 8, localWidth * 6 / 8,
                localWidth * 7 / 8, localWidth -1 };

        int r;
        int g;
        int b;
        int number = 0;
        double bright = 0;
        Integer localTemp;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                number++;
                localTemp = (Integer) bitmap.getPixel(x[i], y[j]);
                r = (localTemp | 0xff00ffff) >> 16 & 0x00ff;
                g = (localTemp | 0xffff00ff) >> 8 & 0x0000ff;
                b = (localTemp | 0xffffff00) & 0x0000ff;

                bright = bright + 0.299 * r + 0.587 * g + 0.114 * b;
            }
        }

        bright =(int)(bright / number);
        return bright;

    }


    /**
     *
     * @param bmp input bitmap
     * @param contrast 0..10 1 is default
     * @param brightness -255..255 0 is default
     * @return new bitmap
     */
    public static Bitmap changeBitmapContrastBrightness(Bitmap bmp, float contrast, float brightness)
    {
        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });

        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        Canvas canvas = new Canvas(ret);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bmp, 0, 0, paint);

        return ret;
    }


    public static boolean writeBitmapToFile(Bitmap bitmap, String desFileUrl){
        return writeBitmapToFile(bitmap, desFileUrl, Bitmap.CompressFormat.JPEG);
    }

    public static boolean writeBitmapToFile(Bitmap bitmap, String desFileUrl, Bitmap.CompressFormat format){
        FileOutputStream outStream = null;

        try{
            outStream = new FileOutputStream(desFileUrl);
            bitmap.compress(format, 100, outStream);
            outStream.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }


    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    public static Bitmap createRoundedRectangleBitmap(Bitmap bitmap, float radius) {
        if (bitmap == null) return bitmap;
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        try {
            Canvas canvas = new Canvas(output);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);

            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(rect);

            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawRoundRect(rectF, radius, radius, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            canvas.drawBitmap(bitmap, src, rect, paint);
            bitmap.recycle();
            return output;
        } catch (Exception e) {
            output.recycle();
            return bitmap;
        }
    }

}
