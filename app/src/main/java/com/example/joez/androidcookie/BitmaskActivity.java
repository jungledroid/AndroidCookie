package com.example.joez.androidcookie;

import android.support.annotation.IntDef;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BitmaskActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "BitmaskActivity";
    private static final String MODE_SINGLE="SingleMode";
    private static final String MODE_MULTI = "MultiMode";
    private static final String MODE_FILLAFTER="FillAfterMode";
    private AnimateModeController mAnimateController;
    private CheckBox mCbRotation,mCbTransitionY,mCbTransitionX;
    private Button mBtnRotation,mBtnTransitionY,mBtnTransitonX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        mAnimateController = new AnimateModeController(findViewById(R.id.iv_image));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
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
                mAnimateController.animateImageByFlag(AnimateModeController.ROTATION_MASK);
                break;
            case R.id.btn_trantionY:
                mAnimateController.animateImageByFlag(AnimateModeController.TRANSITIONY_MASK);
                break;
            case R.id.btn_transitonX:
                mAnimateController.animateImageByFlag(AnimateModeController.TRANSITIONX_MASK);
                break;
            case R.id.btn_mode:

                boolean isFillAfterMode = mAnimateController.hasStatus(AnimateModeController.STATE_MODE_FILAFTER_MASK);
                boolean isCheckable = false;
                if(isFillAfterMode){
                    ((Button)v).setText(MODE_FILLAFTER);
                    mAnimateController.animateWithMode(AnimateModeController.STATE_MODE_SINGLE_MASK);
                    ((Button) v).setText( MODE_SINGLE);
                }else {
                    boolean isSingleMode = mAnimateController.hasStatus(AnimateModeController.STATE_MODE_SINGLE_MASK);
                    isCheckable = isSingleMode;
                    mAnimateController.animateWithMode(isSingleMode?AnimateModeController.STATE_MODE_MULTI_MASK:AnimateModeController.STATE_MODE_FILAFTER_MASK);
                    ((Button) v).setText(isSingleMode ?MODE_MULTI:MODE_FILLAFTER);
                }
                updateViewEnable(isCheckable);
                break;
            case R.id.btn_animate:
                int flag = 0;
                if(mCbRotation.isChecked()){
                    flag|=AnimateModeController.ROTATION_MASK;
                }
                if(mCbTransitionY.isChecked()){
                    flag|=AnimateModeController.TRANSITIONY_MASK;
                }
                if(mCbTransitionX.isChecked()){
                    flag|=AnimateModeController.TRANSITIONX_MASK;
                }
                mAnimateController.animateImageByFlag(flag);

        }
    }

    private static final class AnimateModeController {
        private int mViewFlag = STATE_MODE_MULTI_MASK;
        public static final int ROTATION_MASK = 0x1;
        public static final int TRANSITIONY_MASK =0x2;
        public static final int TRANSITIONX_MASK =0x4;

        private static final int ANIMATE_CLEAR_MASK =0x7;

        public static final int STATE_MODE_SINGLE_MASK =0x8;
        public static final int STATE_MODE_MULTI_MASK =0x0;
        public static final int STATE_MODE_FILAFTER_MASK=0x10;

        public static final int STATE_CLEAR_MASK =0x1F;
        private ViewPropertyAnimatorCompat mViewCompat;
        public AnimateModeController(View view){
            mViewCompat = ViewCompat.animate(view);
        }

        @IntDef(flag=true,value={ROTATION_MASK, TRANSITIONY_MASK, TRANSITIONX_MASK,STATE_MODE_SINGLE_MASK,STATE_MODE_FILAFTER_MASK,STATE_MODE_MULTI_MASK})
        @Retention(RetentionPolicy.SOURCE)
        public @interface AnimateState{}

        private void animateWithMode(@AnimateState int flag){
            clearAllSate();
            mViewFlag |= flag;
            mViewCompat.rotation(0).translationX(0).translationY(0).setDuration(500).start();
        }

        private void revertRequestFlag(@AnimateState int flag){
            if(hasStatus(flag)){
                mViewFlag&=~flag;
            }else{
                mViewFlag|=flag;
            }
        }
        private void animateImageByFlag(@AnimateState int flag){
            boolean isFilterAfterMode = hasStatus(STATE_MODE_FILAFTER_MASK);
            if(isFilterAfterMode){
                revertRequestFlag(flag);
                mViewCompat.rotation(hasStatus(ROTATION_MASK)?30:0)
                        .translationX(hasStatus(TRANSITIONX_MASK)?200:0)
                        .translationY(hasStatus(TRANSITIONY_MASK)?200:0);
            }else {
                clearAnimateState();
                mViewCompat.rotation(0).translationX(0).translationY(0);
                if(requestHasState(flag,ROTATION_MASK)){
                    mViewCompat.rotation(30);
                }
                if(requestHasState(flag,TRANSITIONY_MASK)){
                    mViewCompat.translationY(200);
                }

                if(requestHasState(flag,TRANSITIONX_MASK)){
                    mViewCompat.translationX(200);
                }
            }
            mViewCompat.setDuration(500).start();
        }



        private boolean requestHasState(@AnimateState int request, @AnimateState int targetState){
            return (request&targetState) != 0;
        }

        private boolean hasStatus(@AnimateState int flag){
            return (mViewFlag&flag)!=0;
        }

        private void clearAnimateState(){
            mViewFlag&=~ANIMATE_CLEAR_MASK;
        }

        private void clearAllSate(){
            mViewFlag&=~STATE_CLEAR_MASK;
        }

    }

}
