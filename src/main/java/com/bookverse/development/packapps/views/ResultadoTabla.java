package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.models.Table;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ResultadoTabla extends JDialog {

    public JTable resultadoTab;
    private Table modelo = new Table();
    private JScrollPane scroll;
    private TableRowSorter<TableModel> ordenar;

    // Constructor que recibe la ventana padre y el valor modal
    public ResultadoTabla(JDialog parent, boolean modal, String[] columnas) {

        super(parent, modal);
        Componentes(columnas);
    }

    public void Componentes(String[] columnas) {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /* TABLA */
        for (int i = 0; i < columnas.length; i++) {
            modelo.addColumn(columnas[i]);
        }

        resultadoTab = new JTable(modelo);
        resultadoTab.getTableHeader().setReorderingAllowed(false);
        scroll = new JScrollPane(resultadoTab);
        getContentPane().add(scroll, BorderLayout.CENTER);

        pack();

        ordenar = new TableRowSorter<TableModel>(modelo);
        resultadoTab.setRowSorter(ordenar);

        for (int i = 0; i < columnas.length; i++) {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            resultadoTab.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void limpiarTabla(DefaultTableModel modelo) {

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
}