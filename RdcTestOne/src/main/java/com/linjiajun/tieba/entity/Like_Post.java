package com.linjiajun.tieba.entity;

import java.util.Objects;

public class Like_Post {
    private int userId;//用户id(主键，依赖于user中的id_user)
    private int postId;//帖子id(主键，依赖于psot中的id_post)

    public Like_Post() {
    }

    public Like_Post(int userId, int postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Like_Post{" +
                "userId=" + userId +
                ", postId=" + postId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like_Post likePost = (Like_Post) o;
        return userId == likePost.userId && postId == likePost.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }
}
