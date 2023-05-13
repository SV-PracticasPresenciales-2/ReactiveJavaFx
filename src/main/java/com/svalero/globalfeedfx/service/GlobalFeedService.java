package com.svalero.globalfeedfx.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svalero.globalfeedfx.domain.Post;
import com.svalero.globalfeedfx.domain.User;
import okhttp3.OkHttpClient;
import io.reactivex.Observable;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalFeedService {
    private String GLOBAL_FEED_API = "localhost:8081";
    private GlobalFeedAPI globalFeedAPI;

    public GlobalFeedService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gsonParser = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit globalFeedAPI = new Retrofit.Builder()
                .baseUrl(GLOBAL_FEED_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonParser))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.globalFeedAPI = globalFeedAPI.create(GlobalFeedAPI.class);
    }

    public Observable<User> getUser(String usermane){
        return this.globalFeedAPI.getUser(usermane);
    }

    public Observable<Post> getPostsFromUser(String username){
        return this.globalFeedAPI.getPostsFromUser(username).flatMapIterable(posts -> posts);
    }
}
