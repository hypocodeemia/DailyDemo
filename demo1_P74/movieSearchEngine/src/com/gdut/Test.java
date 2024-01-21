package com.gdut;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //菜单所需的变量

        int choice = 0;
        //定义电影库数组变量--(可替换为for套一连串手动输入setxxx())
        Movie movies[] = new Movie[3];
        movies[0] = new Movie(1, "The Shawshank Redemption", "剧情 / 犯罪", 9.7, "弗兰克·德拉邦特", "蒂姆·罗宾斯，摩根·弗里曼", 142,100);
        movies[1] = new Movie(2, "The Truman Show", "剧情 / 科幻", 9.4, "彼得·威尔", "金·凯瑞，劳拉·琳妮", 103,110);
        movies[2] = new Movie(3, "Titanic ", "剧情 / 爱情 / 灾难", 9.5, "詹姆斯·卡梅隆", " 莱昂纳多·迪卡普里奥 / 凯特·温丝莱特 / 比利·赞恩", 194,120);
        // 定义operator和Scanner
        MovieOperator operator = new MovieOperator(movies);
        Scanner scanner = new Scanner(System.in);

        //菜单实现
        while (choice != 3) {
            System.out.println();
            System.out.println("╔════════════════════════════╗");
            System.out.println("║---------电影搜索程序---------║");
            System.out.println("║1.粗略呈现库中所有电影         ║");
            System.out.println("║2.搜索电影                   ║");
            System.out.println("║3.退出程序                   ║");
            System.out.println("╚════════════════════════════╝");
            System.out.print("请输入指令:");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    operator.printAllMoviesInfo();
                    break;
                case 2:
                    System.out.print("请输入需要查询的电影ID:");
                    int id = scanner.nextInt();
                    operator.SearchID(id);
                    break;
                case 3:
                    System.out.println("----------即将退出程序----------");
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
            }
        }
    }
}
