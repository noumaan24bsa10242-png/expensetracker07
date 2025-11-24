package services;

import database.DBConnection;
import models.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    public void addExpense(String category, double amount, String date) {
        try {
            Connection con = DBConnection.connect();
            String query = "INSERT INTO expenses(category, amount, date) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, category);
            ps.setDouble(2, amount);
            ps.setString(3, date);
            ps.executeUpdate();
            con.close();

            System.out.println("Expense added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding expense: " + e.getMessage());
        }
    }

    public List<Expense> getAllExpenses() {
        List<Expense> list = new ArrayList<>();

        try {
            Connection con = DBConnection.connect();
            String query = "SELECT * FROM expenses";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("date")
                ));
            }
            con.close();

        } catch (Exception e) {
            System.out.println("Error fetching expenses: " + e.getMessage());
        }

        return list;
    }

    public double getTotalSpent() {
        double total = 0;

        try {
            Connection con = DBConnection.connect();
            String query = "SELECT SUM(amount) AS total FROM expenses";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) total = rs.getDouble("total");
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return total;
    }
}
