package com.bookverse.development.packapps.views;

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
import com.bookverse.development.packapps.services.StackService;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Effects;

public class StackView extends JDialog implements MouseListener, ActionListener {

  private JLabel[] stackActions = new JLabel[8];
  private JLabel title;
  private JLabel message;
  private JButton[] stack = new JButton[50];

  public StackView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
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

  private void getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    String[] images = {
        "push.png",
        "pop.png",
        "peek.png",
        "contar.png",
        "sumar.png",
        "promedio.png",
        "pares.png",
        "vaciar.png"
    };

    panel.setBorder(Resources.getBorder("Select action"));

    IntStream.range(0, stackActions.length).forEach(i -> {
      stackActions[i] = new JLabel();
      stackActions[i].setIcon(new ImageIcon(Resources.getImage(images[i])));
      stackActions[i].addMouseListener(this);
      panel.add(stackActions[i]);
    });

    panel.setBounds(0, 480, 480, 100);
    add(panel);
  }

  private void createComponents() {

    setLayout(null);

    int c = 0;

    for (int j = 0; j < stack.length; j++) {
      stack[j] = Resources.getButton("", null, this, this);
      stack[j].setBounds(StackService.x, StackService.y, 80, 40);
      stack[j].setForeground(Styles.MAIN_COLOR);
      stack[j].setVisible(false);
      StackService.y -= 40;

      c++;

      if (c == 10) {
        StackService.x += 100;
        StackService.y = 400;

        c = 0;
      }
    }

    getPanel();

    title = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.BIG);
    title.setBounds(470, 20, 400, 200);

    message = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.BIG);
    message.setBounds(620, 480, 200, 85);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == stackActions[0]) {
      StackService.clickOnPush(stack, title);
    } else if (e.getSource() == stackActions[1]) {
      StackService.clickOnPop(stack, title, this);
    } else if (e.getSource() == stackActions[2]) {
      StackService.clickOnPeek(stack, title);
    } else if (e.getSource() == stackActions[3]) {
      StackService.clickOnCount(title);
    } else if (e.getSource() == stackActions[4]) {
      StackService.clickOnAdd(stack, title);
    } else if (e.getSource() == stackActions[5]) {
      StackService.average(stack, title);
    } else if (e.getSource() == stackActions[6]) {
      StackService.clickOnPairs(stack, title);
    } else if (e.getSource() == stackActions[7]) {
      StackService.clickOnClean(stack, title, this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (e.getSource() == stackActions[0]) {
      stackActions[0].setCursor(Styles.HAND);
      message.setText("<html><strong>Push( )</strong></html>");
    } else if (e.getSource() == stackActions[1]) {
      stackActions[1].setCursor(Styles.HAND);
      message.setText("<html><strong>Pop( )</strong></html>");
    } else if (e.getSource() == stackActions[2]) {
      stackActions[2].setCursor(Styles.HAND);
      message.setText("<html><strong>Peek( )</strong></html>");
    } else if (e.getSource() == stackActions[3]) {
      stackActions[3].setCursor(Styles.HAND);
      message.setText("<html><strong>Count</strong></html>");
    } else if (e.getSource() == stackActions[4]) {
      stackActions[4].setCursor(Styles.HAND);
      message.setText("<html><strong>Sum</strong></html>");
    } else if (e.getSource() == stackActions[5]) {
      stackActions[5].setCursor(Styles.HAND);
      message.setText("<html><strong>Average</strong></html>");
    } else if (e.getSource() == stackActions[6]) {
      stackActions[6].setCursor(Styles.HAND);
      message.setText("<html><strong>Pairs numbers</strong></html>");
    } else if (e.getSource() == stackActions[7]) {
      stackActions[7].setCursor(Styles.HAND);
      message.setText("<html><strong>Clean</strong></html>");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == stackActions[0]) {
      message.setText("");
    } else if (e.getSource() == stackActions[1]) {
      message.setText("");
    } else if (e.getSource() == stackActions[2]) {
      message.setText("");
    } else if (e.getSource() == stackActions[3]) {
      message.setText("");
    } else if (e.getSource() == stackActions[4]) {
      message.setText("");
    } else if (e.getSource() == stackActions[5]) {
      message.setText("");
    } else if (e.getSource() == stackActions[6]) {
      message.setText("");
    } else if (e.getSource() == stackActions[7]) {
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

    for (int j = 0; j < StackService.i; j++) {

      if (e.getSource() == stack[j]) {

        if (stack[j].getBackground() == Styles.TEXT_COLOR) {
          stack[j].setBackground(getBackground());
          StackService.con--;
          StackService.sum -= Double.parseDouble(stack[j].getText());
        } else {
          stack[j].setBackground(Styles.TEXT_COLOR);
          StackService.con++;
          StackService.sum += Double.parseDouble(stack[j].getText());
        }

        if (StackService.con > 0) {
          title.setText(
              "<html><strong>Data selected → " + StackService.con + "<br>" + "Sum up → " + (int) StackService.sum
                  + "<br>" + "Average → " + String.format("%.2f", StackService.sum / StackService.con)
                  + "</strong></html>");
        } else {
          title.setText("<html><strong>No data selected</strong></html>");
        }
      }
    }
  }
}