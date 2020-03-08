package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Core;
import com.bookverse.development.packapps.core.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Matrices extends JDialog implements ActionListener {

    private JButton btndeterminante, btnaction, btnautofill, btnempty, btntranspuesta, btndiagonales, btnmultiplicar;
    private JLabel welcome, filas, columnas;
    private JTextField txtFilas, txtColumnas;
    private Resources h = new Resources();
    private JButton[][] matriz;
    private boolean isWork = false;

    public Matrices(JDialog parent, boolean modal) {

        super(parent, modal);
        Componentes();
    }

    public Matrices() {
        Componentes();
    }

    public void Componentes() {

        setLayout(null);

        matriz = new JButton[9][9];
        int x = 250;
        int y = 80;

        for (int f = 0; f < matriz.length; f++) {
            for (int c = 0; c < matriz.length; c++) {
                matriz[f][c] = h.getButton("", null, this, this);
                matriz[f][c].setBounds(x, y, 45, 45);
                matriz[f][c].setVisible(false);
                x = x + 45;
            }

            x = 250;
            y = y + 45;
        }

        welcome = h.getLabel("<html><em><strong>Operaciones Con Matrices</strong></em></html>", h.core.ROJO, this, h.core.BIG);
        welcome.setBounds(280, 10, 500, 40);

        btnaction = h.getButton("Show", h.core.AZUL, this, this);
        btnaction.setBounds(20, 190, 70, 25);

        btnautofill = h.getButton("Autofill", h.core.AZUL, this, this);
        btnautofill.setBounds(120, 100, 90, 25);
        btnautofill.setEnabled(false);

        btnempty = h.getButton("Empty", h.core.ROJO, this, this);
        btnempty.setBounds(120, 150, 90, 25);
        btnempty.setEnabled(false);

        btntranspuesta = h.getButton("Transponer", h.core.AZUL, this, this);
        btntranspuesta.setBounds(720, 100, 120, 25);
        btntranspuesta.setEnabled(false);

        btnmultiplicar = h.getButton("Multiplicar", h.core.AZUL, this, this);
        btnmultiplicar.setBounds(720, 200, 120, 25);
        btnmultiplicar.setEnabled(false);

        btndeterminante = h.getButton("|A|", h.core.AZUL, this, this);
        btndeterminante.setBounds(720, 250, 120, 25);
        btndeterminante.setEnabled(false);

        btndiagonales = h.getButton("Diagonales", h.core.AZUL, this, this);
        btndiagonales.setBounds(720, 150, 120, 25);
        btndiagonales.setEnabled(false);

        filas = h.getLabel("<html><em>Filas</em></html>", h.core.AZUL, this, h.core.SMALL);
        filas.setBounds(40, 80, 100, 25);

        txtFilas = new JTextField();
        txtFilas.setBounds(20, 100, 70, 25);
        txtFilas.setHorizontalAlignment(JTextField.CENTER);
        add(txtFilas);

        txtFilas.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                txtNumKeyTyped(e);
            }

            public void txtNumKeyTyped(KeyEvent e) {
                h.core.solonumeros(e.getKeyChar(), e, txtFilas.getText(), 1);
            }
        });

        columnas = h.getLabel("<html><em>Columnas</em></html>", h.core.AZUL, this, h.core.SMALL);
        columnas.setBounds(25, 130, 100, 25);

        txtColumnas = new JTextField();
        txtColumnas.setBounds(20, 150, 70, 25);
        txtColumnas.setHorizontalAlignment(JTextField.CENTER);
        add(txtColumnas);

        txtColumnas.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                txtNumKeyTyped(e);
            }

            public void keyPressed(KeyEvent e) {
                txtNumKeyPressed(e);
            }

            public void txtNumKeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    generarMatrizAP();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void txtNumKeyTyped(KeyEvent e) {
                h.core.solonumeros(e.getKeyChar(), e, txtColumnas.getText(), 1);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnaction) {
            generarMatrizAP();
        } else if (e.getSource() == btnautofill) {
            autoFillAP(matriz, Integer.parseInt(txtFilas.getText()), Integer.parseInt(txtColumnas.getText()));
        } else if (e.getSource() == btnempty) {
            emptyAP();
        } else if (e.getSource() == btntranspuesta) {
            transpuestaAP();
        } else if (e.getSource() == btndiagonales) {
            diagonalesAP();
        } else if (e.getSource() == btnmultiplicar) {
            multiplicarAP();
        } else if (e.getSource() == btndeterminante) {
            determinanteAP();
        }

        if (btnaction.getText().equals("New")) {
            manualFill(e, matriz, Integer.parseInt(txtFilas.getText()), Integer.parseInt(txtColumnas.getText()));
        }
    }

    private void determinanteAP() {

        if (entradasFull(matriz, Integer.parseInt(txtFilas.getText()), Integer.parseInt(txtColumnas.getText()))) {

            int f = Integer.parseInt(txtFilas.getText());
            int c = Integer.parseInt(txtColumnas.getText());

            if (esCuadrada(f, c)) {

                JOptionPane.showMessageDialog(null,
                        "<html>" + h.core.styleJOption() + "<strong><center>|A| = "
                                + Determinante.getDeterminante(matriz, f, c) + "</center></strong></html>",
                        "Determinante de la matriz", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void multiplicarAP() {

        if (entradasFull(matriz, Integer.parseInt(txtFilas.getText()), Integer.parseInt(txtColumnas.getText()))) {

            for (int i = 0; i < Integer.parseInt(txtFilas.getText()); i++) {
                for (int j = 0; j < Integer.parseInt(txtColumnas.getText()); j++) {
                    matriz[i][j].setBackground(getBackground());
                }
            }

            Object opcion = null;

            opcion = JOptionPane.showInputDialog(null,
                    "<html>" + h.core.styleJOption()
                            + "<strong><em>¿Con qué desea multiplicar la matriz?</em></strong></html>",
                    "Producto de Matrices", JOptionPane.PLAIN_MESSAGE, null,
                    new Object[]{"Un escalar", "Un vector", "Una matriz"}, "Un escalar");

            if (opcion != null) {

                switch (opcion.toString()) {

                    case "Un escalar":

                        int escalar = Integer.parseInt(
                                Core.enterNumber("Ingrese el escalar por el cual desea multiplicar la matriz", 2));

                        for (int i = 0; i < Integer.parseInt(txtFilas.getText()); i++) {
                            for (int j = 0; j < Integer.parseInt(txtColumnas.getText()); j++) {

                                if (matriz[i][j].getText().length() == 1) {
                                    matriz[i][j]
                                            .setText(String.valueOf(Integer.parseInt(matriz[i][j].getText()) * escalar));
                                    matriz[i][j].setFont(h.core.MEDIUM);
                                } else if (matriz[i][j].getText().length() == 2) {
                                    matriz[i][j]
                                            .setText(String.valueOf(Integer.parseInt(matriz[i][j].getText()) * escalar));
                                    matriz[i][j].setFont(h.core.SMALL);
                                }
                            }
                        }

                        break;

                    case "Un vector":

                        int fv = Integer.parseInt(txtFilas.getText());
                        int cv = Integer.parseInt(txtColumnas.getText());

                        double[][] matrizO = new double[fv][cv];
                        double[][] vectorB = getMatrizB(cv, 1);

                        for (int i = 0; i < fv; i++) {
                            for (int j = 0; j < cv; j++) {
                                matrizO[i][j] = Double.parseDouble(matriz[i][j].getText());
                            }
                        }

                        showMatrizC(matrizxmatriz(matrizO, vectorB), fv, 1);

                        break;

                    case "Una matriz":

                        int columnasB = Integer
                                .parseInt(h.core.enterNumber("Ingrese el número de columnas que tendrá la matriz B", 1));

                        int f = Integer.parseInt(txtFilas.getText());
                        int c = Integer.parseInt(txtColumnas.getText());

                        double[][] matrizA = new double[f][c];
                        double[][] matrizB = getMatrizB(c, columnasB);

                        for (int i = 0; i < f; i++) {
                            for (int j = 0; j < c; j++) {
                                matrizA[i][j] = Double.parseDouble(matriz[i][j].getText());
                            }
                        }

                        showMatrizC(matrizxmatriz(matrizA, matrizB), f, columnasB);

                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.", "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    private void showMatrizC(int[][] matrizC, int filas, int columnas) {

        JDialog result = new JDialog(this, true);
        result.setLayout(null);

        JButton[][] matrizResult = new JButton[filas][columnas];

        int x = 30;
        int y = 30;

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                matrizResult[f][c] = new JButton();
                matrizResult[f][c].setBounds(x, y, 45, 45);
                matrizResult[f][c].setText(String.valueOf(matrizC[f][c]));
                matrizResult[f][c].setFont(h.core.SMALL);
                result.add(matrizResult[f][c]);
                x = x + 45;
            }

            x = 30;
            y = y + 45;
        }

        result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        result.setSize(480, 480);
        result.setTitle("Resultado de multiplicar A x B");
        result.setResizable(false);
        result.setLocationRelativeTo(null);
        result.setVisible(true);
    }

    private void diagonalesAP() {

        if (entradasFull(matriz, Integer.parseInt(txtFilas.getText()), Integer.parseInt(txtColumnas.getText()))) {

            int f = Integer.parseInt(txtFilas.getText());
            int c = Integer.parseInt(txtColumnas.getText());

            if (esCuadrada(f, c)) {

                String principal = "";
                String secundaria = "";
                int sumaSuperior = 0;
                int sumaInferior = 0;

                for (int i = 0; i < f; i++) {
                    for (int j = 0; j < c; j++) {

                        if (i == j) {
                            principal += "[" + matriz[i][j].getText() + "]";
                            matriz[i][j].setBackground(h.core.ROJO);
                        }

                        if (i + j == f - 1) {
                            secundaria += "[" + matriz[i][j].getText() + "]";
                            matriz[i][j].setBackground(h.core.AZUL);
                        }
                    }
                }

                for (int i = 0; i < f; i++) {
                    for (int j = i; j < c; j++) {

                        if (i != j) {
                            sumaSuperior += Integer.parseInt(matriz[i][j].getText());
                        }
                    }
                }

                for (int i = 1; i < f; i++) {
                    for (int j = 0; j < c; j++) {
                        if (j < i) {
                            sumaInferior += Integer.parseInt(matriz[i][j].getText());
                        }
                    }
                }

                JOptionPane.showMessageDialog(null,
                        "<html>" + h.core.styleJOption() + "<strong><center>Resultado</center></strong><br>"
                                + "Diagonal principal ? " + principal + "<br>" + "Suma del triángulo superior ? "
                                + sumaSuperior + "<br><br>" + "Diagonal secundaria ? " + secundaria + "<br>"
                                + "Suma del triángulo inferior ? " + sumaInferior + "</html>",
                        "Diagonales de la Matriz", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private boolean esCuadrada(int f, int c) {

        if (f != c) {

            JOptionPane
                    .showMessageDialog(null,
                            "<html>" + h.core.styleJOption() + "<strong><center>Matriz inválida</center></strong><br>"
                                    + "La matriz actual no es cuadrada." + "</html>",
                            "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

            return false;
        }

        return true;
    }

    private void manualFill(ActionEvent e, JButton[][] matriz, int f, int c) {

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {

                if (e.getSource() == matriz[i][j]) {
                    matriz[i][j].setText(Core.enterNumber("Ingrese el valor de la posición [" + (i + 1) + "][" + (j + 1) + "]", 2));
                }
            }
        }
    }

    public int[][] matrizxmatriz(double[][] a, double[][] b) {
        int[][] c = new int[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    // aquí se multiplica la matriz
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return c;
    }

    public double[][] getMatrizB(int filas, int columnas) {

        JDialog result = new JDialog(this, true);
        result.setLayout(null);

        JButton[][] matrizResult = new JButton[filas][columnas];
        double[][] matrizB = new double[filas][columnas];

        int x = 30;
        int y = 30;

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                matrizResult[f][c] = new JButton();
                matrizResult[f][c].setBounds(x, y, 45, 45);

                matrizResult[f][c].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        manualFill(e, matrizResult, filas, columnas);
                    }
                });

                matrizResult[f][c].setFont(h.core.MEDIUM);
                result.add(matrizResult[f][c]);
                x = x + 45;
            }

            x = 30;
            y = y + 45;
        }

        JButton btnsend = new JButton("Finished");
        btnsend.setBackground(h.core.ROJO);
        btnsend.setBounds(250, 455, 120, 25);
        result.add(btnsend);
        btnsend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (entradasFull(matrizResult, filas, columnas)) {

                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            matrizB[i][j] = Double.parseDouble(matrizResult[i][j].getText());
                        }
                    }

                    result.dispose();
                }
            }
        });

        JButton btnauto = new JButton("Autofill");
        btnauto.setBackground(h.core.AZUL);
        btnauto.setBounds(100, 455, 120, 25);
        result.add(btnauto);
        btnauto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autoFillAP(matrizResult, filas, columnas);
            }
        });

        result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        result.setSize(480, 530);
        result.setTitle("Llenar B");
        result.setResizable(false);
        result.setLocationRelativeTo(null);
        result.setVisible(true);

        return matrizB;
    }

    private void transpuestaAP() {

        if (entradasFull(matriz, Integer.parseInt(txtFilas.getText()), Integer.parseInt(txtColumnas.getText()))) {

            for (int i = 0; i < Integer.parseInt(txtFilas.getText()); i++) {
                for (int j = 0; j < Integer.parseInt(txtColumnas.getText()); j++) {
                    matriz[i][j].setBackground(getBackground());
                }
            }

            JDialog result = new JDialog(this, true);
            result.setLayout(null);

            int fi = Integer.parseInt(txtColumnas.getText());
            int ci = Integer.parseInt(txtFilas.getText());

            JButton[][] matrizResult = new JButton[fi][ci];

            int x = 30;
            int y = 30;

            for (int f = 0; f < fi; f++) {
                for (int c = 0; c < ci; c++) {
                    matrizResult[f][c] = new JButton();
                    matrizResult[f][c].setBounds(x, y, 45, 45);
                    matrizResult[f][c].setFont(h.core.MEDIUM);
                    matrizResult[f][c].setText(matriz[c][f].getText());
                    result.add(matrizResult[f][c]);
                    x = x + 45;
                }

                x = 30;
                y = y + 45;
            }

            result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            result.setSize(480, 480);
            result.setTitle("Resultado de Matriz Transpuesta");
            result.setResizable(false);
            result.setLocationRelativeTo(null);
            result.setVisible(true);
        }
    }

    private boolean entradasFull(JButton[][] matriz, int f, int c) {

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (matriz[i][j].getText().equals("")) {

                    JOptionPane.showMessageDialog(null,
                            "<html>" + h.core.styleJOption() + "<strong><center>Entradas indefinidas</center></strong><br>"
                                    + "Alguna de las entradas está indefinida, por favor introduzca un valor."
                                    + "</html>",
                            "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

                    return false;
                }
            }
        }

        return true;
    }

    private void emptyAP() {

        for (int i = 0; i < Integer.parseInt(txtFilas.getText()); i++) {
            for (int j = 0; j < Integer.parseInt(txtColumnas.getText()); j++) {
                matriz[i][j].setText("");
                matriz[i][j].setBackground(getBackground());
                matriz[i][j].setFont(h.core.MEDIUM);
            }
        }
    }

    private void autoFillAP(JButton[][] matriz, int f, int c) {

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                matriz[i][j].setText(String.valueOf(h.core.enteroAleatorio(0, 9)));
                matriz[i][j].setBackground(getBackground());
                matriz[i][j].setFont(h.core.MEDIUM);
            }
        }
    }

    private void generarMatrizAP() {

        if (btnaction.getText().equals("Show")) {

            if (txtFilas.getText().equals("") || txtColumnas.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "<html>" + h.core.styleJOption() + "<strong><center>Campos vacíos</center></strong><br>"
                                + "Alguno de los campos está vacío, por favor introduzca un valor." + "</html>",
                        "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

                isWork = false;

            } else {

                isWork = true;

                deleteAll();

                for (int i = 0; i < Integer.parseInt(txtFilas.getText()); i++) {
                    for (int j = 0; j < Integer.parseInt(txtColumnas.getText()); j++) {
                        matriz[i][j].setVisible(true);
                    }
                }

                btnautofill.setEnabled(true);
                btndeterminante.setEnabled(true);
                btnempty.setEnabled(true);
                btntranspuesta.setEnabled(true);
                btndiagonales.setEnabled(true);
                btnmultiplicar.setEnabled(true);
            }

            if (isWork) {
                txtFilas.setEnabled(false);
                txtColumnas.setEnabled(false);

                btnaction.setText("New");
                btnaction.setBackground(h.core.ROJO);
            }

        } else if (btnaction.getText().equals("New")) {

            if (isWork) {

                emptyAP();

                deleteAll();

                btndeterminante.setEnabled(false);
                btnautofill.setEnabled(false);
                btnempty.setEnabled(false);
                btntranspuesta.setEnabled(false);
                btndiagonales.setEnabled(false);
                btnmultiplicar.setEnabled(false);

                txtFilas.setEnabled(true);
                txtColumnas.setEnabled(true);

                btnaction.setText("Show");
                btnaction.setBackground(h.core.AZUL);
            }
        }
    }

    private void deleteAll() {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j].setVisible(false);
                matriz[i][j].setText("");
                matriz[i][j].setBackground(getBackground());
                matriz[i][j].setFont(h.core.MEDIUM);
            }
        }
    }
}

