package com.gdut.TakeawayMerchants;

import java.util.Scanner;

public class ArrayListDemo2_P82 {
    public static void main(String[] args) {
        //设置对象
        ProductsOperator operator = new ProductsOperator();
        Scanner scanner = new Scanner(System.in);
        //菜单界面
        while (true) {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║----------菜单管理系统----------║");
            System.out.println("║══════════════════════════════║");
            System.out.println("║1.上架商品                     ║");
            System.out.println("║══════════════════════════════║");
            System.out.println("║2.展示所有商品                  ║");
            System.out.println("║══════════════════════════════║");
            System.out.println("║3.下架商品                     ║");
            System.out.println("║══════════════════════════════║");
            System.out.println("║4.退出系统                     ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("请输入指令:");
            String command = scanner.next();
            switch (command) {
                case "1":
                    operator.AddProduct();
                    break;
                case "2":
                    operator.ShowAllProducts();
                    break;
                case "3":
                    operator.DeleteProduct();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("功能选择错误，请重新输入");
            }
        }
    }
}
