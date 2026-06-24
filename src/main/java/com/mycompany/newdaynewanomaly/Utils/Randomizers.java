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

            int number = random.nextInt(25) + 1;

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


    public static Boolean getRandomChance() {

        int chance = random.nextInt(100) + 1;

        return chance > 65;


    }

    public static Boolean FalseInfo() {

        int chance = random.nextInt(10) + 1;

        return chance > 7;


    }

    public static Boolean HarderFalseInfo() {

        int chance = random.nextInt(100) + 1;

        return chance > 85;


    }
}
