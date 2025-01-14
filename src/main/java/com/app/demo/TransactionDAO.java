// Java
package com.app.demo;

import java.sql.*;
import java.util.*;

public class TransactionDAO {
    public static void insertTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions(date, description, amount, category, incomeExpense, payer) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transaction.getDate());
            pstmt.setString(2, transaction.getDescription());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getCategory().getName());
            pstmt.setString(5, transaction.getIncomeExpense());
            pstmt.setString(6, transaction.getPayer());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Category category = new Category(rs.getString("category"));
                Transaction transaction = new Transaction(
                        rs.getString("date"),
                        rs.getString("description"),
                        rs.getDouble("amount"),
                        category,
                        rs.getString("incomeExpense"),
                        rs.getString("payer")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }
    public static void deleteTransaction(Transaction transaction){
        String sql = "DELETE FROM transactions WHERE date = ? AND description = ? AND amount = ? AND category = ? AND incomeExpense = ? AND payer = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transaction.getDate());
            pstmt.setString(2, transaction.getDescription());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getCategory().getName());
            pstmt.setString(5, transaction.getIncomeExpense());
            pstmt.setString(6, transaction.getPayer());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void updateTransaction(Transaction transaction){
        String sql = "UPDATE transactions SET date = ?, description = ?, amount = ?, category = ?, incomeExpense = ?, payer = ? WHERE date = ? AND description = ? AND amount = ? AND category = ? AND incomeExpense = ? AND payer = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transaction.getDate());
            pstmt.setString(2, transaction.getDescription());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getCategory().getName());
            pstmt.setString(5, transaction.getIncomeExpense());
            pstmt.setString(6, transaction.getPayer());
            pstmt.setString(7, transaction.getDate());
            pstmt.setString(8, transaction.getDescription());
            pstmt.setDouble(9, transaction.getAmount());
            pstmt.setString(10, transaction.getCategory().getName());
            pstmt.setString(11, transaction.getIncomeExpense());
            pstmt.setString(12, transaction.getPayer());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void saveTotalBalance(double totalBalance){
        String sql = "UPDATE totalBalance SET balance = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, totalBalance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static double getTotalBalance(){
        double totalBalance = 0;
        String sql = "SELECT total_balance FROM totalBalance";
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            totalBalance = rs.getDouble("balance");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalBalance;
    }
}