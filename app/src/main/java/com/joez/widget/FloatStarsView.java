package com.joez.widget;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.joez.ui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Administrator on 2016/6/6 0006.
 */
public class FloatStarsView extends View{
    private static final float[][] STARS_LOCATION={
            {0.5f, 0.2f}, {0.68f, 0.35f}, {0.5f, 0.05f},
            {0.15f, 0.15f}, {0.5f, 0.5f}, {0.15f, 0.8f},
            {0.2f, 0.3f}, {0.77f, 0.4f}, {0.75f, 0.5f},
            {0.8f, 0.55f}, {0.9f, 0.6f}, {0.1f, 0.7f},
            {0.1f, 0.1f}, {0.7f, 0.8f}, {0.5f, 0.6f}
    };
    private Paint mStarPaint;

    private static Bitmap mStar;

    public FloatStarsView(Context context) {
        this(context,null);
    }

    public FloatStarsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int mTotalWidth,mTotalHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(infos == null || infos.size() == 0){
            return;
        }
        Rect srcRect = new Rect(0,0,mStar.getWidth(),mStar.getHeight());
        Rect destRect = null;
        for (StarInfo info : infos) {
            canvas.save();
            destRect = new Rect();
            destRect.left= (int) info.startX;
            destRect.top= (int) info.startY;
            destRect.right=destRect.left+mStar.getWidth()/2;
            destRect.bottom=destRect.top+mStar.getHeight()/2;
            canvas.drawBitmap(mStar,srcRect,destRect,mStarPaint);
            canvas.restore();
            Log.e(TAG, "onDraw: ---draw:destrect: "+destRect.toString());
            resetStarFloat(info);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void startAnimation(){
        ValueAnimator animator = ValueAnimator.ofInt(0,100);
        animator.setDuration(60000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
            }
        });
        animator.start();
    }



    private void resetStarFloat(StarInfo starInfo) {
        switch (starInfo.derection) {
            case 0:
                starInfo.startX -= starInfo.speed;
                if(starInfo.startX<=0){
                    starInfo.derection = 1;
                }
                break;
            case 1:
                starInfo.startX += starInfo.speed;
                if(starInfo.startX>=mTotalWidth){
                    starInfo.derection=0;
                }
                break;
            case 2:
                starInfo.startY -= starInfo.speed;
                if(starInfo.startY<=0){
                    starInfo.derection=3;
                }
                break;
            case 3:
                starInfo.startY += starInfo.speed;
                if(starInfo.startY>=mTotalHeight){
                    starInfo.derection=2;
                }
                break;
            default:
                break;
        }
    }

    private static final String TAG = "FloatStarsView";
    private int mSlowSpeed,mMiddleSpeed,mHighSpeed;
    private List<StarInfo> infos;
    private void init(){
        mStarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Resources resources = getContext().getResources();
        mStar = ((BitmapDrawable)resources.getDrawable(R.mipmap.ic_launcher)).getBitmap();
        mSlowSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0.5f,resources.getDisplayMetrics());
        mMiddleSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0.75f,resources.getDisplayMetrics());
        mHighSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,1f,resources.getDisplayMetrics());
        infos = new ArrayList<>();
        StarInfo info = null;
        for (int i = 0;i<15;i++){
            info = new StarInfo();
            info.derection = getRandomDerection();
            info.speed = getSpeed();
            info.startX=STARS_LOCATION[i][0]*mTotalWidth;
            info.startY = STARS_LOCATION[i][1]*mTotalHeight;
            infos.add(info);
            Log.e(TAG, "init: ------add"+i );
        }
    }

    private int getSpeed(){
        int speed = mSlowSpeed;
        Random random = new Random();
        switch (random.nextInt(3)){
            case 0:
                speed = mSlowSpeed;
                break;
            case 1:
                speed = mMiddleSpeed;
                break;
            case 2:
                speed = mHighSpeed;
                break;

        }

        return speed;
    }

    private float getStarSizePercent(int start, int end){
        float nextFloat = (float) Math.random();
        if (start < nextFloat && nextFloat < end) {
            return nextFloat;
        } else {
            return (float) Math.random();
        }
    }

    private int getRandomDerection(){
        Random random = new Random();
        return random.nextInt(4);
    }

    private static final class StarInfo{
        float startX;
        float startY;
        float alpha;
        float speed;
        int derection;
    }
}
