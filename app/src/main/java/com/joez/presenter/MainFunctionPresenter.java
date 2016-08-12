package com.joez.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.joez.view.MainFunctionView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/11 0011.
 */
public class MainFunctionPresenter extends BasePresenter{
    private static final String TAG = "MainFunctionPresenter";

    public MainFunctionPresenter() {

    }

    public List<String> getFunctionList(){
        List<String> cookies = new ArrayList<>();
        cookies.add("KeyBoard research");
        cookies.add("use Bit Mask");
        cookies.add("Canvas test");
        cookies.add("Volley");
        cookies.add("dagger2");
        cookies.add("rxJavaTest");
        return  cookies;
    }

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };
    public void testVolley() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://httpbin.org/post", new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Log.e(TAG, "onResponse: "+response.toString(),null );
            }
        },mErrorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("name","joez");
                map.put("pwd","1234");
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue((Context) mView);
        queue.add(request);
    }

    public void testVolleyJsonObject(){
//        JsonObjectRequest request = new JsonObjectRequest()
    }
}
