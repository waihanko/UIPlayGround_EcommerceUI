package me.waihanko.uiplayground.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import me.waihanko.uiplayground.R;

public class Tools {

    public static void displayImageOriginal(Context ctx, ImageView img, @DrawableRes int drawable) {
        try {
            Glide.with(ctx).load(drawable)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img);
        } catch (Exception e) {
        }
    }

    public static int getProductItemColor(Activity activity, int imageResource){
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(),imageResource);
        Palette p = Palette.from(bitmap).generate();
        Log.d("Color HEX",String.format("#%06X", (0xFFFFFF & p.getLightVibrantColor(Color.WHITE)))+"");
        return  p.getLightVibrantColor(Color.WHITE);
    }


    public static int getColorWithAlpha(int color, float ratio) {
        int newColor;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }

}