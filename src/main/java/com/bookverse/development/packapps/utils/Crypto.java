package com.bookverse.development.packapps.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.bookverse.development.packapps.models.DataSet;
import com.bookverse.development.packapps.utils.ui.Alerts;

public final class Crypto {

  public static String encrypt(String text, boolean useDefaultKey) {

    String base64EncryptedString = "";

    try {

      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestOfPassword = md
          .digest(getSecretKey(useDefaultKey).getBytes(StandardCharsets.UTF_8));
      byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

      SecretKey key = new SecretKeySpec(keyBytes, "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE, key);

      byte[] plainTextBytes = text.getBytes(StandardCharsets.UTF_8);
      byte[] buf = cipher.doFinal(plainTextBytes);
      byte[] base64Bytes = Base64.encodeBase64(buf);
      base64EncryptedString = new String(base64Bytes);

    } catch (Exception ex) {
      Alerts.message("Error", ex.getMessage());
    }
    return base64EncryptedString;
  }

  public static String decrypt(String text, boolean isEmail) {

    String base64EncryptedString = "";

    try {
      byte[] message = Base64.decodeBase64(text.getBytes(StandardCharsets.UTF_8));
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestOfPassword = md.digest(getSecretKey(isEmail).getBytes(StandardCharsets.UTF_8));
      byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
      SecretKey key = new SecretKeySpec(keyBytes, "AES");

      Cipher decipher = Cipher.getInstance("AES");
      decipher.init(Cipher.DECRYPT_MODE, key);

      byte[] plainText = decipher.doFinal(message);

      base64EncryptedString = new String(plainText, StandardCharsets.UTF_8);

    } catch (Exception ex) {
      Alerts.message("Error", ex.getMessage());
    }

    if (base64EncryptedString.equals("")) {

      Alerts.message("Verify!",
          "The text entered does not have encrypted content or the key is incorrect");
      return text;
    } else {
      return base64EncryptedString;
    }
  }

  private static String setSecretKey() {

    boolean canContinue;
    String value;

    do {

      value = Alerts.inputPassword("Enter secret key");

      if (value.length() >= 2) {
        canContinue = true;
      } else {
        Alerts.message("Warnings", "Invalid text or length too short.");
        canContinue = false;
      }

    } while (!canContinue);

    return value;
  }

  private static String getSecretKey(boolean useDefaultKey) {

    if (!useDefaultKey) {
      return setSecretKey();
    } else {
      return DataSet.getDefaultEncryptKey();
    }
  }

  private Crypto() {
  }
}
