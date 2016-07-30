package com.joez.dagger;

import com.joez.ui.Dagger2Aty;

/**
 * Created by gzzyj on 2016/7/30.
 */
public class DaggerPresenter {
    Dagger2Aty mActivity;
    User mUser;

    public DaggerPresenter(Dagger2Aty activity, User user) {
        mActivity = activity;
        this.mUser = user;
    }

    public void showUserName() {
        mActivity.showUserName(mUser.name);
    }
}
