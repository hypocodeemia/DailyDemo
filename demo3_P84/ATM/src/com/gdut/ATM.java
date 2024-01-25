package com.gdut;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//即AccountOperator-ATM用于模拟ATM机的功能，对ArrayList中的Account进行操作
public class ATM {
    //设置对象
    private ArrayList<Account> accounts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    //方法1--登录菜单界面
    public void MenuLogin() {
        while (true) {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║============ ATM =============║");
            System.out.println("║══════════════════════════════║");
            System.out.println("║1.登录");
            System.out.println("║══════════════════════════════║");
            System.out.println("║2.开户");
            System.out.println("║══════════════════════════════║");
            System.out.println("║3.退出");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("请输入指令：");
            String command = sc.next();
            switch (command) {
                case "1": {
                    SignIn();
                    break;
                }
                case "2": {
                    Creat();
                    break;
                }
                case "3":
                    return;
                default:
                    System.out.println("    ==指令输入错误，请重新输入==");
            }
        }
    }

    //方法1--随机创建卡号
    public static String RandomCardId(int length) {
        String str = "1234567890";
        String code = "";
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int index = r.nextInt(str.length());
            code += str.charAt(index);
        }
        return code;
    }


    //方法2--开户
    public void Creat(){
        boolean result= false;
        Account account = new Account();
        System.out.println("\n-欢迎您的到来，接下来我们要开始建立您的账户-");
        System.out.print("请输入您的真实姓名：");
        account.setName(sc.next());
        System.out.print("请输入您的性别:");
        do {
            result = account.setSex(sc.next());
        } while (result==false);
        System.out.print("请设置账户密码(6位纯数字):");
        account.setPassword(sc.next());
        while (true) {
            System.out.print("请再次输入账户密码:");
            String checkPassword = sc.next();
            if(checkPassword.equals(account.getPassword())){
                String cardId = RandomCardId(16);
                System.out.println(account.getName(2)+"，恭喜您成功开户，以下是您的卡号:"+cardId);
                account.setCardId(cardId);
                break;
            }
            else System.out.printf("您输入的密码与第一次输入的不一致");
        }
        accounts.add(account);
    }

    //方法3--登录
    public  void SignIn(){
        if(accounts.size()==0){
            System.out.println("系统中没有任何用户数据，无法登录");
            return;
        }
        System.out.println("\n-欢迎您的到来，接下来请输入您的账户信息-");
        System.out.print("请输入您的银行卡号:");
        String cardId=sc.next();
        System.out.print("请输入您的密码:");
        String password=sc.next();
    }
}
