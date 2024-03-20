package com.linjiajun.tieba.entity;

import java.util.Objects;

public class User {
    private int     idUser;   //用户id(主键)
    private String  userName; //用户名(可以用来登录,非空，唯一。最长14个英文，7个汉字  ->  简化为最长14个字符)
    private String  nickName; //昵称（1-10个字符，非空，唯一）
   // private String  hashValue;//处理后的哈希值。密码（8-14位，字母/数字/标点符号至少2种)
   // private String  slat;     //盐值

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }


    public User(Integer id_user, String userName, String nickName) {
        this.idUser = id_user;
        this.userName = userName;
        this.nickName = nickName;
        //this.hashValue = hashValue;
        //this.slat = slat;
    }

    public Integer getId_User() {
        return idUser;
    }

    public void setId_User(Integer id_User) {
        this.idUser = id_User;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }




    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser && Objects.equals(userName, user.userName) && Objects.equals(nickName, user.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, userName, nickName);
    }
}
