package com.bookverse.development.packapps.models;

import static com.bookverse.development.packapps.utils.DatabaseConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.utils.DatabaseConstants.DICES;
import static com.bookverse.development.packapps.utils.DatabaseConstants.FEEDBACK;
import static com.bookverse.development.packapps.utils.DatabaseConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.utils.DatabaseConstants.HANGMAN;
import static com.bookverse.development.packapps.utils.DatabaseConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.DatabaseConstants.LOANS;
import static com.bookverse.development.packapps.utils.DatabaseConstants.NOTES;
import static com.bookverse.development.packapps.utils.DatabaseConstants.PURCHASES;
import static com.bookverse.development.packapps.utils.DatabaseConstants.PUZZLE;
import static com.bookverse.development.packapps.utils.DatabaseConstants.SALES;
import static com.bookverse.development.packapps.utils.DatabaseConstants.USERS;

import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Queries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.sql.DataSource;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jetbrains.annotations.NotNull;

public class Database {

  public static final Store store = new Store();
  private static Connection connection = null;
  private static ResultSet resultSet;
  private static PreparedStatement preparedStatement;
  private static DefaultTableModel tableModel;
  private static DataSource dataSource;
  private static DataSourceService dataSourceService = new DataSourceService();

  public static boolean insertData(@NotNull String[] data) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      switch (data[0]) {
        case GUESS_NUMBER:
          preparedStatement = connection.prepareStatement(Queries.insertGuessNumber());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setInt(2, Integer.parseInt(data[2]));
          preparedStatement.setString(3, data[3]);
          preparedStatement.setString(4, data[4]);
          preparedStatement.execute();
          break;
        case HANGMAN:
          preparedStatement = connection.prepareStatement(Queries.insertHangman());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setInt(2, Integer.parseInt(data[2]));
          preparedStatement.setString(3, data[3]);
          preparedStatement.setString(4, data[4]);
          preparedStatement.setString(5, data[5]);
          preparedStatement.execute();
          break;
        case DICES:
          preparedStatement = connection.prepareStatement(Queries.insertDices());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setInt(3, Integer.parseInt(data[3]));
          preparedStatement.setString(4, data[4]);
          preparedStatement.execute();
          break;
        case PUZZLE:
          preparedStatement = connection.prepareStatement(Queries.insertPuzzle());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setString(3, data[3]);
          preparedStatement.setInt(4, Integer.parseInt(data[4]));
          preparedStatement.setString(5, data[5]);
          preparedStatement.execute();
          break;
        case NOTES:
          preparedStatement = connection.prepareStatement(Queries.insertNote());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setInt(3, Integer.parseInt(data[3]));
          preparedStatement.setString(4, data[4]);
          preparedStatement.setString(5, data[5]);
          preparedStatement.setString(6, data[6]);
          preparedStatement.execute();
          break;
        case FEEDBACK:
          preparedStatement = connection.prepareStatement(Queries.insertFeedback());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setString(3, data[3]);
          preparedStatement.execute();
          break;
        case INVENTORY:
          preparedStatement = connection.prepareStatement(Queries.insertInventory());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setDouble(3, Double.parseDouble(data[3]));
          preparedStatement.setInt(4, Integer.parseInt(data[4]));
          preparedStatement.execute();
          break;
        case CASH_REGISTER:
          preparedStatement = connection.prepareStatement(Queries.insertRecord());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setInt(2, Integer.parseInt(data[2]));
          preparedStatement.setDouble(3, Double.parseDouble(data[3]));
          preparedStatement.setInt(4, Integer.parseInt(data[4]));
          preparedStatement.setDouble(5, Double.parseDouble(data[5]));
          preparedStatement.setDouble(6, Double.parseDouble(data[6]));
          preparedStatement.execute();
          break;
        case LOANS:
          preparedStatement = connection.prepareStatement(Queries.insertLoan());
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
          preparedStatement = connection.prepareStatement(Queries.insertUser());
          preparedStatement.setString(1, data[1]);
          preparedStatement.setString(2, data[2]);
          preparedStatement.setString(3, data[3]);
          preparedStatement.execute();
          break;
        case PURCHASES:
          preparedStatement = connection.prepareStatement(Queries.insertPurchase());
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
          preparedStatement = connection.prepareStatement(Queries.insertSale());
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

  public static boolean readTable(JTable tabla, String query, boolean isMain) {

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

  public static void updateData(String nickname, String id, String table) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.updateNickname(nickname, id, table));
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

  public static void deleteData(@NotNull String[] rows, String table) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      for (String row : rows) {
        preparedStatement = connection.prepareStatement(Queries.deleteDataByID(row, table));
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

  public static void updateInventory(int units, String reference, boolean isPurchase) {

    int trueUnits;

    if (isPurchase) {
      trueUnits = units + store.getUnitsActual();
    } else {
      trueUnits = units - store.getUnitsActual();
    }

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.updateInventory(reference, trueUnits));
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

  public static boolean searchProductByReference(String reference, String table) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.getDataProduct(reference, table));
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        store.setReference(resultSet.getString("ID"));
        store.setProductState(resultSet.getString("State"));
        store.setPrice(resultSet.getDouble("Price"));
        store.setUnitsActual(resultSet.getInt("Quantity"));
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

    return reference.equals(store.getReference());
  }

  public static void updateSales(String user, int productsSold, Double totalSales) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries
          .updateCashRegisterSales(user, (productsSold + store.getSoldProducts()),
              (totalSales + store.getTotalSales())));
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

  public static void updatePurchases(String user, int purchasedProducts, Double totalPurchases) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries
          .updateCashRegisterPurchases(user, (purchasedProducts + store.getPurchasedProducts()),
              (totalPurchases + store.getTotalPurchases())));
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

  public static void updateLoan(String user, Double loanValue) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(
          Queries.updateCashRegisterLoan(user, (loanValue + store.getTotalLoans())));
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

  public static void recordLogin(String status, String user) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.recordLogin(status, user));
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

  public static boolean searchDataUserInCashRegister(String user) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.searchDataUserInCashRegister(user));
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        store.setUser(resultSet.getString("User"));
        store.setSoldProducts(resultSet.getInt("SoldProducts"));
        store.setTotalSales(resultSet.getDouble("TotalSales"));
        store.setPurchasedProducts(resultSet.getInt("PurchasedProducts"));
        store.setTotalPurchases(resultSet.getDouble("TotalPurchases"));
        store.setTotalLoans(resultSet.getDouble("TotalLoans"));
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

    return user.equals(store.getUser());
  }

  public static boolean userAlreadyExist(String user) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.searchUser(user));
      resultSet = preparedStatement.executeQuery();

      return resultSet.next();

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

  public static boolean searchUserRegister(String user, String password) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.searchUserRegister(user, password));
      resultSet = preparedStatement.executeQuery();

      return resultSet.next();

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

  public static void updatePassword(String user, String newPassword) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.updatePassword(user, newPassword));
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

  public static void updateUsername(String user, String newUsername) {

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.updateUsername(user, newUsername));
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

  public static List<String> getListBook() {

    ArrayList<String> listBook = new ArrayList<>();

    try {
      dataSource = dataSourceService.getDataSource();
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(Queries.getTitleBooks());
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        listBook.add(resultSet.getString(1));
      }

      return listBook;

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

    return listBook;
  }
}