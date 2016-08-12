package com.joez.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.joez.presenter.BasePresenter;
import com.joez.view.HttpView;
import com.joez.presenter.Navigator;
import com.joez.ui.CookieAdapter;
import com.joez.ui.R;
import com.joez.utils.OnRecyclerItemListener;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class VolleyHomeFragment extends BaseFragment implements HttpView {

    private static final String TAG = "VolleyHomeFragment";
    private RecyclerView mRvCookie;
    private HttpPresenter mPresenter;
    private Navigator mNavigator;

    public VolleyHomeFragment() {
    }


    public static VolleyHomeFragment newInstance() {
        VolleyHomeFragment fragment = new VolleyHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNavigator = (Navigator) getActivity();
        mPresenter = new HttpPresenter();
        mPresenter.attach(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_volley_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvCookie = (RecyclerView)view.findViewById(R.id.rv_https);
        mRvCookie.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvCookie.setAdapter(new CookieAdapter(mPresenter.getFunctionList()));
        Log.e(TAG, "onCookieCreate: ====");
        mRvCookie.addOnItemTouchListener(new OnRecyclerItemListener(mRvCookie) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                int position = holder.getAdapterPosition();
                Log.e(TAG, "onItemClick: "+position);
                if(position == 0){
                    mNavigator.addFragment(VolleyFragment.newInstance());
                }else if(position ==1){
                    mPresenter.testJsonObjectRequest();
                }
            }

            @Override
            protected void onLongClick(RecyclerView.ViewHolder holder) {
                super.onLongClick(holder);
            }
        });
    }

    private static final class  HttpPresenter extends BasePresenter<HttpView> {
        private RequestQueue mRequestQueue;
        public HttpPresenter() {


        }

        @Override
        public void attach(HttpView view) {
            super.attach(view);
            mRequestQueue = Volley.newRequestQueue(view.getContext());
        }

        public List<String> getFunctionList(){
            List<String> functions = new ArrayList<>();
            functions.add("stringrequest");
            functions.add("jsonrequest");
            functions.add("custom request");
            return functions;
        }

        public void testJsonObjectRequest(){
            JsonObjectRequest jsonRequest = new JsonObjectRequest("http://httpbin.org/get", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e(TAG, "onResponse: "+response.toString() );
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            mRequestQueue.add(jsonRequest);
        }
    }
}
