package com.example.joez.androidcookie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.joez.androidcookie.keyboard.KeyBoardActivity;
import com.joez.utils.OnRecyclerItemListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView mRvCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvCookie = (RecyclerView) findViewById(R.id.rv_cookie);
        mRvCookie.setLayoutManager(new LinearLayoutManager(this));
        mRvCookie.setAdapter(new CookieAdapter());
        mRvCookie.addOnItemTouchListener(new OnRecyclerItemListener(mRvCookie) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                Log.e(TAG, "onItemClick: position:"+holder.getAdapterPosition() );
                Log.e(TAG, "onItemClick: layout position:"+holder.getLayoutPosition() );
                if(holder.getAdapterPosition() == 0) {
                    Intent intent = new Intent(MainActivity.this, KeyBoardActivity.class);
                    startActivity(intent);
                }
            }
        });


    }


}
