package com.svalero.globalfeedfx.controller;

import com.svalero.globalfeedfx.task.LoadingTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class AppController {
    @FXML
    public TextField username;
    @FXML
    public Button btGetPosts;
    @FXML
    public ProgressBar pbBar;

    private LoadingTask loadingTask;
    private boolean first = true;

    @FXML
    public void getUserPost(ActionEvent actionEvent){
        loadingTask = new LoadingTask(username.getText());
        pbBar.progressProperty().unbind();
        pbBar.progressProperty().bind(loadingTask.progressProperty());

        new Thread(loadingTask).start();
    }
}
