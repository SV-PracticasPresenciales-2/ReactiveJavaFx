package com.svalero.globalfeedfx.controller;

import com.svalero.globalfeedfx.domain.Post;
import com.svalero.globalfeedfx.domain.User;

import java.util.List;

public class UserInfoController {
    private User user;
    private List<Post> postList;

    public UserInfoController(User user, List<Post> postList) {
        this.user = user;
        this.postList = postList;
    }
}
