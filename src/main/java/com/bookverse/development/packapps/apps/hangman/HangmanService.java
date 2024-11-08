package com.bookverse.development.packapps.apps.hangman;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import lombok.Data;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Resources;

@Data
public class HangmanService {
  private int minutesTimer = 2;
  private int secondsTimer = 0;
  private int countAttempts = 0;
  private int maxAttempts = 7;
  private boolean lyricsEquals = false;
  private String lines = "";
  private String randomWord;
  private String lyrics = "";
  private char[] secretWord = new char[13];
  private char[] word = new char[13];
  public List<String> categories = List.of(
      "Animals",
      "Colors",
      "Sports",
      "Fruits",
      "Verbs",
      "Countries"
  );

  public boolean btnPlayAP(HangmanViewModel model) {
    if ("Select a option".equals(Objects.requireNonNull(model.getOptions().getSelectedItem()).toString())) {
      Alerts.message("Message", "Select a category");
      resetModel(model);
      return false;
    }

    disableModelOptions(model);
    initializeGame(model);

    String option = model.getOptions().getSelectedItem().toString();
    int randomInt = GeneralUtils.getIntRandom(0, 14);
    randomWord = getSecretWord(option, randomInt);

    StringBuilder linesBuilder = new StringBuilder();
    int size = randomWord.length() - 1;
    for (int i = 0; i <= size; i++) {
      secretWord[i] = randomWord.charAt(i);
      linesBuilder.append("_ ");
    }

    lines = linesBuilder.toString();
    IntStream.rangeClosed(0, size).forEach(i -> word[i] = lines.charAt(i));
    model.getLyricsNumber().setText("Lyrics: " + randomWord.length());
    model.getTxtWord().setText(lines);

    return true;
  }

  private void resetModel(HangmanViewModel model) {
    countAttempts = 0;
    model.getAttempts().setText("");
    model.getTxtWord().setText("");
    model.getTime().setText("");
  }

  private void disableModelOptions(HangmanViewModel model) {
    model.getOptions().setEnabled(false);
    model.getBtnExit().setEnabled(false);
    model.getBtnPlay().setEnabled(false);
  }

  private void initializeGame(HangmanViewModel model) {
    randomWord = null;
    countAttempts = 0;
    model.getTxtWord().setText("");
    model.getTime().setText("");
    model.getAttempts().setText("Made mistakes: " + countAttempts);
    lines = "";
    model.getParent().repaint();

    IntStream.rangeClosed(0, word.length - 1).forEach(i -> {
      word[i] = ' ';
      secretWord[i] = ' ';
    });
  }

  private boolean compare(HangmanViewModel model, char lyricActual) {

    model.getTxtWord().setText("");
    String lyric = lines;
    lines = "";

    IntStream.rangeClosed(0, randomWord.length() - 1).forEach(j -> {
      if (randomWord.charAt(j) == lyricActual) {
        word[j] = lyricActual;
        lyricsEquals = true;
      }
      lines += word[j] + " ";
    });

    if (!lyricsEquals) {
      countAttempts++;
      model.getAttempts().setText("Made mistakes: " + countAttempts);
      lines = lyric;
    }

    model.getTxtWord().setText(lines);

    lyricsEquals = false;
    model.getParent().repaint();
    win(model);
    return validaLosser(model);
  }

  public boolean validaLosser(HangmanViewModel model) {
    if (countAttempts == maxAttempts) {
      model.getTxtWord().setText("");
      lines = "";

      model.getImage().setIcon(new ImageIcon(Resources.getImage("dead.png")));

      Alerts.message("You lose", "Correct word: " + randomWord);

      insert("Loser", model);
      reset(model);
      return true;
    }

    return false;
  }

  public void win(HangmanViewModel model) {

    if (validateWinner()) {

      model.getImage().setIcon(new ImageIcon(Resources.getImage("win.png")));

      if (countAttempts >= 0 && countAttempts <= 4) {
        Alerts.message("You win!", "Pretty easy, right?");
      } else {
        Alerts.message("You win!", "All right, you made it!");
      }

      insert("Winner", model);
      reset(model);
    }
  }

  public boolean validateWinner() {

    boolean win = false;

    for (int i = 0; i <= secretWord.length - 1; i++) {

      if (word[i] == secretWord[i]) {
        win = true;
      } else {
        win = false;
        break;
      }
    }

    return win;
  }

  public void reset(HangmanViewModel model) {
    model.getBtnPlay().setEnabled(true);
    model.getOptions().setEnabled(true);
    model.getBtnExit().setEnabled(true);
    model.getImage().setIcon(null);
    countAttempts = 0;
    lyrics = "";
    model.getOptions().setSelectedIndex(0);
    model.getAttempts().setText("");
    model.getTxtWord().setText("");
    model.getLyricsNumber().setText("");
    model.getLyricsPressed().setText("");
    model.getTime().setText("");
  }

  public void insert(String state, HangmanViewModel model) {

    if (GeneralUtils.verifyConnection("Data don't saved", true) && Alerts.saveGame()) {

      String[] data = {DatabaseConstants.HANGMAN, Alerts.inputText("Enter a Nickname", 20),
          String.valueOf(countAttempts), state,
          Objects.requireNonNull(model.getOptions().getSelectedItem()).toString(),
          Format.getDate()};

      OlderRepository.insertData(data);
    }
  }

  public boolean onlyLyrics(char lyric, KeyEvent evt, HangmanViewModel model) {
    if ((lyric < 'a' || lyric > 'z') && lyric != 'ñ') {
      Alerts.message("Warning", "Only lowercase letters are allowed");
      evt.consume();
      return false;
    } else {
      String l = Character.toString(lyric);

      if (!lyrics.contains("[" + l + "]")) {
        lyrics += "[" + l + "]";
        model.getLyricsPressed().setText("Lyrics pressed " + lyrics);
      }
      return compare(model, lyric);
    }
  }

  private String getSecretWord(String category, int index) {
    return switch (category) {
      case "Animals" -> List.of(
          "gato", "perro", "ardilla", "pez", "pajaro", "lombris", "zorra", "elefante", "leon",
          "paloma", "rana", "panda", "tortuga", "leopardo", "jirafa"
      ).get(index);
      case "Colors" -> List.of("amarillo", "azul", "verde", "rojo", "morado", "blanco", "negro", "cafe", "naranjado",
          "gris", "rosado", "celeste", "turquesa", "dorado", "plateado").get(index);
      case "Sports" -> List.of(
          "futbol", "baloncesto", "tenis", "natacion", "voleibol", "ciclismo", "golf", "hockey",
          "karate", "esgrima", "boxeo", "atletismo", "rugby", "beisbol", "paracaidismo"
      ).get(index);
      case "Fruits" -> List.of(
          "pera", "mango", "limon", "sandia", "banana", "manzana", "naranja", "mandarina", "fresa",
          "coco", "papaya", "kiwi", "cereza", "uva", "ciruela"
      ).get(index);
      case "Verbs" -> List.of(
          "begin", "break", "choose", "draw", "drive", "understand", "spend", "speak", "write",
          "wear", "mean", "build", "bring", "find", "leave"
      ).get(index);
      case "Countries" -> List.of(
          "colombia", "rusia", "venezuela", "brasil", "francia", "italia", "china", "uruguay",
          "japon", "españa", "argentina", "alemania", "suiza", "suecia", "inglaterra"
      ).get(index);
      default -> throw new IllegalStateException("Unexpected value: " + category);
    };
  }
}
