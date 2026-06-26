package com.mycompany.newdaynewanomaly.Models;

public class Player {

    private int Money;
    private int CurrentDay;

    private float Sanity;

    private int AnomaliesGuessed;
    private int HumansGuessed;


    private int Cola;
    private int BigCola;
    private int Chips;

    private boolean Glasses;

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




    public int getCola() {
        return Cola;
    }

    public void setCola(int cola) {
        Cola = cola;
    }

    public int getBigCola() {
        return BigCola;
    }

    public void setBigCola(int bigCola) {
        BigCola = bigCola;
    }

    public int getChips() {
        return Chips;
    }

    public void setChips(int chips) {
        Chips = chips;
    }

    public boolean isGlasses() {
        return Glasses;
    }

    public void setGlasses(boolean glasses) {
        Glasses = glasses;
    }

    public Player(int money, int currentDay, float sanity, int anomaliesGuessed, int humansGuessed, int cola, int bigCola, int chips, boolean glasses) {
        Money = money;
        CurrentDay = currentDay;
        Sanity = sanity;
        AnomaliesGuessed = anomaliesGuessed;
        HumansGuessed = humansGuessed;
        Cola = cola;
        BigCola = bigCola;
        Chips = chips;
        Glasses = glasses;
    }

    public Player() {

        Money = 0;
        CurrentDay = 1;
        Sanity = 100;
        AnomaliesGuessed = 0;
        HumansGuessed = 0;

        this.Cola = 0;
        this.BigCola = 0;
        this.Chips = 0;
        this.Glasses = false;

    }
}
