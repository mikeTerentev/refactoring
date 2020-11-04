package ru.akirakozov.sd.refactoring.servlet;

import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.dao.ProductsDao;
import ru.akirakozov.sd.refactoring.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetProductServletTest {

    @Test
    public void getProducts() throws IOException {
        Product product = new Product("phone", 60000);
        Product product2 = new Product("mac", 160000);
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(TestHttpResponse.class);

        ProductsDao dao = mock(ProductsDao.class);
        when(dao.getAllProducts()).thenReturn(Arrays.asList(product, product2));
        GetProductsServlet servlet = new GetProductsServlet(dao);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doGet(request, response);
        printWriter.flush();
        assertEquals("<html><body>\n" +
                "phone\t60000</br>\n" +
                "mac\t160000</br>\n" +
                "</body></html>\n", sw.toString());
        verify(dao, times(1)).getAllProducts();
    }
}
