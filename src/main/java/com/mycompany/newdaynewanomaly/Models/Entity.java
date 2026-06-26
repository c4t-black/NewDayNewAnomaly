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

    private String eyes;


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

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public Entity(boolean isAnomaly) {

        this.isAnomaly = isAnomaly;

        if (isAnomaly == true) {

            this.gender = Randomizers.getRandomGender();


            if ( Randomizers.HarderFalseInfo() ) {

                Gender genderOposto = (this.gender == Gender.Masculino) ? Gender.Feminino : Gender.Masculino;

                this.nome = Randomizers.getRandomName(genderOposto);

            } else {

                this.nome = Randomizers.getRandomName(this.gender);

            }

            if (Randomizers.HarderFalseInfo()) {

                this.idade = Randomizers.getRandomAge() + 60;

            }

            this.idade = Randomizers.getRandomAge();

            this.cidade = Randomizers.getRandomCity();

            if ( Randomizers.FalseInfo() ) {

                Gender genderOposto = (this.gender == Gender.Masculino) ? Gender.Feminino : Gender.Masculino;

                this.LinkImage = Randomizers.getRandomPerson(genderOposto);

            } else {

                this.LinkImage = Randomizers.getRandomPerson(this.gender);

            }

            this.eyes = Randomizers.getRandomEye(this.gender, Randomizers.getRandomChanceEasy(), true);


        }

        if (isAnomaly == false) {

            this.gender = Randomizers.getRandomGender();

            this.nome = Randomizers.getRandomName(this.gender);

            this.idade = Randomizers.getRandomAge();

            this.cidade = Randomizers.getRandomCity();


            this.LinkImage = Randomizers.getRandomPerson(this.gender);

            this.eyes = Randomizers.getRandomEye(this.gender, Randomizers.getRandomChance(), false);


        }


    }
}
