package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.ProductsDao;
import ru.akirakozov.sd.refactoring.model.Product;
import ru.akirakozov.sd.refactoring.response.ResponseBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        ResponseBuilder responseBuilder = new ResponseBuilder(response);
        switch (command) {
            case "max": {
                Product product = ProductsDao.getMaxPriceProduct();
                responseBuilder.addBlock("<h1>Product with max price: </h1>");
                responseBuilder.addBlock(product.getName() + "\t" + product.getPrice() + "</br>");
                break;
            }
            case "min": {
                responseBuilder.addBlock("<h1>Product with min price: </h1>");
                Product product = ProductsDao.getMinPriceProduct();
                responseBuilder.addBlock(product.getName() + "\t" + product.getPrice() + "</br>");
                break;
            }
            case "sum":
                responseBuilder.addBlock("Summary price: ");
                int sum = ProductsDao.getPricesSum();
                responseBuilder.addBlock(Integer.toString(sum));
                break;
            case "count":
                responseBuilder.addBlock("Number of products: ");
                int count = ProductsDao.getProductsCount();
                responseBuilder.addBlock(Integer.toString(count));
                break;
            default:
                response.getWriter().println("Unknown command: " + command);
        }
        responseBuilder.build();
    }
}
