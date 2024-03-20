package com.linjiajun.tieba.view;

import com.linjiajun.tieba.dao.UserDao;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Scanner;

public class UserView {
    //统一的Scanner对象
    private static Scanner scanner = new Scanner(System.in);

    /*用于退出"\exit"和退步"\return"的方法*/
    private static boolean isExit(String input) {
        return (input.equals("\\exit"));
    }

    private static boolean isReturn(String input) {
        return (input.equals("\\return"));
    }


    /*整合提示语和输入(同时删除首位的空白字符，返回直到第一个换行符的所有字符)*/
    private static String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }


    /*模拟用户注册界面*/
    public static String[] registerView() {
        String[] info = new String[3];
        String username, nickname, password;
        //创建一个长度等长，用于表示中途退出的数组
        String[] exitSignal = new String[] {"\\exit"};

        while (true) {
            //用户名输入
            username = getInput("请输入用户名(1-14个字符):");
            if (isExit(username)) return exitSignal;
            if (isReturn(username)) continue;
            info[0] = username;
            //昵称输入
            nickname = getInput("请输入昵称(1-10个字符):");
            if (isExit(nickname)) return exitSignal;
            if (isReturn(nickname)) continue;
            info[1] = nickname;
            //密码输入
            password = getInput("请输入密码(8-14位，字母/数字/标点符号至少2种):");
            if (isExit(password)) return exitSignal;
            if (isReturn(password)) continue;
            info[2] = password;

            break;
        }
        //将username,nickname,password依次存入String数组,并返回  ->  用作service包下UserServiceImpl类的Register方法的形参
        return info;
    }

    /*登录界面*/
    public static String[] logInView() throws Exception {
        String username, password;
        String[] info = new String[2];
        //创建一个长度等长，用于表示中途退出的数组
        String[] exitSignal = new String[] {"\\exit"};
        while (true) {
            //输入用户名
            username = getInput("请输入用户名:");
            if(isExit(username)) {
                System.out.println("已退出登录界面");
                return exitSignal;
            }
            if(isReturn(username))continue;
            info[0] = username;
            //输入密码
            password = getInput("请输入密码");
            if(isExit(username)) {
                System.out.println("已退出登录界面");
                return exitSignal;
            }
            if(isReturn(username))continue;
            info[1] = password;

            //完整输入完，就返回username和password
            return info;
        }
    }


    /*创建贴吧的界面*/
    public static String[] createForumView(){
        String nameForum;
        String[] info = new String[1];
        //创建一个长度等长，用于表示中途退出的数组
        String[] exitSignal = new String[] {"\\exit"};

        while (true) {
            nameForum = getInput("请输入希望创建的贴吧的名字(1-14个字符):");
            if(isExit(nameForum)) {
                System.out.println("已退出创建贴吧界面");
                return exitSignal;
            }
            if(isReturn(nameForum))continue;
            info[0] = nameForum;
            return info;
        }
    }



}
