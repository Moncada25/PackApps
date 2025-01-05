package com.bookverse.packapps.apps.queues;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueuesViewModel {
  private JLabel queueTitle;
  private JButton[] queue;
  private JDialog parent;
}
