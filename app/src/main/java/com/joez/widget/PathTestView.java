package com.joez.widget;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.joez.ui.R;

/**
 * Created by JoeZ on 2016/6/3.
 */
public class PathTestView extends View{
    private Path mPath = new Path();
    private Paint mPaint = new Paint();

    public PathTestView(Context context) {
        this(context,null);
    }

    public PathTestView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public PathTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPath();
    }

    private void initPath(){
        mPaint.setColor(Color.RED);
        Log.e(TAG, "initPath: -----------");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawCircle(canvas);
//        testFillType(canvas);
//        testPath(canvas);
//        testBas(canvas);
//        testPathMessure(canvas);
        drawBitmapGirl(canvas);
    }

    private void drawBitmapGirl(Canvas canvas){
        Bitmap image = ((BitmapDrawable)ContextCompat.getDrawable(getContext(), R.drawable.img_cover_recommendation)).getBitmap();
        canvas.drawBitmap(image,new Rect(0,0,image.getWidth(),image.getHeight()),
                new Rect(mTotalWidth/4,mTotalHeight/4,3*mTotalWidth/4,3*mTotalHeight/4),mPaint);
    }
    private float[] mCurrentPosition = {250f,250f};
    private PathMeasure mPathMessure;

    private void testPathMessure(Canvas canvas){
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();
        mPath.moveTo(250,250);
        mPath.quadTo(500,180,250,800);
        mPath.quadTo(0,180,250,250);
        mPathMessure = new PathMeasure(mPath,false);
        canvas.drawPath(mPath,mPaint);
        canvas.drawCircle(mCurrentPosition[0],mCurrentPosition[1],10,mPaint);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void startAnimation(){
//        ValueAnimator animator = ValueAnimator.ofFloat(0,mPathMessure.getLength());
//        animator.setDuration(10000);
//        animator.setInterpolator(new DecelerateInterpolator());
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                mPathMessure.getPosTan(value,mCurrentPosition,null);
//                postInvalidate();
//            }
//        });
//        animator.start();
    }


    private void testBas(Canvas canvas){
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath.moveTo(0,0);
        mPath.quadTo(50,50,100,0);
        canvas.drawPath(mPath,mPaint);
    }

    private void testPath(Canvas canvas){
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath.moveTo(0,0);
        mPath.lineTo(150,150);
        mPath.arcTo(new RectF(0,400,500,500),0,90,true);

        mPath.close();
        canvas.drawPath(mPath,mPaint);
    }

    private void testFillType(Canvas canvas){
        canvas.save();
        mPath.addCircle(200,200,200, Path.Direction.CW);
        mPath.addCircle(400,400,200, Path.Direction.CW);
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        RectF rf = new RectF();
        mPath.computeBounds(rf,false);
        Log.e(TAG, "testFillType: compute bounds :"+rf.toString() );
        canvas.restore();
        canvas.drawPath(mPath,mPaint);
    }

    private void drawCircle(Canvas canvas){
        mPath.addCircle(400,400,200, Path.Direction.CW);
        mPath.addRect(new RectF(50, 50, 250, 200), Path.Direction.CW);
        canvas.drawPath(mPath,mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawTextOnPath("hello",mPath,0,0,mPaint);
    }


    private int mTotalWidth,mTotalHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
    }

    private static final String TAG = "PathTestView";
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState",super.onSaveInstanceState());
        bundle.putInt("paint",Color.RED);
        Log.e(TAG, "onSaveInstanceState: ");
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            int color = bundle.getInt("paint");
//            mPaint.setColor(color);
        }
        super.onRestoreInstanceState(state);
    }
}
