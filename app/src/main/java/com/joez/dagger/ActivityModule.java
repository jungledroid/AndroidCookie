package com.joez.dagger;

import com.joez.ui.Dagger2Aty;
import dagger.Module;
import dagger.Provides;

/**
 * Created by gzzyj on 2016/7/30.
 */
@Module
public class ActivityModule {
    private Dagger2Aty mActivity;

    public ActivityModule(Dagger2Aty activity) {
        mActivity = activity;
    }

    @Provides
    public Dagger2Aty providerActivity() {
        return mActivity;
    }

    @Provides
    public User providerUser() {
        return new User("Joe");
    }

    @Provides
    public DaggerPresenter providerDaggerPresenter(Dagger2Aty activity,User user) {
        return new DaggerPresenter(activity,user);
    }
}
