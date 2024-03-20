package com.linjiajun.tieba.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private int            idPostOwner;//发帖的人的id(依赖于user中的id_user)
    private int            idPost;     //帖子的id(主键，自增长)
    private String         namePost;   //帖子的标题(5-31个字）
    private String         context;    //帖子的正文(1-200个字,酌情限制)
    private String         forumName;  //所属贴吧的名字(依赖于forum中的name_forum)
    private LocalDateTime  lastReply;  //最近一次回复的时间
    private boolean        isPinned;   //表示是否被置顶(0是未，1是被置顶)

    public Post() {
    }

    public Post(Integer idPostOwner, Integer idPost, String namePost, String context, String forumName, LocalDateTime lastReply, boolean isPinned) {
        this.idPostOwner = idPostOwner;
        this.idPost = idPost;
        this.namePost = namePost;
        this.context = context;
        this.forumName = forumName;
        this.lastReply = lastReply;
        this.isPinned = isPinned;
    }

    public Integer getIdPostOwner() {
        return idPostOwner;
    }

    public void setIdPostOwner(Integer idPostOwner) {
        this.idPostOwner = idPostOwner;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public LocalDateTime getLastReply() {
        return lastReply;
    }

    public void setLastReply(LocalDateTime lastReply) {
        this.lastReply = lastReply;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPostOwner=" + idPostOwner +
                ", idPost=" + idPost +
                ", namePost='" + namePost + '\'' +
                ", context='" + context + '\'' +
                ", forumName='" + forumName + '\'' +
                ", lastReply=" + lastReply +
                ", isPinned=" + isPinned +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return isPinned == post.isPinned && Objects.equals(idPostOwner, post.idPostOwner) && Objects.equals(idPost, post.idPost) && Objects.equals(namePost, post.namePost) && Objects.equals(context, post.context) && Objects.equals(forumName, post.forumName) && Objects.equals(lastReply, post.lastReply);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPostOwner, idPost, namePost, context, forumName, lastReply, isPinned);
    }
}
