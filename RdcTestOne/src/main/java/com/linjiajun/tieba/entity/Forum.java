package com.linjiajun.tieba.entity;

import java.util.Objects;

public class Forum {
    private int     idForum;        //贴吧的id(主键)
    private String  nameForum;      //贴吧的名字(非空，唯一)
    private Integer idForumOwner;   //吧主的id_user(依赖于user中的id_user)

    public Forum() {
    }

    public Forum(int idForum, String nameForum, Integer idForumOwner) {
        this.idForum = idForum;
        this.nameForum = nameForum;
        this.idForumOwner = idForumOwner;
    }

    public int getIdForum() {
        return idForum;
    }

    public void setIdForum(int idForum) {
        this.idForum = idForum;
    }

    public String getNameForum() {
        return nameForum;
    }

    public void setNameForum(String nameForum) {
        this.nameForum = nameForum;
    }

    public Integer getIdForumOwner() {
        return idForumOwner;
    }

    public void setIdForumOwner(Integer idForumOwner) {
        this.idForumOwner = idForumOwner;
    }

    @Override
    public String toString() {
        return "Forum{" +
                "idForum=" + idForum +
                ", nameForum='" + nameForum + '\'' +
                ", idForumOwner=" + idForumOwner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forum forum = (Forum) o;
        return idForum == forum.idForum && Objects.equals(nameForum, forum.nameForum) && Objects.equals(idForumOwner, forum.idForumOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idForum, nameForum, idForumOwner);
    }
}
