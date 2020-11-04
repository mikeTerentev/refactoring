package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.commands.SqlCommands;
import ru.akirakozov.sd.refactoring.dao.ProductsDao;
import ru.akirakozov.sd.refactoring.response.ResponseBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {

    private final ProductsDao dao;

    public AddProductServlet(ProductsDao dao) {
        this.dao = dao;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        ResponseBuilder responseBuilder = new ResponseBuilder(response);

        dao.addProduct(name, price);

        responseBuilder.addBlock("OK");
        responseBuilder.build();
    }
}
