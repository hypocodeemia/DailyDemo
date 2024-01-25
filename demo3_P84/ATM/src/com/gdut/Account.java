package com.gdut;

import java.util.Scanner;

//对象→Account类--用于存放用户信息(卡号，姓名，性别，密码，存款，取款额度)
public class Account {
    private String cardId;
    private String name;
    private String sex;
    private String password;
    private double deposit;
    private double limit;

    public Account() {
    }

    public Account(String cardId, String name, String sex, String password, double deposit, double limit) {
        this.cardId = cardId;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.deposit = deposit;
        this.limit = limit;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName(int condition) {//condition用于区分需要获取全名(1)还是敬语(2)
        if (condition == 1) return name;
        else return name + (sex.equals("男") ? "先生" : "女士");
    }

    //需要添加对:  1.空白输入 2.全空格输入  的检测→不用，因为Java的Scanner类会自动忽略字符串前后的空格，只会读取有效的字符。
    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    //需添加对:  输入只能为“男”or“女”的判断
    public boolean setSex(String sex) {
        if ((sex.equals("男") || sex.equals("女")) == false) {
            System.out.print("您的性别输入有误，请重新输入:");
            return false;
        }
        this.sex = sex;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (IsValidPassword(password)) {
            System.out.println("您的密码符合规定，设置成功");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("您的密码不符合\"6位纯数字的规定\"，请重新设置");
            while (!IsValidPassword(password)) {
                System.out.print("请按照规定设置密码（6位纯数字）：");
                password = sc.next();
            }
            System.out.println("恭喜您，成功设置了规范的密码");
        }
        this.password = password;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    //方法1--检测银行卡密码是否符合规则：1. 6位   2. 纯数字
    private static boolean IsValidPassword(String password) {
        if (password.length() != 6) {
            return false;
        }
        for (char c : password.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
