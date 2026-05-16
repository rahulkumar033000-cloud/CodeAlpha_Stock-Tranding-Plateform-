package com.mycompany.stocktrading;

import java.util.HashMap;
import java.util.Scanner;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class StockTrading {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<String, Stock> market = new HashMap<>();
        HashMap<String, Integer> portfolio = new HashMap<>();

        double balance = 10000;

        market.put("TCS", new Stock("TCS", 3500));
        market.put("INFY", new Stock("INFY", 1500));
        market.put("RELIANCE", new Stock("RELIANCE", 2500));

        int choice;

        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Balance");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Market Data ---");

                    for (String key : market.keySet()) {
                        Stock s = market.get(key);
                        System.out.println(s.name + " : ₹" + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock name: ");
                    String buyStock = sc.next().toUpperCase();

                    if (market.containsKey(buyStock)) {

                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();

                        double cost = market.get(buyStock).price * qty;

                        if (cost <= balance) {

                            balance -= cost;

                            portfolio.put(
                                    buyStock,
                                    portfolio.getOrDefault(buyStock, 0) + qty
                            );

                            System.out.println("Stock Purchased Successfully!");
                        } else {
                            System.out.println("Insufficient Balance!");
                        }

                    } else {
                        System.out.println("Stock Not Found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock name: ");
                    String sellStock = sc.next().toUpperCase();

                    if (portfolio.containsKey(sellStock)) {

                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();

                        int owned = portfolio.get(sellStock);

                        if (qty <= owned) {

                            double amount = market.get(sellStock).price * qty;
                            balance += amount;

                            portfolio.put(sellStock, owned - qty);

                            System.out.println("Stock Sold Successfully!");

                        } else {
                            System.out.println("Not enough shares!");
                        }

                    } else {
                        System.out.println("You don't own this stock!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Portfolio ---");

                    if (portfolio.isEmpty()) {
                        System.out.println("No stocks owned.");
                    } else {

                        for (String key : portfolio.keySet()) {
                            System.out.println(key + " : " + portfolio.get(key) + " shares");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Current Balance: ₹" + balance);
                    break;

                case 0:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}