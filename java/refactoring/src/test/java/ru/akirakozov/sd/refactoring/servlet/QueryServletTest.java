package ru.akirakozov.sd.refactoring.servlet;

import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.dao.ProductsDao;
import ru.akirakozov.sd.refactoring.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QueryServletTest {

    @Test
    public void getMin() throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(TestHttpResponse.class);

        when(request.getParameter("command")).thenReturn("min");

        ProductsDao dao = mock(ProductsDao.class);
        when(dao.getMinPriceProduct()).thenReturn(new Product("phone", 15));
        QueryServlet servlet = new QueryServlet(dao);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doGet(request, response);
        printWriter.flush();
        assertEquals("<html><body>\n" +
                "<h1>Product with min price: </h1>\n" +
                "phone\t15</br>\n" +
                "</body></html>\n", sw.toString());
        verify(dao, times(1)).getMinPriceProduct();
    }

    @Test
    public void getMax() throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(TestHttpResponse.class);

        when(request.getParameter("command")).thenReturn("max");

        ProductsDao dao = mock(ProductsDao.class);
        when(dao.getMaxPriceProduct()).thenReturn(new Product("phone", 15));
        QueryServlet servlet = new QueryServlet(dao);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doGet(request, response);
        printWriter.flush();
        assertEquals("<html><body>\n" +
                "<h1>Product with max price: </h1>\n" +
                "phone\t15</br>\n" +
                "</body></html>\n", sw.toString());
        verify(dao, times(1)).getMaxPriceProduct();
    }

    @Test
    public void getPricesSum() throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(TestHttpResponse.class);

        when(request.getParameter("command")).thenReturn("sum");

        ProductsDao dao = mock(ProductsDao.class);
        when(dao.getPricesSum()).thenReturn(15);
        QueryServlet servlet = new QueryServlet(dao);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doGet(request, response);
        printWriter.flush();
        assertEquals("<html><body>\n" +
                "Summary price: \n" +
                "15\n" +
                "</body></html>\n", sw.toString());
        verify(dao, times(1)).getPricesSum();
    }

    @Test
    public void getCount() throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(TestHttpResponse.class);

        when(request.getParameter("command")).thenReturn("count");

        ProductsDao dao = mock(ProductsDao.class);
        when(dao.getProductsCount()).thenReturn(15);
        QueryServlet servlet = new QueryServlet(dao);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doGet(request, response);
        printWriter.flush();
        assertEquals("<html><body>\n" +
                "Number of products: \n" +
                "15\n" +
                "</body></html>\n", sw.toString());
        verify(dao, times(1)).getProductsCount();
    }
}
