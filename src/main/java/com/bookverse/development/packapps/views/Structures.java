package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

public class Structures extends JDialog implements MouseListener {

  private Resources resources = new Resources();
  private JLabel btnStack, btnQueue, btnExit, btnArrays;

  public Structures(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  @NotNull
  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBorder(Settings.getBorder("Select structure"));

    btnStack = resources
        .getLabel("  Stack  ", Settings.TEXT_COLOR, panel, Settings.MEDIUM);
    btnStack.setBorder(Settings.BORDER_BLUE);
    btnStack.addMouseListener(this);

    btnQueue = resources
        .getLabel("  Queue  ", Settings.TEXT_COLOR, panel, Settings.MEDIUM);
    btnQueue.setBorder(Settings.BORDER_BLUE);
    btnQueue.addMouseListener(this);

    btnArrays = resources
        .getLabel("  Arrays  ", Settings.TEXT_COLOR, panel, Settings.MEDIUM);
    btnArrays.setBorder(Settings.BORDER_BLUE);
    btnArrays.addMouseListener(this);

    btnExit = resources
        .getLabel("  Return  ", Settings.MAIN_COLOR, panel, Settings.MEDIUM);
    btnExit.setBorder(Settings.BORDER_RED);
    btnExit.addMouseListener(this);

    return panel;
  }

  public void start(JFrame parent) {
    setSize(717, 380);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Structures of data");
    Settings.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesEstructuras();
    setVisible(true);
  }

  private void createComponents() {

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("estructuras.png")).getImage());

    add(getPanel(), BorderLayout.SOUTH);

    ImageIcon image = new ImageIcon(resources.getImage("estructuras_datos.jpg"));
    JLabel wallpaper = new JLabel();
    wallpaper.setIcon(image);
    wallpaper.setSize(711, 284);
    add(wallpaper, BorderLayout.CENTER);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == btnExit) {
      Settings.fadeOut(this);
    } else if (e.getSource() == btnStack) {
      new Stack(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == btnQueue) {
      new Queue(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == btnArrays) {
      new Arrays(this, true).start(this);
      setVisible(true);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == btnExit) {
      btnExit.setCursor(Settings.HAND);
    } else if (e.getSource() == btnStack) {
      btnStack.setCursor(Settings.HAND);
    } else if (e.getSource() == btnQueue) {
      btnQueue.setCursor(Settings.HAND);
    } else if (e.getSource() == btnArrays) {
      btnArrays.setCursor(Settings.HAND);
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}