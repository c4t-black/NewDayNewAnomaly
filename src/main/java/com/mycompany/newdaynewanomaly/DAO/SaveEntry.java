package com.mycompany.newdaynewanomaly.DAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa um resumo de save para exibição na tela de seleção.
 * Contém apenas os campos necessários para o usuário identificar o save.
 */
public class SaveEntry {

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final int           id;
    private final int           currentDay;
    private final int           money;
    private final float         sanity;
    private final LocalDateTime savedAt;

    public SaveEntry(int id, int currentDay, int money, float sanity, LocalDateTime savedAt) {
        this.id         = id;
        this.currentDay = currentDay;
        this.money      = money;
        this.sanity     = sanity;
        this.savedAt    = savedAt;
    }

    public int           getId()         { return id; }
    public int           getCurrentDay() { return currentDay; }
    public int           getMoney()      { return money; }
    public float         getSanity()     { return sanity; }
    public LocalDateTime getSavedAt()    { return savedAt; }

    /** Texto formatado para exibição no botão de save. */
    public String getDisplayText() {
        return String.format("Dia %d   |   $%d   |   %.0f%% san   |   %s",
                currentDay, money, sanity, FMT.format(savedAt));
    }
}
