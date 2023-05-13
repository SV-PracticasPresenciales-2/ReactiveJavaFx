package com.svalero.globalfeedfx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    public int id;
    public String message;
    public String postDate;
    public int likes;
    public User userPost;
}
