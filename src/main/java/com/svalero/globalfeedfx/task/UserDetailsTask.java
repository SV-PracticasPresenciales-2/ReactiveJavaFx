package com.svalero.globalfeedfx.task;

import com.svalero.globalfeedfx.domain.User;
import com.svalero.globalfeedfx.service.GlobalFeedService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class UserDetailsTask extends Task<Integer> {
    private Consumer<User> userConsumer;
    private String username;

    public UserDetailsTask(Consumer<User> userConsumer, String username){
        this.userConsumer = userConsumer;
        this.username = username;
    }

    @Override
    protected Integer call() throws Exception {
        GlobalFeedService globalFeedService = new GlobalFeedService();
        globalFeedService.getUser(username).subscribe(userConsumer);
        return null;
    }
}
