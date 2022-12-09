package com.bookverse.development.packapps.apps.utils.ui;

import javax.swing.table.DefaultTableModel;

public class Table extends DefaultTableModel {

  public boolean isCellEditable(int row, int column) {
    return false;
  }
}