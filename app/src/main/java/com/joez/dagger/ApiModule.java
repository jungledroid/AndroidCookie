package com.joez.dagger;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
@Module
public class ApiModule {

    public static final String END_POINT = "http://www.baidu.com";

    @Provides
    @Singleton
    OkHttpClient providerOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit providerRetrofit() {
        return new Retrofit.Builder().build();
    }

    @Provides
    @Singleton
    User providerUser() {
        return new User("Joe Zhang");
    }
}
