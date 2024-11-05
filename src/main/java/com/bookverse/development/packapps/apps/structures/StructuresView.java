package com.bookverse.development.packapps.apps.structures;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.bookverse.development.packapps.apps.arrays.ArraysView;
import com.bookverse.development.packapps.apps.queues.QueuesView;
import com.bookverse.development.packapps.apps.stacks.StackView;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.ui.factory.Label;

public class StructuresView extends JDialog {

  private JLabel btnStack;
  private JLabel btnQueue;
  private JLabel btnExit;
  private JLabel btnArrays;

  public StructuresView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
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

  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBorder(Resources.getBorder("Select structure"));

    btnStack = new Label().setText("  Stack  ").setColor(Styles.TEXT_COLOR).setFont(Styles.MEDIUM)
        .build();
    btnStack.setBorder(Styles.BORDER_BLUE);
    btnStack.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        new StackView(StructuresView.this, true).start(StructuresView.this);
        setVisible(true);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnStack.setCursor(Styles.HAND);
      }
    });
    panel.add(btnStack);

    btnQueue = new Label().setText("  Queue  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    btnQueue.setBorder(Styles.BORDER_BLUE);
    btnQueue.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        new QueuesView(StructuresView.this, true).start(StructuresView.this);
        setVisible(true);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnQueue.setCursor(Styles.HAND);
      }
    });
    panel.add(btnQueue);

    btnArrays = new Label().setText("  Arrays  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    btnArrays.setBorder(Styles.BORDER_BLUE);
    btnArrays.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        new ArraysView(StructuresView.this, true).start(StructuresView.this);
        setVisible(true);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnArrays.setCursor(Styles.HAND);
      }
    });
    panel.add(btnArrays);

    btnExit = new Label().setText("  Return  ")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    btnExit.setBorder(Styles.BORDER_RED);
    btnExit.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Effects.fadeOut(StructuresView.this);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnExit.setCursor(Styles.HAND);
      }
    });
    panel.add(btnExit);

    return panel;
  }

  private void createComponents() {

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("estructuras.png")).getImage());

    add(getPanel(), BorderLayout.SOUTH);

    ImageIcon image = new ImageIcon(Resources.getImage("estructuras_datos.jpg"));
    JLabel wallpaper = new JLabel();
    wallpaper.setIcon(image);
    wallpaper.setSize(711, 284);
    add(wallpaper, BorderLayout.CENTER);
  }
}