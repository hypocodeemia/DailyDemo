package com.gdut;

import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //设置对象
        Scanner scanner = new Scanner(System.in);
        //定义变量
        String CODE = VerificationCode(5);

        //登录界面
        for (int i = 1; i <= 3; i++) {
            System.out.println("╔══════════════════════════════╗");
            System.out.print("║请输入用户名: ");
            String username = scanner.next();
            System.out.println("║══════════════════════════════║");
            System.out.print("║请输入密码: ");
            String password = scanner.next();
            System.out.println("║══════════════════════════════║");
            System.out.println("║"+CODE);
            System.out.print("║请输入验证码: ");
            String code = scanner.next();
            System.out.println("╚══════════════════════════════╝");

            boolean result1 = CheckAccount(username, password);
            if (CODE.equals(code)) {
                if (CheckAccount(username, password)) {
                    System.out.println("账号信息输入正确，欢迎登录。");
                    break;
                } else System.out.println("账号信息有误，请重新输入。剩余次数:" + (3 - i));
            } else {
                System.out.println("验证码输入错误，请重新输入。剩余次数:" + (3 - i));
                continue;
            }
        }

        //后续接正式的功能菜单
    }

    //方法——验证账号和密码是否正确
    public static boolean CheckAccount(String usernaem, String password) {
        String USERNAME = "gdut";
        String PASSWORD = "123456Ab";
        return (USERNAME.equals(usernaem) && PASSWORD.equals(PASSWORD));
    }

    //方法——生成验证码
    public static String VerificationCode(int length) {
        String str = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        String code = "";
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int index = r.nextInt(str.length());
            code += str.charAt(index);
        }
        return code;
    }

}
