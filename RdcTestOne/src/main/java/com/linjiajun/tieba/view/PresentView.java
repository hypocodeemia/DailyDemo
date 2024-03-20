package com.linjiajun.tieba.view;

import com.linjiajun.tieba.controller.CommonUserController;
import com.linjiajun.tieba.dao.DataBase;
import com.linjiajun.tieba.entity.User;

import java.util.Scanner;

public class PresentView {
    Scanner sc = new Scanner(System.in);
    private CommonUserController commonUserController = CommonUserController.getCommonUserController();

    //登陆前的主界面(包含注册和登录，返回的User不是null就表示登录成功)
    public  User landingPage() throws Exception {
        String choice = null;
        User user = null;

        while(choice!="3"&&user==null){
            System.out.println("1.注册");
            System.out.println("2.登录");
            System.out.println("3.退出");
            System.out.print("请输入指令:");
            choice = sc.next();
            switch (choice){
                case "1":commonUserController.register();
                    break;
                case "2":
                    user = commonUserController.logIn();
                    break;
                case "3":break;
                default:
                    System.out.println("输入错误");
            }
        }

        System.out.println(user);
        return user;
    }

    /*登陆后的主界面*/
    public void mainMenu(User user){

    }

}
