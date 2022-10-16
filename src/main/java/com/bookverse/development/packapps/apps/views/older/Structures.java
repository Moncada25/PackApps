package com.bookverse.development.packapps.apps.views.older;

import com.bookverse.development.packapps.apps.views.ArraysView;
import com.bookverse.development.packapps.apps.views.QueueView;
import com.bookverse.development.packapps.apps.views.StackView;
import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
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
    panel.setBorder(Resources.getBorder("Select structure"));

    btnStack = resources
        .getLabel("  Stack  ", Styles.TEXT_COLOR, panel, Styles.MEDIUM);
    btnStack.setBorder(Styles.BORDER_BLUE);
    btnStack.addMouseListener(this);

    btnQueue = resources
        .getLabel("  Queue  ", Styles.TEXT_COLOR, panel, Styles.MEDIUM);
    btnQueue.setBorder(Styles.BORDER_BLUE);
    btnQueue.addMouseListener(this);

    btnArrays = resources
        .getLabel("  Arrays  ", Styles.TEXT_COLOR, panel, Styles.MEDIUM);
    btnArrays.setBorder(Styles.BORDER_BLUE);
    btnArrays.addMouseListener(this);

    btnExit = resources
        .getLabel("  Return  ", Styles.MAIN_COLOR, panel, Styles.MEDIUM);
    btnExit.setBorder(Styles.BORDER_RED);
    btnExit.addMouseListener(this);

    return panel;
  }

  public void start(JFrame parent) {
    setSize(717, 380);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Structures of Data");
    Effects.fadeIn(this);
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
      Effects.fadeOut(this);
    } else if (e.getSource() == btnStack) {
      new StackView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == btnQueue) {
      new QueueView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == btnArrays) {
      new ArraysView(this, true).start(this);
      setVisible(true);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == btnExit) {
      btnExit.setCursor(Styles.HAND);
    } else if (e.getSource() == btnStack) {
      btnStack.setCursor(Styles.HAND);
    } else if (e.getSource() == btnQueue) {
      btnQueue.setCursor(Styles.HAND);
    } else if (e.getSource() == btnArrays) {
      btnArrays.setCursor(Styles.HAND);
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