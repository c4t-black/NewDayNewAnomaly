package com.mycompany.newdaynewanomaly.Utils;

import java.util.Random;

public class Randomizers {

    static Random random = new Random();

    public static Gender getRandomGender() {

        Gender[] RandomGender = Gender.values();

        int index = random.nextInt(RandomGender.length);

        return RandomGender[index];

    }

    public static String getRandomName(Gender gender) {


        if (gender == Gender.Masculino) {

            MasculineNames[] RandomManName = MasculineNames.values();
            int index = random.nextInt(RandomManName.length);

            return RandomManName[index].toString();

        }

        if (gender == Gender.Feminino) {

            FeminineNames[] RandomWomanName = FeminineNames.values();
            int index = random.nextInt(RandomWomanName.length);

            return RandomWomanName[index].toString();

        }

        return null;

    }

    public static int getRandomAge() {

        return  random.nextInt(60) + 17;

    }

    public static String getRandomCity() {

        Cidade[] RandomCity = Cidade.values();
        int index = random.nextInt(RandomCity.length);

        return RandomCity[index].toString();

    }

    public static String getRandomPerson(Gender gender) {

        if (gender == Gender.Masculino) {

            int number = random.nextInt(24) + 1;

            String link = "/com/mycompany/newdaynewanomaly/Images/Entities/Man/homem" + number + ".png";

            System.out.println(link);

            return link;

        }

        if (gender == Gender.Feminino) {

            int number = random.nextInt(9) + 1;

            String link = "/com/mycompany/newdaynewanomaly/Images/Entities/Women/mulher" + number + ".png";

            System.out.println(link);

            return link;

        }

        return "/com/mycompany/newdaynewanomaly/Images/Entities/NoSignal.png";
    }

    public static String getRandomEye(Gender gender, Boolean isInfected, Boolean isAnomaly) {

        if (gender == Gender.Masculino && isInfected == false) {

            int number = random.nextInt(3) + 1;

            String link = "/com/mycompany/newdaynewanomaly/Images/Eyes/Male/eye" + number + ".png";

            System.out.println(link);

            return link;

        }

        if (gender == Gender.Feminino && isInfected == false) {

            int number = random.nextInt(3) + 1;

            String link = "/com/mycompany/newdaynewanomaly/Images/Eyes/Female/eye" + number + ".png";

            System.out.println(link);

            return link;

        }

        if (isInfected && isAnomaly) {

            int number = random.nextInt(10) + 1;

            if (number > 4 ) {

                int number2 = random.nextInt(4) + 1;
                return "/com/mycompany/newdaynewanomaly/Images/Eyes/InfectedEvil/eye" + number2 + ".png";


            }

            return "/com/mycompany/newdaynewanomaly/Images/Eyes/InfectedNormal/eye1.png";

        }

        if (isInfected && !isAnomaly) {

            return "/com/mycompany/newdaynewanomaly/Images/Eyes/InfectedNormal/eye1.png";

        }



        return "/com/mycompany/newdaynewanomaly/Images/Entities/NoSignal.png";
    }




    public static Boolean getRandomChance() {

        int chance = random.nextInt(100) + 1;

        return chance > 65;


    }

    public static Boolean getRandomChanceEasy() {

        int chance = random.nextInt(100) + 1;

        return chance > 30;


    }

    public static Boolean FalseInfo() {

        int chance = random.nextInt(10) + 1;

        return chance > 7;


    }

    public static Boolean HarderFalseInfo() {

        int chance = random.nextInt(100) + 1;

        return chance > 85;


    }

    public static int rollSanityNumber() {

        // Peso de cada número de 1 a 20.
        // 1-13: peso fixo (mais fácil)
        // 14-20: peso decrescente (mais difícil conforme aumenta)

        int[] weights = new int[20];

        for (int i = 0; i < 13; i++) {
            weights[i] = 10; // números 1 a 13: peso 10 cada
        }
        for (int i = 13; i < 20; i++) {
            // número 14 -> peso 8, 15 -> 6, 16 -> 5, ... 20 -> 1
            weights[i] = Math.max(1, 9 - (i - 12) * 2);
        }

        int totalWeight = 0;
        for (int w : weights) totalWeight += w;

        int roll = random.nextInt(totalWeight);
        int cumulative = 0;

        for (int i = 0; i < weights.length; i++) {
            cumulative += weights[i];
            if (roll < cumulative) {
                return i + 1; // +1 porque o array é 0-indexado
            }
        }

        return 1; // fallback, nunca deve chegar aqui
    }
}
