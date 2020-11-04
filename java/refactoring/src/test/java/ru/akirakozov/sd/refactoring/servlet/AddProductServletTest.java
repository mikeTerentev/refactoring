package ru.akirakozov.sd.refactoring.servlet;

import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.dao.ProductsDao;
import ru.akirakozov.sd.refactoring.model.Product;
import org.mockito.*;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddProductServletTest {

    @Test
    public void testAdd() throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw);
        Product product = new Product("phone", 60000);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(TestHttpResponse.class);
        ProductsDao dao = mock(ProductsDao.class);
        when(request.getParameter("name")).thenReturn(product.getName());
        when(request.getParameter("price")).thenReturn(Integer.toString(product.getPrice()));
        doNothing().when(dao).addProduct(product.getName(), product.getPrice());
        AddProductServlet servlet = new AddProductServlet(dao);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doGet(request, response);
        printWriter.flush();
        assertEquals("<html><body>\n" +
                "OK\n" +
                "</body></html>\n", sw.toString());
        verify(dao, times(1)).addProduct(product.getName(), product.getPrice());
    }
}
