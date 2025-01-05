package com.bookverse.packapps.apps.stacks;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StacksViewModel {
  private JButton[] stack;
  private JLabel stackTitle;
  private JDialog parent;
}
