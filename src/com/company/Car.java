package com.company;

public class Car {
    private int uniqueNumb;
    private int durationMoves;

    public Car(){
        this.uniqueNumb = 1000 + (int) (Math.random() * 9000);
        this.durationMoves = 1+(int)(Math.random()*10);
    }

    public void setDurationMoves(int durationMoves){
        this.durationMoves=durationMoves;
    }

    public int getDurationMoves(){
        return durationMoves;
    }


}
