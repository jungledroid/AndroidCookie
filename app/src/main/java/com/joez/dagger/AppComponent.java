package com.joez.dagger;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
@Singleton
@Component(modules={ApiModule.class})
public interface AppComponent {
    OkHttpClient getHttpClient();
    Retrofit getRetrofit();
    User getUser();
}
