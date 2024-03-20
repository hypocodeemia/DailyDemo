package com.linjiajun.tieba.service.impl;

import com.linjiajun.tieba.service.Interface.UserService;

public class UserServiceImpl implements UserService {
    public UserServiceImpl() {
    }

    /*新用户注册用到的规范判断  ->  接收UserView中registerView方法返回的值进行处理，将处理结果返回给*/
    @Override//参数为依次存有username,nickname,password的String数组
    public  boolean  register(String[] info) {
        // isAllInputsValid为true表示全部输入规范，false表示至少有一个输入不规范
        boolean isAllInputsValid = true;

        // 以下为username, nickname, password的正则表达式
        String usernameRegex = "^.{1,14}$"; // 1-14个字符
        String nicknameRegex = "^.{1,10}$"; // 1-10个字符
        String passwordRegex = "^(?:(?=.*[A-Za-z])(?=.*[0-9])|(?=.*[A-Za-z])(?=.*[^A-Za-z0-9])|(?=.*[0-9])(?=.*[^A-Za-z0-9])).{8,14}$"; // 8-14位，字母/数字/标点符号至少2种

        //将正则表达式和用户输入信息的类型塞入数组，便于修改维护
        String[] regexes = {usernameRegex, nicknameRegex, passwordRegex};
        String[] inputDescriptions = {"用户名", "昵称", "密码"};

        //依次判断用户的注册信息是否规范
        for (int i = 0; i < info.length; i++) {
            if (!info[i].matches(regexes[i])) {
                System.out.println("输入的" + inputDescriptions[i] + "不符合规范");
                isAllInputsValid = false;
            }
        }

        return isAllInputsValid;
    }



    /*判断登录时输入的用户名以及密码是否规范，用来过滤一些明显错误的登录信息，减少不必要的与数据库交流的操作*/
    @Override//参数为依次存有username,password的String数组
    public  boolean logIn(String[] info) {
        //isAllInputsValid为true表示全部输入规范，false表示至少有一个输入不规范
        boolean isAllInputsValid = true;
        // 以下为username, nickname, password的正则表达式
        String usernameRegex = "^.{1,14}$"; // 1-14个字符
        String passwordRegex = "^(?:(?=.*[A-Za-z])(?=.*[0-9])|(?=.*[A-Za-z])(?=.*[^A-Za-z0-9])|(?=.*[0-9])(?=.*[^A-Za-z0-9])).{8,14}$"; // 8-14位，字母/数字/标点符号至少2种

        //将正则表达式和用户输入信息的类型塞入数组，便于修改维护
        String[] regexes = {usernameRegex, passwordRegex};
        String[] inputDescriptions = {"用户名","密码"};

        //依次判断用户的注册信息是否规范
        for (int i = 0; i < info.length; i++) {
            if (!info[i].matches(regexes[i])) {
                System.out.println("输入的" + inputDescriptions[i] + "不符合规范");
                isAllInputsValid = false;
            }
        }

        return isAllInputsValid;
    }

    /*判断创建贴吧时的信息输入是否规范*/
    @Override
    public boolean createForum(String[] info) {
        //isAllInputsValid为true表示全部输入规范，false表示至少有一个输入不规范
        boolean isAllInputsValid = true;
        // 以下为nameForum的正则表达式
        String usernameRegex = "^.{1,14}$"; // 1-14个字符

        //将正则表达式和用户输入信息的类型塞入数组，便于修改维护
        String[] regexes = {usernameRegex};
        String[] inputDescriptions = {"贴吧名"};

        //依次判断创建贴吧的信息是否规范
        for (int i = 0; i < info.length; i++) {
            if (!info[i].matches(regexes[i])) {
                System.out.println("输入的" + inputDescriptions[i] + "不符合规范");
                isAllInputsValid = false;
            }
        }

        return isAllInputsValid;
    }


}
