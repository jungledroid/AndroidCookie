package com.joez.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

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

    public FloatStarsView(Context context) {
        this(context,null);
    }

    public FloatStarsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int mSlowSpeed,mMiddleSpeed,mHighSpeed;
    private List<StarInfo> infos;
    private void init(){
        Resources resources = getContext().getResources();
        mSlowSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0.5f,resources.getDisplayMetrics());
        mMiddleSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0.75f,resources.getDisplayMetrics());
        mHighSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,1f,resources.getDisplayMetrics());
        infos = new ArrayList<>();
        StarInfo info = null;
        for (int i = 0;i<15;i++){
            info = new StarInfo();


            infos.add(info);
        }

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
        float derection;
    }
}
