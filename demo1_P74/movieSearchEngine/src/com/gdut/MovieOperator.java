package com.gdut;

public class MovieOperator {
    private Movie[] movies;

    public MovieOperator(Movie[] movies) {
        this.movies = movies;
    }

    public void printAllMoviesInfo() {
        System.out.println("-----以下是库中全部的电影-----");
        System.out.println("--------------------------");
        for (int i = 0; i < movies.length; i++) {
            System.out.println("编号:" + movies[i].getId());
            System.out.println("电影名:" + movies[i].getTitle());
            System.out.println("票价:" + movies[i].getPrice());
            System.out.println("--------------------------");
        }
        System.out.println("-----------END------------");
    }

    public void SearchID(int id) {
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].getId() == id) {
                System.out.println("编号:" + movies[i].getId());
                System.out.println("电影名:" + movies[i].getTitle());
                System.out.println("类型:" + movies[i].getType());
                System.out.println("评分:"+movies[i].getScore()+"/10");
                System.out.println("导演:"+movies[i].getDirector());
                System.out.println("主演:"+movies[i].getActors());
                System.out.println("时长:" + movies[i].getLength()+"分钟");
                System.out.println("票价:"+movies[i].getPrice()+"￥");
                return;
            }
        }
        System.out.println("未查询到ID:"+id+"对应的电影");
    }
}