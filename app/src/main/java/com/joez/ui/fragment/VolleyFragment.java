package com.joez.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joez.presenter.BasePresenter;
import com.joez.ui.R;

public class VolleyFragment extends BaseFragment {

    public VolleyFragment() {

    }

    public static VolleyFragment newInstance() {
        VolleyFragment fragment = new VolleyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_volley, container, false);
    }

    private static final class Presenter extends BasePresenter{

        public Presenter() {

        }
    }
}
