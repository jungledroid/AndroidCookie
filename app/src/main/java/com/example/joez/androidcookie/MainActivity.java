package com.example.joez.androidcookie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRvCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvCookie = (RecyclerView) findViewById(R.id.rv_cookie);
        mRvCookie.setLayoutManager(new LinearLayoutManager(this));
        mRvCookie.setAdapter(new CookieAdapter());
    }
}
