package com.linjiajun.tieba.entity;

import java.util.Objects;

public class Comment {
    private int    idCommentOwner;//评论的人的id(依赖于user中的id_user)
    private int    idComment;     //评论的id，也就是楼层数(自增长)
    private String comtent;       //评论的正文(1-200个字,酌情限制)
    private int    postId;        //评论的帖子的id(依赖于post中的id_post)

    public Comment() {
    }

    public Comment(int idCommentOwner, int idComment, String comtent, int postId) {
        this.idCommentOwner = idCommentOwner;
        this.idComment = idComment;
        this.comtent = comtent;
        this.postId = postId;
    }

    public int getIdCommentOwner() {
        return idCommentOwner;
    }

    public void setIdCommentOwner(int idCommentOwner) {
        this.idCommentOwner = idCommentOwner;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getComtent() {
        return comtent;
    }

    public void setComtent(String comtent) {
        this.comtent = comtent;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idCommentOwner=" + idCommentOwner +
                ", idComment=" + idComment +
                ", comtent='" + comtent + '\'' +
                ", postId=" + postId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return idCommentOwner == comment.idCommentOwner && idComment == comment.idComment && postId == comment.postId && Objects.equals(comtent, comment.comtent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommentOwner, idComment, comtent, postId);
    }
}
