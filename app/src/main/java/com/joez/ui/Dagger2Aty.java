package com.joez.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.joez.dagger.ActivityModule;
import com.joez.dagger.DaggerActivityComponent;
import com.joez.dagger.DaggerPresenter;

import javax.inject.Inject;

/**
 * Created by gzzyj on 2016/7/29.
 */
public class Dagger2Aty extends BaseCookieActicity{
    Button mBtn;
    @Inject
    DaggerPresenter mPresenter;
    @Override
    protected void onCookieCreate(@Nullable Bundle savedInstanceState) {
        super.onCookieCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        mBtn = (Button) findViewById(R.id.btn_mode);
        DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
        mPresenter.showUserName();
    }

    private static final String TAG = "Dagger2Aty";
    public void showUserName(String name) {
        Log.e(TAG, "showUserName:--name: "+name);
        mBtn.setText(name);
    }
}
