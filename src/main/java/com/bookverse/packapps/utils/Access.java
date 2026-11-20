package com.bookverse.packapps.utils;

import com.bookverse.packapps.database.DatabaseConnection;
import com.bookverse.packapps.utils.ui.Alerts;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import lombok.SneakyThrows;

public final class Access {
  private Access() {}

  @SneakyThrows
  private static byte[] derive(String password, byte[] salt) {
    var spec = new PBEKeySpec(password.toCharArray(), salt, 600000, 256);
    try { return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded(); }
    finally { spec.clearPassword(); }
  }

  public static String hash(String password) {
    byte[] salt = new byte[16];
    new SecureRandom().nextBytes(salt);
    var base64 = Base64.getEncoder();
    return "pbkdf2$" + base64.encodeToString(salt) + "$" + base64.encodeToString(derive(password,salt));
  }

  public static boolean matchesAccount(String password, String stored) {
    if (!stored.startsWith("pbkdf2$")) {
      return System.getenv("PACKAPPS_LEGACY_KEY") != null && stored.equals(Auth.encode(password,true));
    }
    String[] parts = stored.split("\\$", -1);
    if (parts.length != 3) return false;
    try {
      var decoder = Base64.getDecoder();
      return MessageDigest.isEqual(decoder.decode(parts[2]), derive(password,decoder.decode(parts[1])));
    } catch (IllegalArgumentException invalid) { return false; }
  }

  public static boolean verify(String role, String password) {
    String configured = System.getenv("PACKAPPS_" + role.toUpperCase(java.util.Locale.ROOT) + "_PASSWORD");
    if (configured != null && !configured.isBlank()) return configured.equals(password);
    if (System.getenv("PACKAPPS_MYSQL_URL") != null) {
      Alerts.message("Configuration", "Configure the " + role + " password before using MySQL.");
      return false;
    }
    var directory = DatabaseConnection.dataDirectory();
    var file = directory.resolve(role + ".hash");
    try {
      Files.createDirectories(directory);
      if (!Files.exists(file)) {
        var field = new JPasswordField();
        int answer = JOptionPane.showConfirmDialog(null, field, "First use: confirm your new " + role + " password (8+ characters)",
            JOptionPane.OK_CANCEL_OPTION);
        if (answer != JOptionPane.OK_OPTION || password.length() < 8 || !password.equals(new String(field.getPassword()))) return false;
        Files.writeString(file, hash(password), StandardOpenOption.CREATE_NEW);
      }
      return matchesAccount(password, Files.readString(file));
    } catch (java.io.IOException e) {
      Alerts.error(e, "Access configuration");
      return false;
    }
  }
}
