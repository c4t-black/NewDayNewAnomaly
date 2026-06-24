package com.mycompany.newdaynewanomaly.Models;

public class Player {

    private int Money;
    private int CurrentDay;

    private float Sanity;

    private int AnomaliesGuessed;
    private int HumansGuessed;


    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public int getCurrentDay() {
        return CurrentDay;
    }

    public void setCurrentDay(int currentDay) {
        CurrentDay = currentDay;
    }

    public float getSanity() {
        return Sanity;
    }

    public void setSanity(float sanity) {
        Sanity = sanity;
    }

    public int getAnomaliesGuessed() {
        return AnomaliesGuessed;
    }

    public void setAnomaliesGuessed(int anomaliesGuessed) {
        AnomaliesGuessed = anomaliesGuessed;
    }

    public int getHumansGuessed() {
        return HumansGuessed;
    }

    public void setHumansGuessed(int humansGuessed) {
        HumansGuessed = humansGuessed;
    }

    public Player(int money, int currentDay, float sanity, int anomaliesGuessed, int humansGuessed) {

        Money = money;
        CurrentDay = currentDay;
        Sanity = sanity;
        AnomaliesGuessed = anomaliesGuessed;
        HumansGuessed = humansGuessed;

    }

    public Player() {

        Money = 0;
        CurrentDay = 1;
        Sanity = 100;
        AnomaliesGuessed = 0;
        HumansGuessed = 0;

    }
}
