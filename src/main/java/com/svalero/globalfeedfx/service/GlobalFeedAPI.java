package com.svalero.globalfeedfx.service;

import com.svalero.globalfeedfx.domain.Post;
import com.svalero.globalfeedfx.domain.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface GlobalFeedAPI {
    @GET("user")
    Observable<User> getUser(@Query("username") String username);
    @GET("posts")
    Observable<List<Post>> getPostsFromUser(@Query("username")String username);
}
