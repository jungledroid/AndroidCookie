package com.joez.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.joez.ui.R;

/**
 * Created by Administrator on 2016/6/10 0010.
 * https://github.com/laobie/StatusBarUtil
 * https://github.com/H07000223/FlycoSystemBar
 */
public class SystemBarHelper {

    private SystemBarHelper(){}

    public static void setStatusBarColor(Activity activity,@ColorInt int statusBarColor){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().setStatusBarColor(statusBarColor);
            return;
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            View statuBiew = activity.getWindow().getDecorView().findViewById(R.id.statusbar_view);
            if(statuBiew == null){
                statuBiew = new View(activity);
                statuBiew.setId(R.id.statusbar_view);
                statuBiew.setBackgroundColor(statusBarColor);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getStatusBarHeight(activity));
                ViewGroup contentView = (ViewGroup) activity.getWindow().getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
                contentView.addView(statuBiew,lp);
            }else{
                statuBiew.setBackgroundColor(statusBarColor);
            }
            setTranslucentView(activity.getWindow());
        }
    }

    private static void setTranslucentView(Window activity){
        View transluentStatus = activity.getDecorView().findViewById(R.id.transluent_view);
        if(transluentStatus == null){
            transluentStatus = new View(activity.getContext());
            transluentStatus.setId(R.id.transluent_view);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity.getContext()));
            ViewGroup contentView = (ViewGroup) activity.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.addView(transluentStatus,lp);
        }
        transluentStatus.setBackgroundColor(Color.argb((int) (0.5 * 255), 0, 0, 0));
    }


    public static void immersiveStatusBar(Window window, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {

            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ViewGroup decorView = (ViewGroup) window.getDecorView();
        ViewGroup contentView = (ViewGroup) window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
        View rootView = contentView.getChildAt(0);
        int statusBarHeight = getStatusBarHeight(window.getContext());
        if (rootView != null) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) rootView.getLayoutParams();
            ViewCompat.setFitsSystemWindows(rootView, true);
            lp.topMargin = -statusBarHeight;
            rootView.setLayoutParams(lp);
        }

        setTranslucentView(window);
    }

    public static int getStatusBarHeight(Context ctx) {
        int result = 0;
        int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ctx.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getNavigationBarHeight(Context ctx) {
        int result = 0;
        int resourceId = ctx.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ctx.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