class Determinante {

    /**
     * Calcula el determinante de la matriz. Para ello coge la primera fila y se va
     * multiplicando cada coeficiente por el determinante de la matriz de orden n-1
     * que resulta de suprimir la fila y columna del coeficiente. Hay que ir
     * alternando los signos. Ver
     * http://www.marcevm.com/determinantes/determinantes_def.php
     *
     * @param matriz
     * @return
     */
    public static double determinante(double[][] matriz) {

        /*
         * Aunque se ponga assert, su ejecución es opcional con -ea. Podemos arrancar la
         * aplicación en modo "detección de fallos" o en modo
         * "haz lo que puedas, pero aguanta". Se detectan los fallos en la ejecución lo
         * más pronto posible. De esta forma, si ejecutamos nuestro código java de forma
         * normal, ese assert es ignorado, por lo que es como si no hubiéramos puesto
         * nada. Nuestra aplicación funcionará igual de mal que antes y no se caerá. Sin
         * embargo, si ejecutamos con la opción -ea (enable asserts posiblemente)
         * entonces sí se hace caso de los assert y saltará una excepción inmediatamente
         * si nuestro parámetro es null. En vez de ir evitando el error de un sitio a
         * otro y ver que la aplicación no funciona, arrancando con -ea veremos
         * inmediatamente cual es el primer sitio en el que falla algo.
         */

        assert matriz != null;
        assert matriz.length > 0;
        assert matriz.length == matriz[0].length;

        double determinante = 0.0;

        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Si la matriz es 1x1, el determinante es el elemento de la matriz
        if ((filas == 1) && (columnas == 1))
            return matriz[0][0];

        int signo = 1;

        for (int columna = 0; columna < columnas; columna++) {
            // Obtiene el adjunto de fila=0, columna=columna, pero sin el signo.
            double[][] submatriz = getSubmatriz(matriz, filas, columnas, columna);
            determinante = determinante + signo * matriz[0][columna] * determinante(submatriz);
            signo *= -1;
        }

        return determinante;
    }

    /**
     * Obtiene la matriz que resulta de eliminar la primera fila y la columna que se
     * pasa como parámetro.
     *
     * @param matriz   Matriz original
     * @param filas    Numero de filas de la matriz original
     * @param columnas Numero de columnas de la matriz original
     * @param columna  Columna que se quiere eliminar, junto con la fila=0
     * @return Una matriz de N-1 x N-1 elementos
     */
    public static double[][] getSubmatriz(double[][] matriz, int filas, int columnas, int columna) {
        double[][] submatriz = new double[filas - 1][columnas - 1];
        int contador = 0;
        for (int j = 0; j < columnas; j++) {
            if (j == columna)
                continue;
            for (int i = 1; i < filas; i++)
                submatriz[i - 1][contador] = matriz[i][j];
            contador++;
        }
        return submatriz;
    }

    public static double getDeterminante(JButton[][] matriz, int f, int c) {

        double[][] matrizAux = new double[f][c];

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                matrizAux[i][j] = Double.parseDouble(matriz[i][j].getText());
            }
        }

        return determinante(matrizAux);
    }
}