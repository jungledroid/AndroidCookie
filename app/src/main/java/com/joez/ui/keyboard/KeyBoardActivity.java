package com.joez.ui.keyboard;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.joez.ui.BaseCookieActicity;
import com.joez.ui.R;

public class KeyBoardActivity extends BaseCookieActicity {
    private static final String TAG = "KeyBoardActivity";
    private static final int MINI_KEY_BOARD_HEIGHT = 150;
    private View mVHolder;

    @Override
    protected void onCookieCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_key_board);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(TAG);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(TAG);
        mVHolder = findViewById(R.id.v_holder);
        final View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private final Rect windowVisibleDisplayFrame = new Rect();
            private int mLastVisibleDecorViewHeight;

            @Override
            public void onGlobalLayout() {
                decorView.getWindowVisibleDisplayFrame(windowVisibleDisplayFrame);
                final int visibleDecorViewHeight = windowVisibleDisplayFrame.height();
                if(mLastVisibleDecorViewHeight != 0){
                    if(mLastVisibleDecorViewHeight>= visibleDecorViewHeight+MINI_KEY_BOARD_HEIGHT){
                        int currentKeyboardHeight = decorView.getHeight() - windowVisibleDisplayFrame.bottom;
                        onSoftKeyboardShow(currentKeyboardHeight);
                    }else if(mLastVisibleDecorViewHeight <= visibleDecorViewHeight-MINI_KEY_BOARD_HEIGHT){
                        int currentKeyboardHeight = visibleDecorViewHeight - mLastVisibleDecorViewHeight;
                        onSoftKeyboardHide(currentKeyboardHeight);
                    }
                }
                mLastVisibleDecorViewHeight = visibleDecorViewHeight;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void onSoftKeyboardShow(int height){
        mVHolder.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,height));
        mVHolder.setVisibility(View.VISIBLE);
    }

    private void onSoftKeyboardHide(int height){
        mVHolder.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,0));
        mVHolder.setVisibility(View.GONE);
    }
}
