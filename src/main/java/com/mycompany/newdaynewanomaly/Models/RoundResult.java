package com.mycompany.newdaynewanomaly.Models;

public class RoundResult {

    private final String nomeEntidade;
    private final boolean eraAnomalia;
    private final boolean jogadorAcertou;

    public RoundResult(String nomeEntidade, boolean eraAnomalia, boolean jogadorAcertou) {
        this.nomeEntidade = nomeEntidade;
        this.eraAnomalia = eraAnomalia;
        this.jogadorAcertou = jogadorAcertou;
    }

    public String getNomeEntidade() { return nomeEntidade; }
    public boolean isEraAnomalia() { return eraAnomalia; }
    public boolean isJogadorAcertou() { return jogadorAcertou; }
}