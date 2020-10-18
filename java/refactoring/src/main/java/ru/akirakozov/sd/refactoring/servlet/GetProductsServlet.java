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

import static ru.akirakozov.sd.refactoring.commands.SqlCommandsImpl.CommandsNames.SELECT_ALL;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBuilder responseBuilder = new ResponseBuilder(response);
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(SqlCommandsImpl.getSQLTemplate(SELECT_ALL));
                while (rs.next()) {
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    responseBuilder.addBlock("<br>" + name + "\t" + price + "</br>");
                }

                rs.close();
                stmt.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        responseBuilder.build();
    }
}
