package com.gdut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

//即AccountOperator-ATM用于模拟ATM机的功能，对ArrayList中的Account进行操作
public class ATM {
    //设置对象
    private HashMap<String, Account> accounts = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    //菜单1--登录菜单界面
    public void menuLogin() {
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
                    logIn();
                    break;
                }
                case "2": {
                    signIn();
                    break;
                }
                case "3":
                    return;
                default:
                    System.out.println("    ==指令输入错误，请重新输入==");
            }
        }
    }

    //菜单2--用户菜单界面
    public void menuUser(Account account) {
        while (true) {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║============ ATM =============║");
            System.out.println("║══════════════════════════════║");
            System.out.println("║1.查询用户信息");
            System.out.println("║══════════════════════════════║");
            System.out.println("║2.存款");
            System.out.println("║══════════════════════════════║");
            System.out.println("║3.取款");
            System.out.println("║══════════════════════════════║");
            System.out.println("║4.转账");
            System.out.println("║══════════════════════════════║");
            System.out.println("║5.修改密码");
            System.out.println("║══════════════════════════════║");
            System.out.println("║6.返回上一菜单");
            System.out.println("║══════════════════════════════║");
            System.out.println("║7.销户");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("请输入指令：");
            String command = sc.next();
            switch (command) {
                case "1":
                    showInfo(account);
                    break;
                case "2":
                    depositMoney(account);
                    break;
                case "3":
                    withdrawMoney(account);
                    break;
                case "4":
                    transferMoney(account);
                    break;
                case "5":
                    changePassword(account);
                    break;
                case "6":
                    System.out.println("即将回到登录菜单");
                    return;
                case "7":
                    deleteAccount(account);
                    return;
                default:
                    System.out.println("    ==指令输入错误，请重新输入==");
            }
        }
    }


    //(登录菜单)功能1--随机创建卡号
    public static String randomCardId(int length) {
        String str = "1234567890";
        String code = "";
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int index = r.nextInt(str.length());
            code += str.charAt(index);
        }
        return code;
    }


    //(登录菜单)功能2--开户
    public void signIn() {
        boolean result = false;
        Account account = new Account();
        System.out.println("\n-欢迎您的到来，接下来我们要开始建立您的账户-");
        System.out.print("请输入您的真实姓名：");
        account.setName(sc.next());
        System.out.print("请输入您的性别:");
        do {
            result = account.setSex(sc.next());
        } while (!result);
        System.out.print("请设置账户密码(6位纯数字):");
        account.setPassword(sc.next());
        while (true) {
            System.out.print("请再次输入账户密码:");
            String checkPassword = sc.next();
            if (checkPassword.equals(account.getPassword())) {
                String cardId;
                do {
                    cardId = randomCardId(16);
                } while (accounts.containsKey(cardId)); // 检查卡号是否已存在
                System.out.println(account.getName(2) + "，恭喜您成功开户，以下是您的卡号:" + cardId);
                account.setCardId(cardId);
                break;
            } else System.out.println("您输入的密码与第一次输入的不一致");
        }
        accounts.put(account.getCardId(), account);
    }

    //(登录菜单)功能3--登录,根据返回的值来判断是否成功登录(null→失败；其他→正确)
    public void logIn() {
        if (accounts.isEmpty()) {
            System.out.println("系统中没有任何用户数据，无法登录");
            return;
        }
        System.out.println("\n-欢迎您的到来，接下来请输入您的账户信息-");
        System.out.print("请输入您的银行卡号:");
        String cardId = sc.next();
        System.out.print("请输入您的密码:");
        String password = sc.next();
        Account account = authenticate(cardId, password);
        if (account != null) {
            // 如果验证成功，显示用户菜单
            menuUser(account);
        } else {
            System.out.println("登录失败，卡号或密码错误");
        }
    }

    //(登录菜单)功能4--检测输入的卡号和密码是否正确
    public Account authenticate(String cardId, String password) {
        Account account = accounts.get(cardId);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    //(用户菜单)功能5--展现用户的所有信息(除了密码)
    public void showInfo(Account account) {
        System.out.println("户主:" + account.getName(1));
        System.out.println("卡号:" + account.getCardId());
        System.out.println("存款:" + account.getDeposit());
        System.out.println("状态:普通用户");
        System.out.println("今日剩余取款总额:" + (20000 - account.getAccumulatedWithdrawals()));
        System.out.println("今日剩余转账总额:" + (20000 - account.getAccumulatedTransfer()));
    }

    //(用户菜单)功能6--存款→设置一个额度，*后续可以添加等级系统，由此更改额度*
    public void depositMoney(Account account) {
        System.out.println("ATM机的单次最高存款金额是10000人民币");
        System.out.print("请输入您的存款金额:");
        double amount = sc.nextDouble();
        if (amount > 10000)
            System.out.println("ATM机不能一次性存储" + amount + "人民币\n请减少存款金额,或者前往柜台进行人工受理");
        else if (amount < 0) System.out.println("本次存款金额异常，不予受理");
        else {
            System.out.println("本次存款金额为:" + amount);
            account.setDeposit(account.getDeposit() + amount);
        }
    }

    //(用户菜单)功能7--取款→设置一个额度，*后续可以添加等级系统，由此更改额度*
    public void withdrawMoney(Account account) {
        System.out.println("目前作为普通用户，您的单次最高取款金额是5000人民币，每日最高额度为20000人民币");
        System.out.print("请输入您的取款金额:");
        double amount = sc.nextDouble();
        if (amount > 5000)
            System.out.println("您的信用等级不足以支撑单次" + amount + "人民币的取款操作\n请减少取款金额,或者前往柜台进行人工受理");
        else if (amount < 0) System.out.println("本次取款金额异常，不予受理");
        else if (amount > account.getDeposit()) System.out.println("您的余额不足，无法提出" + amount + "的金额");
        else if (account.getAccumulatedWithdrawals() + amount > 20000)
            System.out.println("本次取款将超过每日总额度，今日您还剩下" + (20000 - account.getAccumulatedWithdrawals()) + "的取款额度");
        else {
            System.out.println("本次取款金额为:" + amount);
            account.setAccumulatedWithdrawals(account.getAccumulatedWithdrawals() + amount);
            account.setDeposit(account.getDeposit() - amount);
        }
    }

    //(用户菜单)功能8--转账→设置一个额度，*后续可以添加等级系统，由此更改额度*
    public void transferMoney(Account account) {
        System.out.println("目前作为普通用户，您的单次最高转账金额是5000人民币，每日最高额度为20000人民币");
        System.out.print("请输入您的转账金额:");
        double amount = sc.nextDouble();
        if (amount > 5000)
            System.out.println("您的信用等级不足以支撑单次" + amount + "人民币的转账操作\n请减少取款金额,或者前往柜台进行人工受理");
        else if (amount < 0) System.out.println("本次转账金额异常，不予受理");
        else if (amount > account.getDeposit()) System.out.println("您的余额不足，无法转出" + amount + "的金额");
        else if (account.getAccumulatedTransfer() + amount > 20000)
            System.out.println("本次转账将超过每日总额度，今日您还剩下" + (20000 - account.getAccumulatedWithdrawals()) + "的转账额度");
        else {
            System.out.println("本次转账金额为:" + amount);
            System.out.print("请输入收款人的卡号:");
            String receiverCardId = sc.next();
            Account receiverAccount = accounts.get(receiverCardId);
            if (receiverAccount != null) {
                account.setDeposit(account.getDeposit() - amount);
                account.setAccumulatedTransfer(account.getAccumulatedTransfer() + amount);
                receiverAccount.setDeposit(receiverAccount.getDeposit() + amount);
                System.out.println("转账成功，您的余额为：" + account.getDeposit());
            } else {
                System.out.println("收款人卡号不存在，请检查后重新输入");
            }
        }
    }

    //（用户菜单）功能9--修改密码
    public void changePassword(Account account) {
        System.out.println("您是否要更改您的银行密码，是的话输入'Y',不是的话输入'N'");
        String confirm = "";
        while (true) {
            confirm = sc.next();
            switch (confirm) {
                case "Y": {
                    System.out.print("请输入您现在的密码:");
                    String presentPassword = sc.next();
                    if (presentPassword.equals(account.getPassword())) {
                        System.out.print("请设置新的账户密码(6位纯数字):");
                        account.setPassword(sc.next());
                        while (true) {
                            System.out.print("请再次输入新的账户密码:");
                            String checkPassword = sc.next();
                            if (checkPassword.equals(account.getPassword())) {
                                System.out.println(account.getName(2) + ",您的密码已经成功更改");
                                break;
                            } else System.out.println("您输入的密码与第一次输入的不一致");
                        }
                    } else System.out.println("密码输入错误");
                    return;
                }
                case "N": {
                    System.out.println("即将返回上一菜单");
                    return;
                }
                default:
                    System.out.print("您的输入并不规范，请重新输入:");
            }
        }
    }

    //(用户菜单)功能10--销户
    public void deleteAccount(Account account) {
        if (account.getDeposit() > 0)
            System.out.println("您的账户中仍有存款，无法销户\n请取出或转出所有存款后再销户，或者前面柜台进行人工受理");
        else {
            System.out.println("您是否真的要销户，是的话输入'Y',不是的话输入'N'");
            String confirm = "";
            while (true) {
                confirm = sc.next();
                switch (confirm) {
                    case "Y": {
                        accounts.remove(account.getCardId());
                        System.out.println("感谢您的使用，您的账户已经被删除");
                        return;
                    }
                    case "N": {
                        System.out.println("即将返回上一菜单");
                        return;
                    }
                    default:
                        System.out.print("您的输入并不规范，请重新输入:");
                }
            }
        }
    }
}
