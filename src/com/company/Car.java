package com.company;

public class Car {
    private int uniqueNumb;
    private int durationMoves;
    private String type;




    public Car(String type,int uniqueNumb){
        this.uniqueNumb=uniqueNumb;
        this.durationMoves = 1+(int)(Math.random()*10);
        this.type=type;
    }

    public void setDurationMoves(int durationMoves){
        this.durationMoves=durationMoves;
    }

    public int getDurationMoves(){
        return durationMoves;
    }

    public String getType(){
        return type;
    }




}
