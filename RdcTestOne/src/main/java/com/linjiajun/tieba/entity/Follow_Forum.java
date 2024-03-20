package com.linjiajun.tieba.entity;

import java.util.Objects;

public class Follow_Forum {
    private int userId; //用户id(主键，依赖于user中的id_user)
    private int forumId;//贴吧id(主键，依赖于forum中的id_forum)

    public Follow_Forum() {
    }

    public Follow_Forum(int userId, int forumId) {
        this.userId = userId;
        this.forumId = forumId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    @Override
    public String toString() {
        return "Follow_Forum{" +
                "userId=" + userId +
                ", forumId=" + forumId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follow_Forum that = (Follow_Forum) o;
        return userId == that.userId && forumId == that.forumId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, forumId);
    }
}
