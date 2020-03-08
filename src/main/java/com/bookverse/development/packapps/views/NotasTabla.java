package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Core;
import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.models.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class NotasTabla extends JDialog implements ActionListener, MouseListener {

    private JScrollPane scroll;
    private JLabel titulo, men;
    public JTable notasTab;
    private Table modelo = new Table();
    private TableRowSorter<TableModel> ordenar;
    private JMenuBar barra;
    private JMenu crud;
    private JMenuItem create, read, delete, update;
    private String[] columnas = {"ID", "NAME", "SCALE", "% TOTAL", "TOTAL", "STATE", "DATE"};
    private Resources img = new Resources();
    private JLabel[] tablas = new JLabel[5];

    public NotasTabla() {
        Componentes();
    }

    public NotasTabla(JFrame parent, boolean modal) {

        super(parent, modal);

        Componentes();
    }

    public JPanel getPanel() {

        JPanel panel = new JPanel(new GridLayout());

        JPanel fila = new JPanel(new FlowLayout());

        String[] imgs = {"adivinar.png", "ahorcado.png", "dado.png", "notas.png", "rompecabezas.png"};

        panel.setBorder(img.core.bordeAzul("Select Table"));

        titulo = new JLabel();
        titulo.setFont(img.core.BIG);
        titulo.setForeground(img.core.ROJO);
        titulo.addMouseListener(this);

        men = new JLabel();
        men.setFont(img.core.BIG);
        men.setForeground(img.core.AZUL);
        men.addMouseListener(this);

        /* ICONOS */
        for (int i = 0; i < tablas.length; i++) {

            tablas[i] = new JLabel();
            tablas[i].setIcon(new ImageIcon(img.getImage(imgs[i])));
            tablas[i].addMouseListener(this);
            fila.add(tablas[i]);
        }

        panel.add(titulo, BorderLayout.EAST);
        panel.add(fila, BorderLayout.CENTER);
        panel.add(men, BorderLayout.WEST);

        return panel;
    }

    // Crea los componentes de la tabla
    private void Componentes() {

        setIconImage(new ImageIcon(img.getImage("notas.png")).getImage());
        add(getPanel(), BorderLayout.SOUTH);

        /* TABLA */
        for (int i = 0; i < columnas.length; i++) {
            modelo.addColumn(columnas[i]);
        }

        notasTab = new JTable(modelo);
        notasTab.getTableHeader().setReorderingAllowed(false);
        scroll = new JScrollPane(notasTab);
        add(scroll, BorderLayout.CENTER);

        int[] anchos = {20, 140, 20, 30, 30, 40, 100};
        for (int i = 0; i < notasTab.getColumnCount(); i++) {
            notasTab.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        /* BARRA */
        barra = new JMenuBar();

        crud = img.getMenu("CRUD", "mysql");
        create = img.getMenuItem("Create", "create", this);
        read = img.getMenuItem("Read", "read", this);
        update = img.getMenuItem("Update", "update", this);
        delete = img.getMenuItem("Delete", "delete", this);

        crud.add(create);
        crud.addSeparator();
        crud.add(read);
        crud.addSeparator();
        crud.add(update);
        crud.addSeparator();
        crud.add(delete);

        barra.add(crud);
        add(barra, BorderLayout.NORTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();

        ordenar = new TableRowSorter<TableModel>(modelo);
        notasTab.setRowSorter(ordenar);

        for (int i = 0; i < columnas.length; i++) {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            notasTab.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        Dimension m = getMaximumSize();
        boolean resize = d.width > m.width || d.height > m.height;
        d.width = Math.min(m.width, d.width);
        d.height = Math.min(m.height, d.height);
        if (resize) {
            Point p = getLocation();
            setVisible(false);
            setSize(d);
            setLocation(p);
            setVisible(true);
        }
        super.paint(g);
    }

    public void limpiarTabla() {

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    private void btnConsultAP() {

        if (notasTab.getRowCount() != 0) {

            Object opcion = null;

            opcion = JOptionPane.showInputDialog(null, "<html>" + img.core.styleJOption() + "<strong><em>¿Qué busca?</em></strong></html>",
                    "Buscar registros", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"ID", "Nickname"}, "ID");

            if (opcion != null) {

                try {

                    if (opcion.toString().equals("ID")) {
                        String sql = "select * from notas where ID ='" + Core.enterNumber("Ingresa un ID", 6) + "'";
                        resultadoBusqueda(90, sql);
                        setVisible(true);
                    } else if (opcion.toString().equals("Nickname")) {
                        String sql = "select * from notas where Nickname ='"
                                + Core.enterNickname("Ingrese un Nickname", 20) + "'";
                        resultadoBusqueda(250, sql);
                        setVisible(true);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error when consulting", "Consult", JOptionPane.PLAIN_MESSAGE);
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Empty table", "Consult", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void btnUpdateAP() {

        if (notasTab.getRowCount() != 0) {

            int filaseleccionada = notasTab.getSelectedRow();

            if (filaseleccionada == -1) {

                JOptionPane.showMessageDialog(null, "You're not selected record", "Update", JOptionPane.PLAIN_MESSAGE);

            } else {

                if (img.core.loginDBA()) {

                    img.database.updateData(Core.enterNickname("Ingrese un Nickname", 20),
                            String.valueOf(modelo.getValueAt(filaseleccionada, 0)), "notas");

                    dispose();
                    new Index().NotasTableAP();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Empty Table", "Update", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void btnDeteleAP() {

        if (notasTab.getRowCount() != 0) {

            if (notasTab.getSelectedRow() == -1) {

                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún registro", "Delete",
                        JOptionPane.PLAIN_MESSAGE);

            } else {

                int[] rows = notasTab.getSelectedRows();
                String[] IDs = new String[rows.length];

                for (int i = 0; i < rows.length; i++) {
                    IDs[i] = String.valueOf(modelo.getValueAt(rows[i], 0));
                }

                if (img.core.loginDBA()) {
                    img.database.deleteData(IDs, "notas");

                    dispose();
                    new Index().NotasTableAP();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Tabla vacía", "Delete", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void resultadoBusqueda(int alto, String sql) {

        TableResult resultado = new TableResult(this, true, columnas);

        resultado.cleanTable((DefaultTableModel) resultado.tabResult.getModel());

        try {

            if (img.database.readTable(resultado.tabResult, sql, false)) {
                resultado.setBounds(0, 0, 780, alto);
                resultado.setResizable(false);
                resultado.setLocationRelativeTo(null);
                resultado.setTitle("Resultado de la consulta");
                setVisible(false);
                resultado.setVisible(true);
            }
            // JOptionPane.showMessageDialog(null, "Importado exitosamente");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == delete) {
            btnDeteleAP();
        } else if (e.getSource() == update) {
            btnUpdateAP();
        } else if (e.getSource() == read) {
            btnConsultAP();
        } else if (e.getSource() == create) {
            setVisible(false);
            new Index().NotasAP();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == tablas[0]) {
            setVisible(false);
            new Index().guessNumberTableAP();
        } else if (e.getSource() == tablas[1]) {
            setVisible(false);
            new Index().hangmanTableAP();
        } else if (e.getSource() == tablas[2]) {
            setVisible(false);
            new Index().dicesTableAP();
        } else if (e.getSource() == tablas[3]) {
            JOptionPane.showMessageDialog(null, "<html><em><strong>You're here!</strong><br></em></html>",
                    "Usuario, cámate po favo", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == tablas[4]) {
            setVisible(false);
            new Index().RompecabezasTableAP();
        }
    }

    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == tablas[0]) {
            tablas[0].setCursor(img.core.MIRA);
            titulo.setText("    Adivinar Número");
        } else if (e.getSource() == tablas[1]) {
            tablas[1].setCursor(img.core.CARGAR);
            titulo.setText("    Ahorcadito");
        } else if (e.getSource() == tablas[2]) {
            tablas[2].setCursor(img.core.REDI);
            titulo.setText("    Juego de Dados");
        } else if (e.getSource() == tablas[3]) {
            tablas[3].setCursor(img.core.TEXT);
            titulo.setText("    Notas");
            men.setText("       You're here");
        } else if (e.getSource() == tablas[4]) {
            tablas[4].setCursor(img.core.MANO);
            titulo.setText("    Rompecabezas");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getSource() == tablas[0]) {
            titulo.setText("");
        } else if (e.getSource() == tablas[1]) {
            titulo.setText("");
        } else if (e.getSource() == tablas[2]) {
            titulo.setText("");
        } else if (e.getSource() == tablas[3]) {
            titulo.setText("");
            men.setText("");
        } else if (e.getSource() == tablas[4]) {
            titulo.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}