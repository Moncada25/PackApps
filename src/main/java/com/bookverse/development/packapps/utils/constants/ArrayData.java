package com.bookverse.development.packapps.utils.constants;

import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.Map;

public class ArrayData {

  public static String getPathBackground(int index) {

    String[] backgrounds = {
        "img1.jpg",
        "img2.jpg",
        "img3.jpg",
        "img4.jpg",
        "img5.jpg",
        "img6.jpg",
        "img7.jpg",
        "img8.jpg",
        "img9.jpg",
        "img10.jpg",
        "img11.jpg",
        "img12.jpg",
        "img13.jpg",
        "img14.jpg"
    };

    return backgrounds[index];
  }

  public static int getWidthBackground(int index) {

    int[] widthBackgrounds = {
        529, 1100, 960, 800, 1150,
        1150, 700, 600, 640, 650,
        920, 650, 600, 538};

    return widthBackgrounds[index];
  }

  public static int getLongBackground(int index) {

    int[] longBackgrounds = {
        660, 618, 540, 531, 646,
        646, 648, 625, 427, 650,
        602, 650, 644, 660};

    return longBackgrounds[index];
  }

  public static String getSecretWord(int x, int y) {

    String[][] listWord =
        {{"pera", "mango", "limon", "sandia", "banana", "manzana", "naranja", "mandarina", "fresa",
            "coco", "papaya", "kiwi", "cereza", "uva", "ciruela"},
            {"gato", "perro", "ardilla", "pez", "pajaro", "lombris", "zorra", "elefante", "leon",
                "paloma", "rana", "panda", "tortuga", "leopardo", "jirafa"},
            {"colombia", "rusia", "venezuela", "brasil", "francia", "italia", "china", "uruguay",
                "japon", "españa", "argentina", "alemania", "suiza", "suecia", "inglaterra"},
            {"amarillo", "azul", "verde", "rojo", "morado", "blanco", "negro", "cafe", "naranjado",
                "gris", "rosado", "celeste", "turquesa", "dorado", "plateado"},
            {"begin", "break", "choose", "draw", "drive", "understand", "spend", "speak", "write",
                "wear", "mean", "build", "bring", "find", "leave"},
            {"futbol", "baloncesto", "tenis", "natacion", "voleibol", "ciclismo", "golf", "hockey",
                "karate", "esgrima", "boxeo", "atletismo", "rugby", "beisbol", "paracaidismo"}};

    return listWord[x][y];
  }

  public static String getDataUser(String key) {

    Faker faker = Faker.instance();

    Map<String, String> dataNewUser = new HashMap<>();
    dataNewUser.put("Name", faker.name().firstName());
    dataNewUser.put("LastName", faker.name().lastName());
    dataNewUser.put("Phone", faker.phoneNumber().cellPhone());
    dataNewUser.put("Occupation", "Otro");
    dataNewUser.put("Address", faker.address().fullAddress());
    dataNewUser.put("Username", faker.name().username());
    dataNewUser.put("Password", faker.random().hex());
    dataNewUser.put("Email", faker.internet().emailAddress());
    dataNewUser.put("Gender", "Masculino");

    return dataNewUser.get(key);
  }

  public static String getCountryCode(String key) {

    Map<String, String> countryCodes = new HashMap<>();
    countryCodes.put("Argentina", "54");
    countryCodes.put("Bolivia", "591");
    countryCodes.put("Brasil", "55");
    countryCodes.put("Colombia", "57");
    countryCodes.put("Costa Rica", "506");
    countryCodes.put("Chile", "56");
    countryCodes.put("Ecuador", "593");
    countryCodes.put("España", "34");
    countryCodes.put("Estados Unidos", "1");
    countryCodes.put("Venezuela", "58");

    return countryCodes.get(key);
  }
}