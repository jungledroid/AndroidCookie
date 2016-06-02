package com.joez.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.joez.utils.DisplayUtil;

/**
 * Created by JoeZ on 2016/6/2.
 */
public class CanvasTestView extends View {
    private static final int RULE_HEIGHT = 70;
    private static final int RULE_LEFT_MARGIN = 20;

    private static final int FIRST_LINE_MARGIN=5;
    private static final int DEFAULT_COUNT = 50;

    private static final int INTERVAL_LINE = 3;

    private int mRuleHeight,mRuleLeftMargin,mRuleFirstLineMargin;

    private int mMaxLineTop = 10;
    private int mMiddleLineTop = 7;
    private int mSmallLineTop = 5;
    private int mRulesBottom;
    private int mIntervalLine;

    private Paint mLinePaint = new Paint();

    public CanvasTestView(Context context) {
        this(context,null);
    }

    public CanvasTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData(){
        mRuleHeight = DisplayUtil.dp2px(getContext(),RULE_HEIGHT);
        mRuleLeftMargin = DisplayUtil.dp2px(getContext(),RULE_LEFT_MARGIN);
        mRuleFirstLineMargin = DisplayUtil.dp2px(getContext(),FIRST_LINE_MARGIN);
        mMaxLineTop = DisplayUtil.dp2px(getContext(),mMaxLineTop);
        mMiddleLineTop = DisplayUtil.dp2px(getContext(),mMiddleLineTop);
        mSmallLineTop = DisplayUtil.dp2px(getContext(),mSmallLineTop);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(2);
        mIntervalLine = DisplayUtil.dp2px(getContext(),INTERVAL_LINE);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mRulesBottom = getHeight();
//        drawLine(canvas);

//        canvas.drawColor(Color.BLUE);
//        canvas.drawRect(new Rect(0, 0, 400, 400), mLinePaint);
//        canvas.scale(0.5f, 0.5f,200,200);
        mLinePaint.setColor(Color.YELLOW);
        canvas.drawRect(new Rect(0, 0, 400, 400), mLinePaint);
        canvas.translate(200, 200);
        canvas.scale(0.5f, 0.5f);
        canvas.translate(-200, -200);
        mLinePaint.setColor(Color.BLACK);
        canvas.drawRect(new Rect(0, 0, 400, 400), mLinePaint);
    }

    private void drawLine(Canvas canvas){
        canvas.save();
        canvas.translate(mRuleFirstLineMargin,0);
        int top = mMaxLineTop;
        for(int index = 0;index<DEFAULT_COUNT;index++){
            if(index % 10 == 0){
                top = mMaxLineTop;
            }else if(index%5 == 0){
                top = mMiddleLineTop;
            }else{
                top = mSmallLineTop;
            }
            canvas.drawLine(0,mRulesBottom,0,mRulesBottom - top,mLinePaint);
            canvas.translate(mIntervalLine,0);

        }
        canvas.restore();
    }
}
