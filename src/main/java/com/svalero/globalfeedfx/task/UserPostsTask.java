package com.svalero.globalfeedfx.task;

import com.svalero.globalfeedfx.domain.Post;
import com.svalero.globalfeedfx.domain.User;
import com.svalero.globalfeedfx.service.GlobalFeedAPI;
import com.svalero.globalfeedfx.service.GlobalFeedService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class UserPostsTask extends Task<Integer> {
    private Consumer<Post> postConsumer;
    private String username;

    public UserPostsTask(Consumer<Post> postConsumer, String username){
        this.postConsumer = postConsumer;
        this.username = username;
    }
    @Override
    protected Integer call() throws Exception {
        GlobalFeedService globalFeedService = new GlobalFeedService();
        globalFeedService.getPostsFromUser(username).subscribe(postConsumer);
        return null;
    }
}
