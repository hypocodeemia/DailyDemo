package com.gdut.TakeawayMerchants;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductsOperator {
    //创建集合productslist来储存Products对象
    private ArrayList<Products> productslist = new ArrayList<>();

    //上架商品
    public void AddProduct(){
        //创建Scanner对象
        Scanner sc =new Scanner(System.in);
        //创建products对象
        Products product = new Products();
        //录入功能
        System.out.print("请输入商品名称:");
        String name=sc.next();
        product.setName(name);
        System.out.print("请输入商品价格:");
        Double price=sc.nextDouble();
        product.setPrice(price);
        System.out.print("请输入商品介绍:");
        String info=sc.next();
        product.setInfo(info);
        //把product加入集合productslist
        productslist.add(product);
    }
    
    //展示现有商品-所有信息
    public void ShowAllProducts(){
        System.out.println("\n");
        if(productslist.size()==0) {
            System.out.println("目前还没有上架任何商品，请先上架商品");
            return;
        }
        for (int i = 0; i < productslist.size(); i++) {
            Products product=productslist.get(i);
            System.out.println("名称："+product.getName());
            System.out.println("价格："+product.getPrice());
            System.out.println("描述："+product.getInfo());
            System.out.println("------------------------------");
        }
    }

    //下架商品
    public boolean DeleteProduct(){
        if(productslist.size()==0) {
            System.out.println("目前还没有上架任何商品，请先上架商品");
            return false;
        }
        //创建Scanner对象
        Scanner sc =new Scanner(System.in);
        ShowAllProducts();
        System.out.print("请输入希望下架的商品的名称(如果不需要下架商品，请输入ESC)：");
        String target=sc.next();
        if(target.equals("ESC"));
        else{
            for (int i = 0; i < productslist.size(); i++) {
                Products product=productslist.get(i);
                if(target.equals(product.getName())){
                    System.out.println("已经查询到名为"+target+"的商品,即将删除目标商品。");
                    productslist.remove(i);
                    return true;
                }
            }
            System.out.println("未查询到名为"+target+"的商品");
        }
        return false;
    }
}
