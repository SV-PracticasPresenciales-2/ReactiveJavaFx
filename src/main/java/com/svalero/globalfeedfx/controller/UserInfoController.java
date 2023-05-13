package com.svalero.globalfeedfx.controller;

import com.svalero.globalfeedfx.domain.Post;
import com.svalero.globalfeedfx.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    private User user;
    private List<Post> postList;

    @FXML
    private Text textUsername;
    @FXML
    private Text textBio;
    @FXML
    private Text textRegister;
    @FXML
    private ListView<Post> postListView;
    private ObservableList<Post> postObservableList;

    public UserInfoController(User user, List<Post> postList) {
        this.user = user;
        this.postList = postList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postObservableList = FXCollections.observableArrayList();
        textUsername.setText(user.getUsername());
        textBio.setText(user.getBiography());
        textRegister.setText(user.getRegisterDate());

        postListView.setCellFactory(new Callback<ListView<Post>, ListCell<Post>>() {
            @Override
            public ListCell<Post> call(ListView<Post> postListView) {
                return new ListCell<>(){
                    protected void updateItem(Post post, boolean empty){
                        super.updateItem(post, empty);
                        if(post != null && !empty) {
                            Text titleText = new Text("Message: " + post.getMessage());
                            Text likeText = new Text("Likes: " + post.getLikes());
                            Text dateText = new Text("Post date: " + post.getPostDate());

                            HBox hBox = new HBox(titleText, likeText);
                            hBox.setSpacing(10);
                            VBox vBox = new VBox();
                            vBox.getChildren().addAll(hBox, dateText);
                            vBox.setSpacing(10);

                            setGraphic(vBox);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        postObservableList.addAll(postList);
        postListView.setItems(postObservableList);
    }
}
