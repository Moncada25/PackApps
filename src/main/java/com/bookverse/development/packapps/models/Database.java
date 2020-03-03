package com.bookverse.development.packapps.models;

import com.bookverse.development.packapps.core.Core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Database {

    private Connection conexion = null;
    private ResultSet rs;
    private PreparedStatement stm;
    private ResultSetMetaData rsm;
    private DefaultTableModel modelo;
    private DataSource dataSource;
    private DataSourceService data = new DataSourceService();

    /* CREATE */
    public boolean insertData(String[] datos) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            if (datos[0].equals("adivinar")) {
                stm = conexion
                        .prepareStatement("insert into adivinar (Nickname,Limite,Intentos,Date) values (?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setInt(2, Integer.parseInt(datos[2]));
                stm.setString(3, datos[3]);
                stm.setString(4, datos[4]);
                stm.execute();
            } else if (datos[0].equals("ahorcado")) {
                stm = conexion.prepareStatement(
                        "insert into ahorcado (Nickname,Mistakes,State, Category, Date) values (?,?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setInt(2, Integer.parseInt(datos[2]));
                stm.setString(3, datos[3]);
                stm.setString(4, datos[4]);
                stm.setString(5, datos[5]);
                stm.execute();
            } else if (datos[0].equals("dados")) {
                stm = conexion.prepareStatement("insert into dados (Nickname,Winner,Round,Date) values (?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setString(2, datos[2]);
                stm.setInt(3, Integer.parseInt(datos[3]));
                stm.setString(4, datos[4]);
                stm.execute();
            } else if (datos[0].equals("rompecabezas")) {
                stm = conexion.prepareStatement(
                        "insert into rompecabezas (Nickname,State,Tiempo,Jugadas,Date) values (?,?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setString(2, datos[2]);
                stm.setString(3, datos[3]);
                stm.setInt(4, Integer.parseInt(datos[4]));
                stm.setString(5, datos[5]);
                stm.execute();
            } else if (datos[0].equals("notas")) {
                stm = conexion.prepareStatement(
                        "insert into notas (Nickname,Scale,Percent,Note,State,Date) values (?,?,?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setString(2, datos[2]);
                stm.setInt(3, Integer.parseInt(datos[3]));
                stm.setString(4, datos[4]);
                stm.setString(5, datos[5]);
                stm.setString(6, datos[6]);
                stm.execute();
            } else if (datos[0].equals("feedback")) {
                stm = conexion.prepareStatement("insert into feedback (Username,Mensaje,Date) values (?,?,?)");
                stm.setString(1, datos[1]);
                stm.setString(2, datos[2]);
                stm.setString(3, datos[3]);
                stm.execute();
            } else if (datos[0].equals("inventario")) {
                stm = conexion
                        .prepareStatement("insert into inventario (ID, Estado, Precio, Cantidad) values (?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setString(2, datos[2]);
                stm.setDouble(3, Double.parseDouble(datos[3]));
                stm.setInt(4, Integer.parseInt(datos[4]));
                stm.execute();
            } else if (datos[0].equals("registros")) {
                stm = conexion.prepareStatement(
                        "insert into registros (Usuario, Productos_Vendidos,Total_Ventas,Productos_Comprados,Total_Compras, Total_Prestamos) values (?,?,?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setInt(2, Integer.parseInt(datos[2]));
                stm.setDouble(3, Double.parseDouble(datos[3]));
                stm.setInt(4, Integer.parseInt(datos[4]));
                stm.setDouble(5, Double.parseDouble(datos[5]));
                stm.setDouble(6, Double.parseDouble(datos[6]));
                stm.execute();
            } else if (datos[0].equals("prestamos")) {
                stm = conexion.prepareStatement(
                        "insert into préstamos (Usuario, Nombre,Documento,Referencia,Teléfono,Plazo,Valor) values (?,?,?,?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setString(2, datos[2]);
                stm.setString(3, datos[3]);
                stm.setString(4, datos[4]);
                stm.setString(5, datos[5]);
                stm.setString(6, datos[6]);
                stm.setDouble(7, Double.parseDouble(datos[7]));
                stm.execute();
            } else if (datos[0].equals("usuarios")) {

                stm = conexion.prepareStatement("insert into usuarios (User_Name, Password, Status) values (?,?,?)");
                stm.setString(1, datos[1]);
                stm.setString(2, datos[2]);
                stm.setString(3, datos[3]);
                stm.execute();
            } else if (datos[0].equals("compras")) {

                stm = conexion.prepareStatement(
                        "insert into compras (IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total) values (?,?,?,?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setString(2, datos[2]);
                stm.setString(3, datos[3]);
                stm.setString(4, datos[4]);
                stm.setString(5, datos[5]);
                stm.setInt(6, Integer.parseInt(datos[6]));
                stm.setDouble(7, Double.parseDouble(datos[7]));
                stm.execute();
            } else if (datos[0].equals("ventas")) {
                // hacer que IDUSUARIO sea USUARIO
                stm = conexion.prepareStatement(
                        "insert into ventas (IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total) values (?,?,?,?,?,?,?)");
                stm.setString(1, datos[1]);
                stm.setString(2, datos[2]);
                stm.setString(3, datos[3]);
                stm.setString(4, datos[4]);
                stm.setString(5, datos[5]);
                stm.setInt(6, Integer.parseInt(datos[6]));
                stm.setDouble(7, Double.parseDouble(datos[7]));
                stm.execute();
            }

            return true;

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            return false;
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    /* READ */
    public boolean importarTabla(JTable tabla, String query, boolean isMain) {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion.prepareStatement(query);
            rs = stm.executeQuery();
            rsm = rs.getMetaData();
            ArrayList<Object[]> datos = new ArrayList<>();
            while (rs.next()) {
                Object[] filas = new Object[rsm.getColumnCount()];
                for (int i = 0; i < filas.length; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                datos.add(filas);
            }

            if (datos.isEmpty() && !isMain) {

                V.emptyTable();

                return false;
            } else {
                modelo = (DefaultTableModel) tabla.getModel();
                for (int i = 0; i < datos.size(); i++) {
                    modelo.addRow(datos.get(i));
                }
                return true;
            }

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
        return false;
    }

    /* UPDATE */
    public void updateData(String nickname, String ID, String table) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion
                    .prepareStatement("UPDATE " + table + " SET Nickname='" + nickname + "' WHERE ID='" + ID + "'");
            stm.executeUpdate();
        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    /* DELETE */
    public void deleteData(String[] rows, String table) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            for (int i = 0; i < rows.length; i++) {
                stm = conexion.prepareStatement("delete from " + table + " where ID ='" + rows[i] + "'");
                stm.executeUpdate();
            }

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    /* DATA COMPRAVENTA */
    public void registrarVentas(String usuario, int producVend, Double totalVentas) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = (PreparedStatement) conexion.prepareStatement(
                    "UPDATE registros SET Productos_Vendidos='" + (producVend + getProdVen()) + "', Total_Ventas='"
                            + (totalVentas + getTotalVen()) + "' WHERE Usuario='" + usuario + "'");
            stm.executeUpdate();

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    public void registrarCompras(String usuario, int producComp, Double totalCompr) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = (PreparedStatement) conexion.prepareStatement(
                    "UPDATE registros SET Productos_Comprados='" + (producComp + getProdCom()) + "', Total_Compras='"
                            + (totalCompr + getTotalCom()) + "' WHERE Usuario='" + usuario + "'");
            stm.executeUpdate();

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    public void registrarPrestamos(String usuario, Double prestamo) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = (PreparedStatement) conexion.prepareStatement("UPDATE registros SET Total_Prestamos='"
                    + (prestamo + getTotalPres()) + "' WHERE Usuario='" + usuario + "'");
            stm.executeUpdate();

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    public void acumularInventario(int cant, String ref) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = (PreparedStatement) conexion.prepareStatement(
                    "UPDATE inventario SET Cantidad='" + (cant + getDisp()) + "' WHERE ID='" + ref + "'");
            stm.executeUpdate();

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    public void importarDatos(String refer) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion.prepareStatement("select * from inventario where ID ='" + refer + "'");
            rs = stm.executeQuery();

            while (rs.next()) {
                setRef(rs.getString("ID"));
                setEst(rs.getString("Estado"));
                setPrecio(rs.getDouble("Precio"));
                setDisp(rs.getInt("Cantidad"));
            }

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    public void login(String status, String user) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = (PreparedStatement) conexion
                    .prepareStatement("UPDATE usuarios SET Status='" + status + "' WHERE User_Name='" + user + "'");
            stm.executeUpdate();

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    public boolean buscarRef(String refer) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion.prepareStatement("select * from inventario where ID ='" + refer + "'");
            rs = stm.executeQuery();

            while (rs.next()) {
                setRef(rs.getString("ID"));
                setEst(rs.getString("Estado"));
                setPrecio(rs.getDouble("Precio"));
                setDisp(rs.getInt("Cantidad"));
            }

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }

        return refer.equals(getRef());
    }

    public int getIDUser(String user) throws SQLException {

        int ID = 0;

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion.prepareStatement("select ID from usuarios where User_Name ='" + user + "'");
            rs = stm.executeQuery();

            while (rs.next()) {
                ID = rs.getInt("ID");
            }

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {
            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }

        return ID;
    }

    public boolean buscarUser(String user) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion.prepareStatement("select * from registros where Usuario ='" + user + "'");
            rs = stm.executeQuery();

            while (rs.next()) {
                setUsuario(rs.getString("Usuario"));
                setProdVen(rs.getInt("Productos_Vendidos"));
                setTotalVen(rs.getDouble("Total_Ventas"));
                setProdCom(rs.getInt("Productos_Comprados"));
                setTotalCom(rs.getDouble("Total_Compras"));
                setTotalPres(rs.getDouble("Total_Prestamos"));
            }

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }

        return user.equals(getUsuario());
    }

    public String returnUser(String stat) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion.prepareStatement("select * from usuarios where Status ='" + stat + "'");
            rs = stm.executeQuery();

            while (rs.next()) {
                setUserLogin(rs.getString("User_Name"));
                setPass(rs.getString("Password"));
                setStatus(rs.getString("Status"));
            }

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }

        return getUserLogin();
    }

    public boolean userExist(String user) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion.prepareStatement("select * from usuarios where User_Name ='" + user + "'");
            rs = stm.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            // e.printStackTrace();
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }

        return false;
    }

    public boolean buscarEmpleado(String user, String secretpassword) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion.prepareStatement(
                    "SELECT * FROM usuarios WHERE User_Name='" + user + "' AND password='" + secretpassword + "'");
            rs = stm.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }

        return false;
    }

    public void updatePassword(String User, String newPassword) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion.prepareStatement(
                    "UPDATE usuarios SET Password='" + newPassword + "' WHERE User_Name='" + User + "'");
            stm.executeUpdate();

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    public void updateCantidad(int cantidad, String ID, String table) throws SQLException {

        try {
            dataSource = data.getDataSource();
            conexion = dataSource.getConnection();

            stm = conexion
                    .prepareStatement("UPDATE " + table + " SET Cantidad='" + cantidad + "' WHERE ID='" + ID + "'");
            stm.executeUpdate();

        } catch (SQLException e) {
            V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
        } finally {

            try {
                if (null != conexion) {
                    conexion.close();
                }
            } catch (SQLException e) {
                V.mostrarMensaje("Datebase not found", "Lo sentimos, ha ocurrido un error, inténtalo más tarde.");
            }
        }
    }

    private String ref, est, doc, tel, fecha, usuario, userLogin, pass, status;
    private Double precio, totalVen, totalCom, totalPres;
    private int disp, prodVen, prodCom;
    private Core V = new Core();

    public Double getTotalPres() {
        return totalPres;
    }

    public void setTotalPres(Double totalPres) {
        this.totalPres = totalPres;
    }

    public String returnPass(String user) {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoc() {
        return doc;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getDisp() {
        return disp;
    }

    public void setDisp(int disp) {
        this.disp = disp;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Double getTotalVen() {
        return totalVen;
    }

    public void setTotalVen(Double totalVen) {
        this.totalVen = totalVen;
    }

    public Double getTotalCom() {
        return totalCom;
    }

    public void setTotalCom(Double totalCom) {
        this.totalCom = totalCom;
    }

    public int getProdVen() {
        return prodVen;
    }

    public void setProdVen(int prodVen) {
        this.prodVen = prodVen;
    }

    public int getProdCom() {
        return prodCom;
    }

    public void setProdCom(int prodCom) {
        this.prodCom = prodCom;
    }
}