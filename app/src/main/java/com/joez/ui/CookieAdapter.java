package com.joez.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoeZ on 2016/5/16.
 */
public class CookieAdapter extends RecyclerView.Adapter{
    private static List<String> mCookies = null;

    public CookieAdapter(List<String> list){
        mCookies = list;
    }

    public void updateData(List<String> cookieList){
        if(cookieList != null){
            mCookies = cookieList;
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CookieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_cookie,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CookieViewHolder cookieHolder = (CookieViewHolder) holder;
        cookieHolder.tvCookie.setText(mCookies.get(position));
    }

    @Override
    public int getItemCount() {
        return mCookies.size();
    }

    private static final class CookieViewHolder extends RecyclerView.ViewHolder{
        TextView tvCookie;
        public CookieViewHolder(View itemView) {
            super(itemView);
            tvCookie = (TextView) itemView.findViewById(R.id.tv_cookie);
        }
    }
}
