package ru.akirakozov.sd.refactoring.dao;

import ru.akirakozov.sd.refactoring.commands.SqlCommands;
import ru.akirakozov.sd.refactoring.model.Product;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductsDao {

    public int getProductsCount() throws IOException {
        return proccessIntRequest(SqlCommands.getProductCountSql());
    }

    public int getPricesSum() throws IOException {
        return proccessIntRequest(SqlCommands.getProductSumSql());
    }

    public Product getMinPriceProduct() throws IOException {
        return processProductRequest(SqlCommands.getProductMinPriceSql());
    }

    public Product getMaxPriceProduct() throws IOException {
        return processProductRequest(SqlCommands.getProductMaxPriceSql());
    }

    public void addProduct(String name, long price) throws IOException {
        String sql = SqlCommands.insertProduct(name, price);
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                try (Statement stmt = c.createStatement()) {
                    stmt.executeUpdate(sql);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() throws IOException {
        List<Product> result = new ArrayList<>();
        String sql = SqlCommands.getSelectAllSql();
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                try (Statement stmt = c.createStatement()) {
                    try (ResultSet rs = stmt.executeQuery(sql)) {
                        while (rs.next()) {
                            String name = rs.getString("name");
                            int price = rs.getInt("price");
                            result.add(new Product(name, price));
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Product processProductRequest(String sql) {
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                try (Statement stmt = c.createStatement()) {
                    try (ResultSet rs = stmt.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Product(rs.getString("name"), rs.getInt("price"));
                        }
                        throw new SQLException("No result given");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private int proccessIntRequest(String sql) {
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                try (Statement stmt = c.createStatement()) {
                    try (ResultSet rs = stmt.executeQuery(sql)) {
                        if (rs.next()) {
                            return rs.getInt(1);
                        }
                        throw new SQLException("No result given");
                    }

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
