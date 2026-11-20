package com.bookverse.packapps.apps.ocr;

import static org.bytedeco.leptonica.global.leptonica.pixDestroy;
import static org.bytedeco.leptonica.global.leptonica.pixRead;

import com.bookverse.packapps.database.DatabaseConnection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.bytedeco.tesseract.TessBaseAPI;

public class OcrService {
  private static Path models() throws IOException {
    Path dir = DatabaseConnection.dataDirectory().resolve("tessdata-v5");
    Files.createDirectories(dir);
    for (String lang : new String[] {"eng", "spa"}) {
      String name = lang + ".traineddata";
      if (Files.isRegularFile(dir.resolve(name))) continue;
      Path temp = Files.createTempFile(dir, lang, ".tmp");
      try (var in = OcrService.class.getResourceAsStream("/tessdata/" + name)) {
        Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);
        Files.move(temp, dir.resolve(name), StandardCopyOption.ATOMIC_MOVE);
      } finally {
        Files.deleteIfExists(temp);
      }
    }
    return dir;
  }

  public String readText(String image, boolean onlyText) {
    if (image == null || image.isBlank()) return "Error";
    try (var api = new TessBaseAPI()) {
      if (api.Init(models().toString(), "eng+spa") != 0) return "Error";
      var pixels = pixRead(image);
      if (pixels == null || pixels.isNull()) return "Error";
      try {
        api.SetImage(pixels);
        var result = onlyText ? api.GetUTF8Text() : api.GetHOCRText(0);
        if (result == null || result.isNull()) return "Error";
        try {
          return result.getString(java.nio.charset.StandardCharsets.UTF_8);
        } finally {
          org.bytedeco.tesseract.global.tesseract.TessDeleteText(result);
        }
      } finally {
        api.End();
        pixDestroy(pixels);
      }
    } catch (IOException | RuntimeException e) {
      return "Error";
    }
  }
}
