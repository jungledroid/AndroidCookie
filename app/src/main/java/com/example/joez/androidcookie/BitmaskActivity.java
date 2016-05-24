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
import android.widget.ImageView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BitmaskActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "BitmaskActivity";
    private AnimateController mAnimateController;

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

        ImageView ivTarget = (ImageView)findViewById(R.id.iv_image);
        findViewById(R.id.btn_rotation).setOnClickListener(this);
        findViewById(R.id.btn_trantionY).setOnClickListener(this);
        findViewById(R.id.btn_transitonX).setOnClickListener(this);
        findViewById(R.id.btn_mode).setOnClickListener(this);

        mAnimateController = new AnimateController(ivTarget);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        v.clearAnimation();
        switch (v.getId()){
            case R.id.btn_rotation:
                mAnimateController.animateImageByFlag(AnimateController.ROTATION_MASK);
                break;
            case R.id.btn_trantionY:
                mAnimateController.animateImageByFlag(AnimateController.TRANSITIONY_MASK);
                break;
            case R.id.btn_transitonX:
                mAnimateController.animateImageByFlag(AnimateController.TRANSITIONX_MASK);
                break;
            case R.id.btn_mode:
                boolean isSingleMode = mAnimateController.animateImageByFlag(AnimateController.STATE_MODE_SINGLE_MASK);
                ((Button)v).setText(isSingleMode?"SingleMode":"MultiMode");
                break;
        }
    }

    private static class AnimateController{
        private int mViewFlag;
        public static final int ROTATION_MASK = 0x1;
        public static final int TRANSITIONY_MASK =0x2;
        public static final int TRANSITIONX_MASK =0x4;
        private static final int ANIMATE_STATE_MASK=0x7;
        public static final int STATE_MODE_SINGLE_MASK =0x8;
        private ViewPropertyAnimatorCompat mViewCompat;

        public AnimateController(View view){
            mViewCompat = ViewCompat.animate(view);
        }

        @IntDef({ROTATION_MASK, TRANSITIONY_MASK, TRANSITIONX_MASK,STATE_MODE_SINGLE_MASK})
        @Retention(RetentionPolicy.SOURCE)
        public @interface AnimateState{}

        private boolean animateImageByFlag(@AnimateState int flag){
            boolean isSingleMode = hasStatus(STATE_MODE_SINGLE_MASK);
            boolean hasStatus = hasStatus(flag);
            if(isSingleMode){
                clearAniamteState();
                mViewCompat.rotation(0).translationX(0).translationY(0);
            }
            if(hasStatus){
                mViewFlag&=~flag;
            }else{
                mViewFlag|=flag;
            }
            switch (flag){
                case ROTATION_MASK:
                    mViewCompat.rotation(hasStatus?0:30);
                    break;
                case TRANSITIONY_MASK:
                    mViewCompat.translationY(hasStatus?0:200);
                    break;
                case TRANSITIONX_MASK:
                    mViewCompat.translationX(hasStatus?0:200);
                    break;
                case STATE_MODE_SINGLE_MASK:
                    clearAniamteState();
                    break;
            }
            mViewCompat.setDuration(500).start();
            return !isSingleMode;
        }

        private boolean hasStatus(int flag){
            return (mViewFlag&flag)>0;
        }

        private void clearAniamteState(){
            mViewFlag&=~ANIMATE_STATE_MASK;
        }
    }

}
