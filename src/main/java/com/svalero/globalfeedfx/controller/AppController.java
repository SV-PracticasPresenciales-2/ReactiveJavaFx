package com.svalero.globalfeedfx.controller;

import com.svalero.globalfeedfx.task.LoadingTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class AppController {
    @FXML
    public TextField username;
    @FXML
    public Button btGetPosts;
    @FXML
    public ProgressBar pgBar;

    private LoadingTask loadingTask;

    @FXML
    public void getUserPost(ActionEvent actionEvent){
        loadingTask = new LoadingTask(username.getText());
        pgBar.progressProperty().unbind();
        pgBar.progressProperty().bind(loadingTask.progressProperty());
        btGetPosts.setDisable(true);
    }
}
