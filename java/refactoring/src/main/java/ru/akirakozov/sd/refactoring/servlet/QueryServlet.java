package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.commands.SqlCommandsImpl;
import ru.akirakozov.sd.refactoring.response.ResponseBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static ru.akirakozov.sd.refactoring.commands.SqlCommandsImpl.CommandsNames.*;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        ResponseBuilder responseBuilder = new ResponseBuilder(response);
        if ("max".equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(SqlCommandsImpl.getSQLTemplate(PRODUCT_MAX_PRICE));
                    responseBuilder.addBlock("<h1>Product with max price: </h1>");

                    while (rs.next()) {
                        String name = rs.getString("name");
                        int price = rs.getInt("price");
                        responseBuilder.addBlock(name + "\t" + price + "</br>");
                    }

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("min".equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(SqlCommandsImpl.getSQLTemplate(PRODUCT_MIN_PRICE));

                    responseBuilder.addBlock("<h1>Product with min price: </h1>");

                    while (rs.next()) {
                        String name = rs.getString("name");
                        int price = rs.getInt("price");
                        responseBuilder.addBlock(name + "\t" + price + "</br>");
                    }

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("sum".equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(SqlCommandsImpl.getSQLTemplate(PRODUCT_SUM));

                    responseBuilder.addBlock("Summary price: ");

                    if (rs.next()) {
                        responseBuilder.addBlock(Integer.toString(rs.getInt(1)));
                    }

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("count".equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(SqlCommandsImpl.getSQLTemplate(PRODUCT_COUNT));

                    responseBuilder.addBlock("Number of products: ");

                    if (rs.next()) {
                        responseBuilder.addBlock(Integer.toString(rs.getInt(1)));
                    }

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            responseBuilder.addBlock("Unknown command: " + command);
        }
        responseBuilder.build();
    }

}
