package com.gdut.TakeawayMerchants;

public class Products {
    private String name;
    private double price;
    private String info;

    public Products() {
    }

    public Products(String name, double price, String info) {
        this.name = name;
        this.price = price;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        if(price<=0) System.out.println("*注意：价格设置异常!*");
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
