package com.bookverse.development.packapps.utils;

import java.util.HashMap;
import java.util.Map;

public class ArrayData {

  public static final String[] PATH_BACKGROUNDS = {
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

  public static final int[] WIDTH_BACKGROUNDS = {
      529, 1100, 960, 800, 1150,
      1150, 700, 600, 640, 650,
      920, 650, 600, 538};
  public static final int[] LONG_BACKGROUNDS = {
      660, 618, 540, 531, 646,
      646, 648, 625, 427, 650,
      602, 650, 644, 660
  };

  public static final String[][] WORD_LIST =
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

  public static Map<String,String> DATA_NEW_USER = new HashMap<String, String>(){{
    put("Name", "Santiago");
    put("LastName", "Moncada");
    put("Phone", "3012499315");
    put("Occupation", "Otro");
    put("Address", "CR 62 #62-b11");
    put("Username", "PackApps");
    put("Password", "PackApps123");
    put("Email", "packapps@gmail.com");
    put("Gender", "Masculino");
  }};
}