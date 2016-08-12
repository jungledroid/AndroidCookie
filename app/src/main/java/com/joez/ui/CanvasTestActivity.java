package com.joez.ui;

import android.os.Bundle;
import com.joez.widget.CanvasTestView;
import com.joez.widget.FloatStarsView;
import com.joez.widget.PathTestView;

public class CanvasTestActivity extends BaseCookieActicity {

    CanvasTestView mCanvasTestView;
    PathTestView mPathTestView;
    FloatStarsView mStarView;

    @Override
    protected void onCookieCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_canvas_test);
//        mCanvasTestView = (CanvasTestView)findViewById(R.id.v_canvas);
//        mPathTestView = (PathTestView)findViewById(R.id.v_path);
//        mCanvasTestView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPathTestView.startAnimation();
//            }
//        });

//        WindowInsets
        mStarView=(FloatStarsView) findViewById(R.id.fsv_stars);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStarView.startAnimation();
    }
}
