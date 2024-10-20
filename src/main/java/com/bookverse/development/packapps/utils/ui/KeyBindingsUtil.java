package com.bookverse.development.packapps.utils.ui;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public final class KeyBindingsUtil {

  public static void addCopyPasteKeyBindings(JTextArea textArea, JLabel title, String text) {
    KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_DOWN_MASK);
    KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.META_DOWN_MASK);
    KeyStroke cut = KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.META_DOWN_MASK);
    KeyStroke selectAll = KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.META_DOWN_MASK);

    textArea.getInputMap().put(copy, "copy");
    textArea.getInputMap().put(paste, "paste");
    textArea.getInputMap().put(cut, "cut");
    textArea.getInputMap().put(selectAll, "selectAll");

    textArea.getActionMap().put("selectAll", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.selectAll();
      }
    });

    if (title != null) {

      textArea.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
          updateCharCount();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
          updateCharCount();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
          updateCharCount();
        }

        private void updateCharCount() {
          int charCount = textArea.getText().length();
          title.setText(String.format(text, charCount));
        }
      });
    }
  }

  private KeyBindingsUtil() {
    throw new IllegalStateException("Utility class");
  }
}
