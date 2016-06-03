package com.joez.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas){
        mPath.addCircle(400,400,200, Path.Direction.CW);
        mPath.addRect(new RectF(50, 50, 250, 200), Path.Direction.CW);
        canvas.drawPath(mPath,mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawTextOnPath("hello",mPath,0,0,mPaint);
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
            mPaint.setColor(color);
        }
        super.onRestoreInstanceState(state);
    }
}
