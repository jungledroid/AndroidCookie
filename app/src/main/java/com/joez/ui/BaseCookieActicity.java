package com.joez.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.joez.presenter.Navigator;
import com.joez.utils.SystemBarHelper;

/**
 * Created by JoeZ on 2016/5/24.
 */
public class BaseCookieActicity extends AppCompatActivity implements Navigator {

    private static final String TAG = "BaseCookieActicity";

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCookieCreate(savedInstanceState);
        SystemBarHelper.setStatusBarColor(this,getResources().getColor(R.color.colorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getClass().getSimpleName());
        setSupportActionBar(toolbar);
        final View rootView = ((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        ViewCompat.setFitsSystemWindows(rootView,true);
        //or
        ViewCompat.setOnApplyWindowInsetsListener(rootView,new OnApplyWindowInsetsListener(){

            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                v.setPadding(0,v.getTop()+ SystemBarHelper.getStatusBarHeight(BaseCookieActicity.this),0,0);
                return insets.consumeSystemWindowInsets();
            }
        });
        Log.e(TAG, "onCreate: "+getSupportActionBar().getTitle() );
    }

    protected void onCookieCreate(@Nullable Bundle savedInstanceState){

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.e(TAG, "onBackPressed: count:"+count );
        if(count==1){
            this.finish();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void addFragment(Fragment fragment) {
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.add(R.id.content,fragment,fragment.getClass().getSimpleName());
        transition.addToBackStack(null);
        transition.commit();
    }
}
