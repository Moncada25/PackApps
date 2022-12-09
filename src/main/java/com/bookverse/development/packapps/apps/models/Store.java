package com.bookverse.development.packapps.apps.models;

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

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getProductState() {
    return productState;
  }

  public void setProductState(String productState) {
    this.productState = productState;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getTotalSales() {
    return totalSales;
  }

  public void setTotalSales(Double totalSales) {
    this.totalSales = totalSales;
  }

  public Double getTotalPurchases() {
    return totalPurchases;
  }

  public void setTotalPurchases(Double totalPurchases) {
    this.totalPurchases = totalPurchases;
  }

  public Double getTotalLoans() {
    return totalLoans;
  }

  public void setTotalLoans(Double totalLoans) {
    this.totalLoans = totalLoans;
  }

  public int getUnitsActual() {
    return unitsActual;
  }

  public void setUnitsActual(int unitsAvailable) {
    this.unitsActual = unitsAvailable;
  }

  public int getSoldProducts() {
    return soldProducts;
  }

  public void setSoldProducts(int soldProducts) {
    this.soldProducts = soldProducts;
  }

  public int getPurchasedProducts() {
    return purchasedProducts;
  }

  public void setPurchasedProducts(int purchasedProducts) {
    this.purchasedProducts = purchasedProducts;
  }
}