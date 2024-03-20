package com.linjiajun.tieba.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

//SHA-256加盐加密
public class HashUtil {

    /*生成随机盐值(此处选择生成16个字节的salt) -> 返回盐*/
    public static byte[] createSalt() {
        //使用SecureRandom而非Random,是因为Random类基于一个可以预测的算法生成随机数。如果攻击者知道算法和种子值就可以轻易破解
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }


    // 使用SHA-256算法和盐值对密码进行哈希 -> 返回SHA-256加盐加密后的hashValue
    public static String generateHash(String password, byte[] salt) {
        String hashValue = null;
        try {
            // 创建MessageDigest实例用于哈希
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 将盐值添加到哈希中
            md.update(salt);
            // 执行哈希操作
            byte[] bytes = md.digest(password.getBytes());
            // 将字节转换为十六进制格式
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // 获得哈希后的密码
            hashValue = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashValue;
    }

    // byte数组转十六进制字符串
    public static String bytesToHex(byte[] bytes) {
        // 创建一个StringBuilder来构建最终的十六进制字符串
        StringBuilder hexString = new StringBuilder();
        // 遍历byte数组
        for (byte b : bytes) {
            // 将每个byte值转换为十六进制字符串
            String hex = Integer.toHexString(0xff & b);
            // 如果十六进制字符串只有一个字符，那么前面补一个'0'
            if (hex.length() == 1) {
                hexString.append('0');
            }
            // 将生成的十六进制字符串添加到StringBuilder中
            hexString.append(hex);
        }
        // 返回构建好的十六进制字符串
        return hexString.toString();
    }

    // 十六进制字符串转byte数组
    public static byte[] hexToBytes(String hexString) {
        // 获取十六进制字符串的长度
        int len = hexString.length();
        // 根据十六进制字符串的长度创建一个byte数组，长度为字符串长度的一半
        byte[] data = new byte[len / 2];
        // 每两个字符描述一个byte，所以步长为2
        for (int i = 0; i < len; i += 2) {
            // 将每对十六进制字符转换为一个byte值
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        // 返回构建好的byte数组
        return data;
    }

}
