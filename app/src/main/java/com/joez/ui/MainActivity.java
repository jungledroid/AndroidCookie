package com.joez.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.joez.ui.keyboard.KeyBoardActivity;
import com.joez.utils.OnRecyclerItemListener;

public class MainActivity extends BaseCookieActicity {
    private static final String TAG = "MainActivity";

    private RecyclerView mRvCookie;

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    protected void onCookieCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mRvCookie = (RecyclerView) findViewById(R.id.rv_cookies);
        mRvCookie.setLayoutManager(new LinearLayoutManager(this));
        mRvCookie.setAdapter(new CookieAdapter());
        mRvCookie.addOnItemTouchListener(new OnRecyclerItemListener(mRvCookie) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                if(holder.getAdapterPosition() == 0) {
                    Intent intent = new Intent(MainActivity.this, KeyBoardActivity.class);
                    startActivity(intent);
                }else if(holder.getAdapterPosition()==1){
                    Intent intent = new Intent(MainActivity.this,BitmaskActivity.class);
                    startActivity(intent);
                }else if(holder.getAdapterPosition() == 2){
                    Intent intent = new Intent(MainActivity.this,CanvasTestActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            protected void onLongClick(RecyclerView.ViewHolder holder) {
                super.onLongClick(holder);
                Log.d(TAG, "onLongClick() called with: " + "holder = [" + holder.getAdapterPosition() + "]"+"---layoutPosition:"+holder.getLayoutPosition());
            }
        });

    }


}
