package com.bookverse.development.packapps.models;

public class Store {

  private String reference, productState, document, phone, date, user, userLogged, password, userStatus;
  private Double price, totalSales, totalPurchases, totalLoans;
  private int availableProducts, soldProducts, purchasedProducts;

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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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

  public String getUserLogged() {
    return userLogged;
  }

  public void setUserLogged(String userLogged) {
    this.userLogged = userLogged;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
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

  public int getAvailableProducts() {
    return availableProducts;
  }

  public void setAvailableProducts(int availableProducts) {
    this.availableProducts = availableProducts;
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
