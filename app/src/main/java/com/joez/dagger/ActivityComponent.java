package com.joez.dagger;

import com.joez.ui.Dagger2Aty;

import dagger.Component;

/**
 * Created by gzzyj on 2016/7/30.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(Dagger2Aty aty);

}
