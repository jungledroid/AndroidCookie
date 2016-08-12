package com.joez.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.joez.presenter.MainFunctionPresenter;
import com.joez.view.MainFunctionView;
import com.joez.ui.keyboard.KeyBoardActivity;
import com.joez.utils.OnRecyclerItemListener;

public class MainActivity extends BaseCookieActicity implements MainFunctionView{
    private static final String TAG = "MainActivity";

    private RecyclerView mRvCookie;
    private MainFunctionPresenter mPresenter;

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    protected void onCookieCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mPresenter = new MainFunctionPresenter();
        mPresenter.attach(this);
//        mPresenter.testVolley();
        mRvCookie = (RecyclerView) findViewById(R.id.rv_cookies);
        mRvCookie.setLayoutManager(new LinearLayoutManager(this));
        mRvCookie.setAdapter(new CookieAdapter(mPresenter.getFunctionList()));
        mRvCookie.addOnItemTouchListener(new OnRecyclerItemListener(mRvCookie) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                MainActivity.this.onItemClick(holder);
//                mCookies.add("dagger2");
            }

            @Override
            protected void onLongClick(RecyclerView.ViewHolder holder) {
                super.onLongClick(holder);
                Log.d(TAG, "onLongClick() called with: " + "holder = [" + holder.getAdapterPosition() + "]"+"---layoutPosition:"+holder.getLayoutPosition());
            }
        });

    }


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
        }else if(holder.getAdapterPosition() == 3){
            Intent intent = new Intent(MainActivity.this,HttpActivity.class);
            startActivity(intent);
        }else if (holder.getAdapterPosition() == 4) {
            startActivity(new Intent(MainActivity.this,Dagger2Aty.class));
        } else if (holder.getAdapterPosition() == 5) {
            startActivity(new Intent(MainActivity.this,RxJavaActivity.class));
        }
    }
}
