package com.gdut.MyArrayList_API;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDemo1_P81 {
    public static void main(String[] args) {
        //变量mycart存储购物车中商品的名字
        ArrayList<String> mycart = new ArrayList<String>();
        //添加Scanner以便输入
        Scanner sc = new Scanner(System.in);

        //添加固定的内容以便测试方法—DeleteAll
        mycart.add("The Binding of Isaac: Rebirth");
        mycart.add("Grand Theft Auto V");
        mycart.add("Battlefield™ 1");
        mycart.add("Battlefield™ V Definitive Edition");

        //以下为在控制台输入,输入"ESC"退出输入
        while (true) {
            System.out.print("请输入要添加至购物车的商品(输入\"ESC\"退出):");
            String part = sc.next();
            if (part.equals("ESC")) break;
            mycart.add(part);
        }

        //以下测试方法-DeleteAll
        while (true) {
            System.out.println("以下为当前购物车中的内容:");
            System.out.println(mycart+"\n");
            System.out.print("请输入关键字词，含有该字词的商品均会被移出购物车(输入\"ESC\"退出):");
            String target = sc.next();
            if (target.equals("ESC")) break;
            if(DeleteAll(mycart,target)) System.out.println("已成功删除所有带有\""+target+"\"的商品\n");
            else System.out.println("未找到含有\""+target+"\"的商品\n");
        }
    }
//main结尾--------------------------------------

    //方法1-删除集合subject中所有带有字符串target的成员,并返回boolean值表示是否有删除过subject的成员
    public static boolean DeleteAll(ArrayList<String> subject, String target) {
        boolean result=false;
        for (int i = subject.size()-1; i >= 0; i--) {
            String part = subject.get(i);
            if(part.contains(target)) {
                subject.remove(part);
                result = true;
            }
        }
        return result;
    }

}
