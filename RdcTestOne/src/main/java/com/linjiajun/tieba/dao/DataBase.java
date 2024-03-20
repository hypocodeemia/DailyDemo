package com.linjiajun.tieba.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBase {
    //单例模式-懒汉式
    private static DataBase dataBase;//实例
    private Connection connection;   //用于后续获取Statement对象
    private String url;              //mysql链接路径--如果在其他机子上使用记得把"localhost"替换为公网ip
    private String user;             //mysql用户名
    private String passWord;         //mysql密码


    /*封装起来的无参构造器，用于获取Connection对象并赋值给connection*/
    private DataBase() throws Exception {

        try (InputStream inputDatabase = new FileInputStream("src/main/resources/database.properties");) {
            Properties prop = new Properties();
            //读取database.properties
            prop.load(inputDatabase);

            //从database.properties获取url,user,password
            url = prop.getProperty("mysql.url");
            user = prop.getProperty("mysql.user");
            passWord = prop.getProperty("mysql.password");

            //mysql5往后的驱动省略注册
            //获取链接
            this.connection = DriverManager.getConnection(url, user, passWord);

        } catch (Exception e) {
            //找不到database.properties或提供的url,user.password有误则报错
            e.printStackTrace();
        }
    }


    /*获取单例实例的方法-双重检查锁定,可能能用volatile修饰database来进一步完善*/
    public static DataBase getDataBase() throws Exception {
        // 如果实例为null，使用同步代码块创建新的实例
        if (dataBase == null) {
            synchronized (DataBase.class) {
                // 再次检查实例是否为null
                if (dataBase == null) {
                    // 创建新的实例
                    dataBase = new DataBase();
                }
            }
        }
        // 返回实例
        return dataBase;
    }


    /*通过实例获取Connection对象的方法*/
    public Connection getConnection() {
        return this.connection;
    }


    /*确保Connection对象已经close  ->  用在main的最后*/
    public void closeConnection(){
        //先判断connection是否为null
        if(connection!=null){
            try {
                //再判断connection是否已经被关闭
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
