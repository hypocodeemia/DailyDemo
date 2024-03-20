package com.linjiajun.tieba.service.Interface;

public interface UserService {

    //注册
    public  boolean register(String info[]);

    //登录
    public  boolean logIn(String info[]);

    //创建贴吧(的信息验证)
    public boolean createForum(String[] info);



}
