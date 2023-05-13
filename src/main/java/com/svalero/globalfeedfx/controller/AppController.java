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
        loadingTask.messageProperty().addListener((observableValue, s, t1) -> {
                if(t1.equals("no")){
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Username not found");
                    alert.setContentText("BAD");

                    alert.showAndWait();
                } else if(t1.equals("ok")){
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("OK");
                    alert.setHeaderText("OK");
                    alert.setContentText("OK");
                    alert.showAndWait();
                }
        });
        new Thread(loadingTask).start();
    }
}
