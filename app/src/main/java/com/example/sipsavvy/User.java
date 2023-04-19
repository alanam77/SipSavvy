package com.example.sipsavvy;

public class User {
    private static String USER_NAME = "";
    private static int ft = 0;
    private static int in = 0;
    private static double weight = 0;
    private static int age = 0;
    public User(String userName, int feet, int inches, double weight, int age){
        USER_NAME = userName;
        ft = feet;
        in = inches;
        this.weight = weight;
        this.age = age;
    }
    public void setUserName(String userName) {
        USER_NAME = userName;
    }
    public String getUserName() {
        return USER_NAME;
    }
    public void setFt(int ft) {
        this.ft = ft;
    }
    public int getFt() {
        return ft;
    }
    public void setIn(int in){
        this.in = in;
    }
    public int getIn() {
        return in;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
}
