package com.joez.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.joez.ui.R;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/6/10 0010.
 */
public class Utils {
    public static final String TAG = "FitSystemBar";
    public static boolean DEBUG = false;

    public static boolean hasSoftKeys(Context ctx){
        boolean hasSoftwareKeys= false;
        WindowManager manager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            Display d = manager.getDefaultDisplay();

            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            d.getRealMetrics(realDisplayMetrics);

            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);

            int displayHeight = displayMetrics.heightPixels;
            int displayWidth = displayMetrics.widthPixels;

            hasSoftwareKeys =  (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        }else{
//            boolean hasMenuKey = ViewConfiguration.get(ctx).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
//            hasSoftwareKeys = !hasMenuKey && !hasBackKey;
        }
        return hasSoftwareKeys;
    }


    public static void paddingToNavigationBar(View view){
//        if (!hasSoftKeys(view.getContext())||!(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT))return;
        Method method = null;
        try {
            method = view.getClass().getMethod("setClipToPadding", boolean.class);
        } catch (NoSuchMethodException e) {
            return;
        }
        try {
            method.invoke(view,false);
//            view.setPadding(0,0,0,getNavigationBarHeight(view.getContext()));
        } catch (IllegalAccessException e) {
            return;
        } catch (InvocationTargetException e) {
            return;
        }
    }

    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            if (decorView.getChildCount() > 0) {
//                decorView.getChildAt(0).setBackgroundColor(activity.getResources().getColor(color));
                decorView.getChildAt(0).setBackgroundColor(Color.parseColor("#20000000"));
            } else {
//                StatusBarView statusView = createStatusBarView(activity, color, statusBarAlpha);
//                decorView.addView(statusView, 0);
            }
//            setRootView(activity);
        }
    }
}
