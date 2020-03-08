package com.bookverse.development.packapps.models;

import static com.bookverse.development.packapps.utils.TableConstants.DICES;
import static com.bookverse.development.packapps.utils.TableConstants.FEEDBACK;
import static com.bookverse.development.packapps.utils.TableConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.utils.TableConstants.HANGMAN;
import static com.bookverse.development.packapps.utils.TableConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.TableConstants.LOANS;
import static com.bookverse.development.packapps.utils.TableConstants.NOTES;
import static com.bookverse.development.packapps.utils.TableConstants.PURCHASES;
import static com.bookverse.development.packapps.utils.TableConstants.PUZZLE;
import static com.bookverse.development.packapps.utils.TableConstants.RECORDS;
import static com.bookverse.development.packapps.utils.TableConstants.SALES;
import static com.bookverse.development.packapps.utils.TableConstants.USERS;

import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Querys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.IntStream;
import javax.sql.DataSource;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Database {

  private Connection connection = null;
  private ResultSet resultSet;
  private PreparedStatement preparedStatement;
  private DefaultTableModel tableModel;
  private DataSource dataSource;
  private DataSourceService dataSourceService = new DataSourceService();
  private String ref, est, doc, tel, fecha, usuario, userLogin, pass, status;
  private Double precio, totalVen, totalCom, totalPres;
  private int disp, prodVen, prodCom;

  public boolean insertData(String[] data) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      switch (data[0]) {
        case GUESS_NUMBER:
          preparedStatement = connection.prepareStatement(Querys.insertGuessNumber());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setInt(2, Integer.parseInt(data[2]));
          preparedStatement.setString(3, data[3]);
          preparedStatement.setString(4, data[4]);
          preparedStatement.execute();
          break;
        case HANGMAN:
          preparedStatement = connection.prepareStatement(Querys.insertHangman());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setInt(2, Integer.parseInt(data[2]));
          preparedStatement.setString(3, data[3]);
          preparedStatement.setString(4, data[4]);
          preparedStatement.setString(5, data[5]);
          preparedStatement.execute();
          break;
        case DICES:
          preparedStatement = connection.prepareStatement(Querys.insertDices());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setInt(3, Integer.parseInt(data[3]));
          preparedStatement.setString(4, data[4]);
          preparedStatement.execute();
          break;
        case PUZZLE:
          preparedStatement = connection.prepareStatement(Querys.insertPuzzle());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setString(3, data[3]);
          preparedStatement.setInt(4, Integer.parseInt(data[4]));
          preparedStatement.setString(5, data[5]);
          preparedStatement.execute();
          break;
        case NOTES:
          preparedStatement = connection.prepareStatement(Querys.insertNote());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setInt(3, Integer.parseInt(data[3]));
          preparedStatement.setString(4, data[4]);
          preparedStatement.setString(5, data[5]);
          preparedStatement.setString(6, data[6]);
          preparedStatement.execute();
          break;
        case FEEDBACK:
          preparedStatement = connection.prepareStatement(Querys.insertFeedback());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setString(3, data[3]);
          preparedStatement.execute();
          break;
        case INVENTORY:
          preparedStatement = connection.prepareStatement(Querys.insertInventory());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setDouble(3, Double.parseDouble(data[3]));
          preparedStatement.setInt(4, Integer.parseInt(data[4]));
          preparedStatement.execute();
          break;
        case RECORDS:
          preparedStatement = connection.prepareStatement(Querys.insertRecord());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setInt(2, Integer.parseInt(data[2]));
          preparedStatement.setDouble(3, Double.parseDouble(data[3]));
          preparedStatement.setInt(4, Integer.parseInt(data[4]));
          preparedStatement.setDouble(5, Double.parseDouble(data[5]));
          preparedStatement.setDouble(6, Double.parseDouble(data[6]));
          preparedStatement.execute();
          break;
        case LOANS:
          preparedStatement = connection.prepareStatement(Querys.insertLoan());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setString(3, data[3]);
          preparedStatement.setString(4, data[4]);
          preparedStatement.setString(5, data[5]);
          preparedStatement.setString(6, data[6]);
          preparedStatement.setDouble(7, Double.parseDouble(data[7]));
          preparedStatement.execute();
          break;
        case USERS:
          preparedStatement = connection.prepareStatement(Querys.insertUser());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setString(3, data[3]);
          preparedStatement.execute();
          break;
        case PURCHASES:
          preparedStatement = connection.prepareStatement(Querys.insertPurchase());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setString(3, data[3]);
          preparedStatement.setString(4, data[4]);
          preparedStatement.setString(5, data[5]);
          preparedStatement.setInt(6, Integer.parseInt(data[6]));
          preparedStatement.setDouble(7, Double.parseDouble(data[7]));
          preparedStatement.execute();
          break;
        case SALES:
          preparedStatement = connection.prepareStatement(Querys.insertSale());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setString(3, data[3]);
          preparedStatement.setString(4, data[4]);
          preparedStatement.setString(5, data[5]);
          preparedStatement.setInt(6, Integer.parseInt(data[6]));
          preparedStatement.setDouble(7, Double.parseDouble(data[7]));
          preparedStatement.execute();
          break;
        default:
          Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }

      return true;

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      return false;
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  public boolean readTable(JTable tabla, String query, boolean isMain) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(query);
      resultSet = preparedStatement.executeQuery();
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      ArrayList<Object[]> data = new ArrayList<>();
      while (resultSet.next()) {
        Object[] rows = new Object[resultSetMetaData.getColumnCount()];
        for (int i = 0; i < rows.length; i++) {
          rows[i] = resultSet.getObject(i + 1);
        }
        data.add(rows);
      }

      if (data.isEmpty() && !isMain) {
        Alerts.emptyTable();
        return false;
      } else {
        tableModel = (DefaultTableModel) tabla.getModel();
        IntStream.range(0, data.size()).forEach(i -> tableModel.addRow(data.get(i)));
        return true;
      }

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
    return false;
  }

  public void updateData(String nickname, String id, String table) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Querys.updateNickname(nickname, id, table));
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  public void deleteData(String[] rows, String table) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      for (String row : rows) {
        preparedStatement = connection.prepareStatement(Querys.deleteDataByID(row, table));
        preparedStatement.executeUpdate();
      }

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  /* DATA COMPRAVENTA */
  public void registrarVentas(String usuario, int producVend, Double totalVentas)
      throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = (PreparedStatement) connection.prepareStatement(
          "UPDATE registros SET Productos_Vendidos='" + (producVend + getProdVen())
              + "', Total_Ventas='"
              + (totalVentas + getTotalVen()) + "' WHERE Usuario='" + usuario + "'");
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  public void registrarCompras(String usuario, int producComp, Double totalCompr)
      throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = (PreparedStatement) connection.prepareStatement(
          "UPDATE registros SET Productos_Comprados='" + (producComp + getProdCom())
              + "', Total_Compras='"
              + (totalCompr + getTotalCom()) + "' WHERE Usuario='" + usuario + "'");
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  public void registrarPrestamos(String usuario, Double prestamo) throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = (PreparedStatement) connection
          .prepareStatement("UPDATE registros SET Total_Prestamos='"
              + (prestamo + getTotalPres()) + "' WHERE Usuario='" + usuario + "'");
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  public void acumularInventario(int cant, String ref) throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = (PreparedStatement) connection.prepareStatement(
          "UPDATE inventario SET Cantidad='" + (cant + getDisp()) + "' WHERE ID='" + ref + "'");
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  public void importarDatos(String refer) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection
          .prepareStatement("select * from inventario where ID ='" + refer + "'");
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        setRef(resultSet.getString("ID"));
        setEst(resultSet.getString("Estado"));
        setPrecio(resultSet.getDouble("Precio"));
        setDisp(resultSet.getInt("Cantidad"));
      }

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  public void login(String status, String user) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = (PreparedStatement) connection
          .prepareStatement(
              "UPDATE usuarios SET Status='" + status + "' WHERE User_Name='" + user + "'");
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  public boolean buscarRef(String refer) throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection
          .prepareStatement("select * from inventario where ID ='" + refer + "'");
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        setRef(resultSet.getString("ID"));
        setEst(resultSet.getString("Estado"));
        setPrecio(resultSet.getDouble("Precio"));
        setDisp(resultSet.getInt("Cantidad"));
      }

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }

    return refer.equals(getRef());
  }

  public int getIDUser(String user) throws SQLException {

    int ID = 0;

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection
          .prepareStatement("select ID from usuarios where User_Name ='" + user + "'");
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        ID = resultSet.getInt("ID");
      }

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {
      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }

    return ID;
  }

  public boolean buscarUser(String user) throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection
          .prepareStatement("select * from registros where Usuario ='" + user + "'");
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        setUsuario(resultSet.getString("Usuario"));
        setProdVen(resultSet.getInt("Productos_Vendidos"));
        setTotalVen(resultSet.getDouble("Total_Ventas"));
        setProdCom(resultSet.getInt("Productos_Comprados"));
        setTotalCom(resultSet.getDouble("Total_Compras"));
        setTotalPres(resultSet.getDouble("Total_Prestamos"));
      }

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }

    return user.equals(getUsuario());
  }

  public String returnUser(String stat) throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection
          .prepareStatement("select * from usuarios where Status ='" + stat + "'");
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        setUserLogin(resultSet.getString("User_Name"));
        setPass(resultSet.getString("Password"));
        setStatus(resultSet.getString("Status"));
      }

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }

    return getUserLogin();
  }

  public boolean userExist(String user) throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection
          .prepareStatement("select * from usuarios where User_Name ='" + user + "'");
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        return true;
      }

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }

    return false;
  }

  public boolean buscarEmpleado(String user, String secretpassword) throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(
          "SELECT * FROM usuarios WHERE User_Name='" + user + "' AND password='" + secretpassword
              + "'");
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        return true;
      }

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }

    return false;
  }

  public void updatePassword(String User, String newPassword) throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(
          "UPDATE usuarios SET Password='" + newPassword + "' WHERE User_Name='" + User + "'");
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

  public void updateCantidad(int cantidad, String ID, String table) throws SQLException {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection
          .prepareStatement(
              "UPDATE " + table + " SET Cantidad='" + cantidad + "' WHERE ID='" + ID + "'");
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        Alerts.message("Database not found", "Sorry, there was an error. Try again later.");
      }
    }
  }

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

  public void setDoc(String doc) {
    this.doc = doc;
  }

  public String getUserLogin() {
    return userLogin;
  }

  public void setUserLogin(String userLogin) {
    this.userLogin = userLogin;
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