package com.joez.ui;

import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import com.joez.presenter.AnimatePresenter;

public class BitmaskActivity extends BaseCookieActicity implements View.OnClickListener{
    private static final String TAG = "BitmaskActivity";
    private static final String MODE_SINGLE="SingleMode";
    private static final String MODE_MULTI = "MultiMode";
    private static final String MODE_FILLAFTER="FillAfterMode";
    private AnimatePresenter mAnimateController;
    private CheckBox mCbRotation,mCbTransitionY,mCbTransitionX;
    private Button mBtnRotation,mBtnTransitionY,mBtnTransitonX;

    @Override
    protected void onCookieCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_bitmask);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(TAG);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(TAG);

        mBtnRotation=(Button) findViewById(R.id.btn_rotation);
        mBtnRotation.setOnClickListener(this);
        mBtnTransitionY = (Button)findViewById(R.id.btn_trantionY);
        mBtnTransitionY.setOnClickListener(this);
        mBtnTransitonX = (Button)findViewById(R.id.btn_transitonX);
        mBtnTransitonX.setOnClickListener(this);
        findViewById(R.id.btn_mode).setOnClickListener(this);
        findViewById(R.id.btn_animate).setOnClickListener(this);
        mCbRotation = (CheckBox)findViewById(R.id.cb_rotation);
        mCbTransitionY = (CheckBox)findViewById(R.id.cb_tanstionY);
        mCbTransitionX = (CheckBox)findViewById(R.id.cb_transitionX);

        mAnimateController = new AnimatePresenter(findViewById(R.id.iv_image));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                Looper.loop();
            }
        });
        return true;
    }

    private void updateViewEnable(boolean isCheckable){
        if(!isCheckable) {
            mCbRotation.setChecked(isCheckable);
            mCbTransitionX.setChecked(isCheckable);
            mCbTransitionY.setChecked(isCheckable);
        }
        mCbRotation.setClickable(isCheckable);
        mCbTransitionX.setClickable(isCheckable);
        mCbTransitionY.setClickable(isCheckable);
        mBtnRotation.setClickable(!isCheckable);
        mBtnTransitonX.setClickable(!isCheckable);
        mBtnTransitionY.setClickable(!isCheckable);
    }

    @Override
    public void onClick(View v) {
        v.clearAnimation();
        switch (v.getId()){
            case R.id.btn_rotation:
                mAnimateController.animateImageByFlag(AnimatePresenter.ROTATION_MASK);
                break;
            case R.id.btn_trantionY:
                mAnimateController.animateImageByFlag(AnimatePresenter.TRANSITIONY_MASK);
                break;
            case R.id.btn_transitonX:
                mAnimateController.animateImageByFlag(AnimatePresenter.TRANSITIONX_MASK);
                break;
            case R.id.btn_mode:

                boolean isFillAfterMode = mAnimateController.hasStatus(AnimatePresenter.STATE_MODE_FILAFTER_MASK);
                boolean isCheckable = false;
                if(isFillAfterMode){
                    ((Button)v).setText(MODE_FILLAFTER);
                    mAnimateController.animateWithMode(AnimatePresenter.STATE_MODE_SINGLE_MASK);
                    ((Button) v).setText( MODE_SINGLE);
                }else {
                    boolean isSingleMode = mAnimateController.hasStatus(AnimatePresenter.STATE_MODE_SINGLE_MASK);
                    isCheckable = isSingleMode;
                    mAnimateController.animateWithMode(isSingleMode? AnimatePresenter.STATE_MODE_MULTI_MASK: AnimatePresenter.STATE_MODE_FILAFTER_MASK);
                    ((Button) v).setText(isSingleMode ?MODE_MULTI:MODE_FILLAFTER);
                }
                updateViewEnable(isCheckable);
                break;
            case R.id.btn_animate:
                int flag = 0;
                if(mCbRotation.isChecked()){
                    flag|= AnimatePresenter.ROTATION_MASK;
                }
                if(mCbTransitionY.isChecked()){
                    flag|= AnimatePresenter.TRANSITIONY_MASK;
                }
                if(mCbTransitionX.isChecked()){
                    flag|= AnimatePresenter.TRANSITIONX_MASK;
                }
                mAnimateController.animateImageByFlag(flag);

        }
    }

}
