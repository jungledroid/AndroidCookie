package com.joez.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.joez.widget.CanvasTestView;
import com.joez.widget.FloatStarsView;
import com.joez.widget.PathTestView;

public class CanvasTestActivity extends AppCompatActivity {

    CanvasTestView mCanvasTestView;
    PathTestView mPathTestView;
    FloatStarsView mStarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_test);
//        mCanvasTestView = (CanvasTestView)findViewById(R.id.v_canvas);
//        mPathTestView = (PathTestView)findViewById(R.id.v_path);
//        mCanvasTestView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPathTestView.startAnimation();
//            }
//        });

        mStarView=(FloatStarsView) findViewById(R.id.fsv_stars);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStarView.startAnimation();
    }
}
