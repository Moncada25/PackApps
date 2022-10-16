package com.bookverse.development.packapps.apps.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Effects;

import static com.bookverse.development.packapps.apps.services.StackService.i;
import static com.bookverse.development.packapps.apps.services.StackService.x;
import static com.bookverse.development.packapps.apps.services.StackService.y;
import static com.bookverse.development.packapps.apps.services.StackService.con;
import static com.bookverse.development.packapps.apps.services.StackService.sum;
import static com.bookverse.development.packapps.apps.services.StackService.average;
import static com.bookverse.development.packapps.apps.services.StackService.clickOnAdd;
import static com.bookverse.development.packapps.apps.services.StackService.clickOnClean;
import static com.bookverse.development.packapps.apps.services.StackService.clickOnCount;
import static com.bookverse.development.packapps.apps.services.StackService.clickOnPairs;
import static com.bookverse.development.packapps.apps.services.StackService.clickOnPeek;
import static com.bookverse.development.packapps.apps.services.StackService.clickOnPop;
import static com.bookverse.development.packapps.apps.services.StackService.clickOnPush;
import static com.bookverse.development.packapps.utils.ui.Resources.getBorder;

import static com.bookverse.development.packapps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.utils.constants.Styles.HAND;
import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;

public class StackView extends JDialog implements MouseListener, ActionListener {

  private JLabel[] actions = new JLabel[8];
  private Resources resources = new Resources();
  private JLabel title, message;
  private JButton[] stack = new JButton[50];

  public StackView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    String[] images = {"push.png", "pop.png", "peek.png", "contar.png", "sumar.png", "promedio.png",
        "pares.png",
        "vaciar.png"};

    panel.setBorder(getBorder("Select action"));

    IntStream.range(0, actions.length).forEach(i -> {
      actions[i] = new JLabel();
      actions[i].setIcon(new ImageIcon(resources.getImage(images[i])));
      actions[i].addMouseListener(this);
      panel.add(actions[i]);
    });

    panel.setBounds(50, 480, 480, 100);
    add(panel);
  }

  private void createComponents() {

    setLayout(null);

    int c = 0;

    for (int j = 0; j < stack.length; j++) {
      stack[j] = resources.getButton("", null, this, this);
      stack[j].setBounds(x, y, 80, 40);
      stack[j].setForeground(MAIN_COLOR);
      stack[j].setVisible(false);
      y -= 40;

      c++;

      if (c == 10) {
        x += 100;
        y = 400;

        c = 0;
      }
    }

    getPanel();

    title = resources.getLabel("", MAIN_COLOR, this, BIG);
    title.setBounds(550, 70, 600, 200);

    message = resources.getLabel("", MAIN_COLOR, this, BIG);
    message.setBounds(620, 480, 200, 85);
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 900, 600);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Stack");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void mouseClicked(@NotNull MouseEvent e) {

    if (e.getSource() == actions[0]) {
      clickOnPush(stack, title);
    } else if (e.getSource() == actions[1]) {
      clickOnPop(stack, title, this);
    } else if (e.getSource() == actions[2]) {
      clickOnPeek(stack, title);
    } else if (e.getSource() == actions[3]) {
      clickOnCount(title);
    } else if (e.getSource() == actions[4]) {
      clickOnAdd(stack, title);
    } else if (e.getSource() == actions[5]) {
      average(stack, title);
    } else if (e.getSource() == actions[6]) {
      clickOnPairs(stack, title);
    } else if (e.getSource() == actions[7]) {
      clickOnClean(stack, title, this);
    }
  }

  @Override
  public void mouseEntered(@NotNull MouseEvent e) {
    if (e.getSource() == actions[0]) {
      actions[0].setCursor(HAND);
      message.setText("<html><strong>Push( )</strong></html>");
    } else if (e.getSource() == actions[1]) {
      actions[1].setCursor(HAND);
      message.setText("<html><strong>Pop( )</strong></html>");
    } else if (e.getSource() == actions[2]) {
      actions[2].setCursor(HAND);
      message.setText("<html><strong>Peek( )</strong></html>");
    } else if (e.getSource() == actions[3]) {
      actions[3].setCursor(HAND);
      message.setText("<html><strong>Count</strong></html>");
    } else if (e.getSource() == actions[4]) {
      actions[4].setCursor(HAND);
      message.setText("<html><strong>Sum</strong></html>");
    } else if (e.getSource() == actions[5]) {
      actions[5].setCursor(HAND);
      message.setText("<html><strong>Average</strong></html>");
    } else if (e.getSource() == actions[6]) {
      actions[6].setCursor(HAND);
      message.setText("<html><strong>Pairs numbers</strong></html>");
    } else if (e.getSource() == actions[7]) {
      actions[7].setCursor(HAND);
      message.setText("<html><strong>Clean</strong></html>");
    }
  }

  @Override
  public void mouseExited(@NotNull MouseEvent e) {

    if (e.getSource() == actions[0]) {
      message.setText("");
    } else if (e.getSource() == actions[1]) {
      message.setText("");
    } else if (e.getSource() == actions[2]) {
      message.setText("");
    } else if (e.getSource() == actions[3]) {
      message.setText("");
    } else if (e.getSource() == actions[4]) {
      message.setText("");
    } else if (e.getSource() == actions[5]) {
      message.setText("");
    } else if (e.getSource() == actions[6]) {
      message.setText("");
    } else if (e.getSource() == actions[7]) {
      message.setText("");
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    for (int j = 0; j < i; j++) {

      if (e.getSource() == stack[j]) {

        if (stack[j].getBackground() == TEXT_COLOR) {
          stack[j].setBackground(getBackground());
          con--;
          sum -= Double.parseDouble(stack[j].getText());
        } else {
          stack[j].setBackground(TEXT_COLOR);
          con++;
          sum += Double.parseDouble(stack[j].getText());
        }

        if (con > 0) {
          title.setText(
              "<html><strong>Data selected → " + con + "<br>" + "Sum up → " + (int) sum
                  + "<br>" + "Average → " + String.format("%.2f", sum / con)
                  + "</strong></html>");
        } else {
          title.setText("<html><strong>No data selected</strong></html>");
        }
      }
    }
  }
}