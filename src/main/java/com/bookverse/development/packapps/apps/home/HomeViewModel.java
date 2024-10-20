package com.bookverse.development.packapps.apps.home;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import lombok.Data;

@Data
public class HomeViewModel {
  private JLabel welcome;
  private JMenuItem[] wallpapers = new JMenuItem[14];
  private JMenuItem darkMode;
  private JMenuItem defaultMode;
  private JMenuItem textureMode;
  private JMenuItem mintMode;
  private JMenuItem classicMode;
  private JMenuItem macMode;
  private JMenuItem grayMode;
}
