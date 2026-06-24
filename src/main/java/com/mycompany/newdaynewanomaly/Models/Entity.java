package com.mycompany.newdaynewanomaly.Models;

import com.mycompany.newdaynewanomaly.Utils.*;
import com.mycompany.newdaynewanomaly.Utils.Cidade;

import java.util.Random;

public class Entity {

    private Boolean isAnomaly;

    private String LinkImage;

    private String nome;
    private Gender gender;

    private int idade;
    private String cidade;


    public String getLinkImage() {
        return LinkImage;
    }

    public void setLinkImage(String linkImage) {
        LinkImage = linkImage;
    }

    public Boolean getAnomaly() {
        return isAnomaly;
    }

    public void setAnomaly(Boolean anomaly) {
        isAnomaly = anomaly;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Entity(boolean isAnomaly) {

        if (isAnomaly == true) {

            // Construtor secundario para anomaly

        }

        if (isAnomaly == false) {

            this.gender = Randomizers.getRandomGender();

            this.nome = Randomizers.getRandomName(this.gender);

            this.idade = Randomizers.getRandomAge();

            this.cidade = Randomizers.getRandomCity();


            this.LinkImage = Randomizers.getRandomPerson(this.gender);

        }


    }
}
