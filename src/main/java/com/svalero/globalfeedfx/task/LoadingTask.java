package com.svalero.globalfeedfx.task;

import com.svalero.globalfeedfx.controller.UserInfoController;
import com.svalero.globalfeedfx.domain.Post;
import com.svalero.globalfeedfx.domain.User;
import com.svalero.globalfeedfx.util.R;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadingTask extends Task<Integer> {
    private User user;
    private List<Post> postList;
    private UserDetailsTask userDetailsTask;
    private UserPostsTask userPostsTask;
    private boolean correct = true;
    private String username;

    public LoadingTask(String username){
        this.username = username;
    }
    @Override
    protected Integer call() throws Exception {
        postList = new ArrayList<>();
        System.out.println("Username: " + username);
        if(!username.isEmpty()){
            Consumer<User> userConsumer = (userC) -> {
                if(userC != null){
                    user = userC;
                } else {
                    correct = false;
                    updateMessage("no");
                }
                updateProgress(10, 100);
            };

            this.userDetailsTask = new UserDetailsTask(userConsumer, username);
            Thread userDetails = new Thread(userDetailsTask);
            userDetails.start();
            while (userDetails.isAlive()){
            }
            if(user != null){
                Consumer<Post> postConsumer = (postC) -> {
                    postList.add(postC);
                    Thread.sleep(100);
                };

                Double update = 10.;
                this.userPostsTask = new UserPostsTask(postConsumer, username);
                Thread postListT = new Thread(userPostsTask);
                postListT.start();
                while (postListT.isAlive()){
                    if(update < 90.){
                        update = update + 5;
                        updateProgress(update, 100);
                    }
                }
                updateProgress(update, 100);
                updateMessage("ok");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(R.getUI("userDetails.fxml"));
                        loader.setController(new UserInfoController(user, postList));
                        VBox vbox = null;
                        try {
                            vbox = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Scene scene = new Scene(vbox);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("UserInfo");
                        stage.show();
                    }
                });
            }
        } else {
            updateMessage("no");
        }

        return null;
    }
}
