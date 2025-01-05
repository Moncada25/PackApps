package com.bookverse.packapps.apps.stacks;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.apps.structures.StructuresViewModel;
import com.bookverse.packapps.utils.ui.factory.Button;
import com.bookverse.packapps.utils.ui.factory.Label;

public class StackView extends JDialog implements MouseListener {

  private transient StackService service = new StackService();
  private transient StacksViewModel model = null;
  private transient StructuresViewModel structures = new StructuresViewModel();
  
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

  private void createComponents() {

    setLayout(null);

    JButton[] stack = new JButton[50];

    int c = 0;

    for (int j = 0; j < stack.length; j++) {
      stack[j] = new Button().setText("").build();
      stack[j].setBounds(service.getX(), service.getY(), 80, 40);
      stack[j].setForeground(Styles.MAIN_COLOR);
      int finalJ = j;
      stack[j].addActionListener(e -> service.clickOnStack(finalJ, model));
      stack[j].setVisible(false);
      add(stack[j]);
      
      service.setY(service.getY() - 40);

      c++;

      if (c == 10) {
        service.setX(service.getX() + 100);
        service.setY(400);
        c = 0;
      }
    }

    structures.getFooter(this, this, "Stack Actions");

    JLabel stackTitle = new Label().setText("")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    stackTitle.setBounds(470, 20, 400, 200);
    add(stackTitle, BorderLayout.EAST);

    model = new StacksViewModel(stack, stackTitle, this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == structures.getStructureActions()[0]) {
      service.clickOnPush(model);
    } else if (e.getSource() == structures.getStructureActions()[1]) {
      service.clickOnPop(model);
    } else if (e.getSource() == structures.getStructureActions()[2]) {
      service.clickOnPeek(model);
    } else if (e.getSource() == structures.getStructureActions()[3]) {
      service.clickOnCount(model);
    } else if (e.getSource() == structures.getStructureActions()[4]) {
      service.clickOnAdd(model);
    } else if (e.getSource() == structures.getStructureActions()[5]) {
      service.average(model);
    } else if (e.getSource() == structures.getStructureActions()[6]) {
      service.clickOnPairs(model);
    } else if (e.getSource() == structures.getStructureActions()[7]) {
      service.clickOnClean(model);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    structures.handleMouseEvent(e);
  }

  @Override
  public void mouseExited(MouseEvent e) {

    for (JLabel structureAction : structures.getStructureActions()) {
      if (e.getSource() == structureAction) {
        structures.getMessage().setText("Stack Actions");
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}