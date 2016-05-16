package com.example.joez.androidcookie.keyboard;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joez.androidcookie.R;

public class KeyBoardActivity extends AppCompatActivity {
    private static final String TAG = "KeyBoardActivity";
    private static final int MINI_KEY_BOARD_HEIGHT = 150;
    private View mVHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle(TAG);
        setSupportActionBar(toolbar);
        EditText etInfo = (EditText)findViewById(R.id.et_info);
        TextView tvImportance = (TextView)findViewById(R.id.tv_importance);
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
                        int currentKeyboardHeight = decorView.getHeight() - mLastVisibleDecorViewHeight;
                        onSoftKeyboardHide(currentKeyboardHeight);
                    }
                }
                mLastVisibleDecorViewHeight = visibleDecorViewHeight;
            }
        });
    }

    private void onSoftKeyboardShow(int height){
        Log.e(TAG, "onSoftKeyboardShow() called with: " + "height = [" + height + "]");
        mVHolder.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,height));
        mVHolder.setVisibility(View.VISIBLE);
        mVHolder.invalidate();
    }

    private void onSoftKeyboardHide(int height){
        Log.e(TAG, "onSoftKeyboardHide() called with: " + "height = [" + height + "]");
        mVHolder.setVisibility(View.GONE);
    }
}
