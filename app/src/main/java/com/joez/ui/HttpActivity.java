package com.joez.ui;

import android.support.annotation.Nullable;
import android.os.Bundle;
import com.joez.ui.fragment.VolleyHomeFragment;

public class HttpActivity extends BaseCookieActicity{
    private static final String TAG = "HttpActivity";


    @Override
    protected void onCookieCreate(@Nullable Bundle savedInstanceState) {
        super.onCookieCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        if(savedInstanceState == null){
            addFragment(new VolleyHomeFragment());
        }
    }


}
