package com.bookverse.development.packapps.models;

import lombok.Data;

@Data
public class Store {
  private String reference;
  private String productState;
  private String document;
  private String date;
  private String user;
  private String password;
  private Double price;
  private Double totalSales;
  private Double totalPurchases;
  private Double totalLoans;
  private int unitsActual;
  private int soldProducts;
  private int purchasedProducts;
}