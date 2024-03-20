package com.linjiajun.tieba.dao;

import com.linjiajun.tieba.entity.User;
import com.linjiajun.tieba.util.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {


    /*通过userName获取对应的idUser*/
    public static int getUserId(String userName) throws Exception {
        int idUser = 0;
        //调用DataBase的方法获取实例，然后获取Connection对象
        DataBase dataBase = DataBase.getDataBase();
        Connection connection = dataBase.getConnection();

        String sql = "select id_user from user where username = ?;";
        //封装成PreparedStatement
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            //对?进行赋值
            preparedStatement.setString(1,userName);
            //获取ResultSet对象
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                //如果有对应的，则给idUser赋值
                if (resultSet.next()) {
                    idUser = resultSet.getInt(1);
                }
            }
        }
        return idUser;
    }



    /*注册用:检测注册时的用户名和昵称是否重复*/
    public static boolean isInfoDistinct(String userName, String nickName) throws Exception {
        boolean rs = true;
        //调用DataBase的方法获取实例，然后获取Connection对象
        DataBase dataBase = DataBase.getDataBase();
        Connection connection = dataBase.getConnection();

        //编写统一查重用sql语句
        String sql1 = "select * from user where username = ?;";
        String sql2 = "select * from user where nickname = ?;";
        //进行整合
        String sqls[] = {sql1, sql2};
        String targets[] = {userName, nickName};
        String sorts[] = {"用户名", "昵称"};
        //正式查重
        for (int i = 0; i < sqls.length; i++) {
            //封装成PreparedStatement
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqls[i])) {
                //对?进行赋值
                preparedStatement.setString(1, targets[i]);
                //获取ResultSet对象
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    //确认已有用户的用户名和昵称是否与新用户的有重复的
                    if (resultSet.next()) {
                        System.out.println(sorts[i] + ";" + targets[i] + " -> " + "重复");
                        rs = false;
                    }
                }
            }
        }
        return rs;
    }


    /*注册用： 在表user中insert一个新的用户 */
    public static void saveUser(String userName, String nickName, String hashValue, String salt) throws Exception {
        //获取DataBase对象
        DataBase dataBase = DataBase.getDataBase();
        //获得Connection对象
        Connection connection = dataBase.getConnection();

        //创建通用的用户注册sql语句
        String sql = "insert into user(username,nickname,hashvalue,salt) values(?,?,?,?);";
        //获取PreparedStatement对象
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            //设置参数
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, nickName);
            preparedStatement.setString(3, hashValue);
            preparedStatement.setString(4, salt);
            //执行sql语句
            preparedStatement.executeUpdate();
        }
    }


    /*登录用:检测输入的用户名是否存在，若存在则继续检测输入的密码是否正确*/
    public static boolean logInCheck(String userName, String password) throws Exception {
        //获取DataBase对象
        DataBase dataBase = DataBase.getDataBase();
        //获得Connection对象
        Connection connection = dataBase.getConnection();

        //创建通用的用户查找语句
        String sql = "select * from user where username = ?";

        //获取PreparedStatement对象
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            //对?进行赋值
            preparedStatement.setString(1, userName);
            //获取ResultSet对象
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                //如果根据用户名找不到对应的数据，则不存在该用户,直接return false
                if (!resultSet.next()) {
                    System.out.printf("不存在这个账户");
                    return false;
                }
                //如果找得到用户，那么读取它的hashvalue和salt(十六进制字符串)字段
                String hashValue = resultSet.getString("hashvalue");
                String hexSalt = resultSet.getString("salt");
                //把salt转换回byte[]
                byte[] salt = HashUtil.hexToBytes(hexSalt);
                //利用本次输入的密码和数据库中的salt(byte[])取算出对应的hashValue，判断与数据库中的hashvalue是否一致
                if (!HashUtil.generateHash(password, salt).equals(hashValue)) {
                    //不一致，密码输入错误
                    System.out.println("密码输入错误");
                    return false;
                }
                //一致，密码输入正确
                return true;
            }
        }
    }


    /*创建贴吧用:检测新贴吧的名字是否语句存在*/
    public static boolean createForumCheck(String nameForum) throws Exception {
        //获取DataBase对象
        DataBase dataBase = DataBase.getDataBase();
        //获得Connection对象
        Connection connection = dataBase.getConnection();

        //创建查找名字相同的贴吧
        String sql = "select * from forum where name_forum = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            //对?进行赋值
            preparedStatement.setString(1,nameForum);
            //获取ResultSet对象
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                //如果根据贴吧名找不到对应的数据，则不存在贴吧使用了相同的名字,直接返回true
                if (!resultSet.next()) {
                    return true;
                }
                //如果找得到,那么证明贴吧名重复了，返回false
                System.out.println("贴吧名重复");
                return false;
            }
        }
    }



    /*创建新贴吧用:存储新建贴吧的名字*/
    public static void saveForum(String nameForum,int idForumOwner) throws Exception {
        //获取DataBase对象
        DataBase dataBase = DataBase.getDataBase();
        //获得Connection对象
        Connection connection = dataBase.getConnection();

        //创建增加贴吧(DML,insert)的语句
        String sql = "insert into Forum(name_forum,id_forum_owner) values (?,?)";
        //获取PreparedStatement对象
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            //对?进行赋值
            preparedStatement.setString(1,nameForum);
            preparedStatement.setInt(2,idForumOwner);
            //执行sql语句
            preparedStatement.executeUpdate();
        }
    }

}
