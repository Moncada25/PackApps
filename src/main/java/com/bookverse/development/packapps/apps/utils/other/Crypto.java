package com.bookverse.development.packapps.apps.utils.other;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.bookverse.development.packapps.apps.utils.ui.Alerts;

import static com.bookverse.development.packapps.apps.utils.constants.AppConfig.DEFAULT_ENCRYPT_KEY;

public final class Crypto {

  public static String encrypt(String text, boolean useDefaultKey) {
    try {
      SecretKey key = generateKey(getSecretKey(useDefaultKey));
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE, key);

      byte[] plainTextBytes = text.getBytes(StandardCharsets.UTF_8);
      byte[] buf = cipher.doFinal(plainTextBytes);
      return Base64.encodeBase64String(buf);

    } catch (Exception e) {
      Alerts.message("Verify!", "The text entered does not have encrypted content or the key is incorrect");
      return "";
    }
  }

  public static String decrypt(String text, boolean isEmail) {
    try {
      byte[] message = Base64.decodeBase64(text.getBytes(StandardCharsets.UTF_8));
      SecretKey key = generateKey(getSecretKey(isEmail));
      Cipher decipher = Cipher.getInstance("AES");
      decipher.init(Cipher.DECRYPT_MODE, key);

      byte[] plainText = decipher.doFinal(message);
      return new String(plainText, StandardCharsets.UTF_8);

    } catch (Exception e) {
      Alerts.message("Verify!", "The text entered does not have encrypted content or the key is incorrect");
      return text;
    }
  }

  private static SecretKey generateKey(String secret) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] digestOfPassword = md.digest(secret.getBytes(StandardCharsets.UTF_8));
    byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
    return new SecretKeySpec(keyBytes, "AES");
  }

  private static String setSecretKey() {
    String value;
    do {
      value = Alerts.inputPassword("Enter secret key");
      if (value.length() < 2) {
        Alerts.message("Warnings", "Invalid text or length too short.");
      }
    } while (value.length() < 2);
    return value;
  }

  private static String getSecretKey(boolean useDefaultKey) {
    return useDefaultKey ? Config.get(DEFAULT_ENCRYPT_KEY.getProperty()) : setSecretKey();
  }

  private Crypto() {
  }
}
