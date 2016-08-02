package com.joez.presenter;


/**
 * Created by Administrator on 2016/6/11 0011.
 */
public abstract class BasePresenter<T> {
    protected T mView;

    public BasePresenter(T view){
        mView = view;
    }
    public void onDetach(){
        mView = null;
    }
}
