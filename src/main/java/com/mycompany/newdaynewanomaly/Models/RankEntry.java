package com.mycompany.newdaynewanomaly.Models;

/**
 * Representa uma entrada no ranking global,
 * agregando o melhor resultado de cada "save" pelo máximo de dias sobrevividos.
 */
public class RankEntry {

    private final int    position;
    private final int    maxDay;
    private final int    money;
    private final float  sanity;
    private final int    anomaliesGuessed;
    private final int    humansGuessed;

    public RankEntry(int position, int maxDay, int money, float sanity,
                     int anomaliesGuessed, int humansGuessed) {
        this.position         = position;
        this.maxDay           = maxDay;
        this.money            = money;
        this.sanity           = sanity;
        this.anomaliesGuessed = anomaliesGuessed;
        this.humansGuessed    = humansGuessed;
    }

    public int   getPosition()         { return position; }
    public int   getMaxDay()           { return maxDay; }
    public int   getMoney()            { return money; }
    public float getSanity()           { return sanity; }
    public int   getAnomaliesGuessed() { return anomaliesGuessed; }
    public int   getHumansGuessed()    { return humansGuessed; }
}
