package com.joez.presenter;

import android.support.annotation.IntDef;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by JoeZ on 2016/5/27.
 */
public class AnimatePresenter {

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
    public AnimatePresenter(View view){
        mViewCompat = ViewCompat.animate(view);
    }

    @IntDef(flag=true,value={ROTATION_MASK, TRANSITIONY_MASK, TRANSITIONX_MASK,STATE_MODE_SINGLE_MASK,STATE_MODE_FILAFTER_MASK,STATE_MODE_MULTI_MASK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimateState{}

    public void animateWithMode(@AnimateState int flag){
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

    interface AnimateCallback{
        void rotationView(float value);
        void translationYView(float value);
        void translationXView(float value);
    }

    private AnimateCallback animateCallback = new AnimateCallback() {
        @Override
        public void rotationView(float value) {
            mViewCompat.rotation(value);
        }

        @Override
        public void translationYView(float value) {
            mViewCompat.translationY(value);
        }

        @Override
        public void translationXView(float value) {
            mViewCompat.translationX(value);
        }
    };

    void setAnimateCallback(AnimateCallback callback){
        animateCallback = callback;
    }


    public void animateImageByFlag(@AnimateState int flag){
        boolean isFilterAfterMode = hasStatus(STATE_MODE_FILAFTER_MASK);
        if(isFilterAfterMode){
            revertRequestFlag(flag);
            animateCallback.rotationView(hasStatus(ROTATION_MASK)?30:0);
            animateCallback.translationXView(hasStatus(TRANSITIONX_MASK)?200:0);
            animateCallback.translationYView(hasStatus(TRANSITIONY_MASK)?200:0);
        }else {
            clearAnimateState();
            animateCallback.rotationView(0);
            animateCallback.translationXView(0);
            animateCallback.translationYView(0);
            if(requestHasState(flag,ROTATION_MASK)){
                animateCallback.rotationView(30);
            }
            if(requestHasState(flag,TRANSITIONY_MASK)){
                animateCallback.translationYView(200);
            }
            if(requestHasState(flag,TRANSITIONX_MASK)){
                animateCallback.translationXView(200);
            }
        }
        mViewCompat.setDuration(500).start();
    }



    private boolean requestHasState(@AnimateState int request, @AnimateState int targetState){
        return (request&targetState) != 0;
    }

    public boolean hasStatus(@AnimateState int flag){
        return (mViewFlag&flag)!=0;
    }

    private void clearAnimateState(){
        mViewFlag&=~ANIMATE_CLEAR_MASK;
    }

    void clearAllSate(){
        mViewFlag&=~STATE_CLEAR_MASK;
    }
}
