package ru.akirakozov.sd.refactoring.servlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.commands.SqlCommands;
import ru.akirakozov.sd.refactoring.model.Product;

import java.io.IOException;

public class SqlCommandsTest {

    @Test
    public void insertProduct() throws IOException {
        Assertions.assertEquals(SqlCommands.insertProduct("phone", 123),
                "INSERT INTO PRODUCT (name, price)\n" +
                        "VALUES (?, ?) phone 123");
    }

    @Test
    public void getProductCnt() throws IOException {
        Assertions.assertEquals(SqlCommands.getProductCountSql(),
                "SELECT SUM(price) FROM PRODUCT");
    }

    @Test
    public void getProductsAll() throws IOException {
        Assertions.assertEquals(SqlCommands.getSelectAllSql(), "SELECT * FROM PRODUCT");
    }

    @Test
    public void getProductSum() throws IOException {
        Assertions.assertEquals(SqlCommands.getProductSumSql(),
                "SELECT SUM(price) FROM PRODUCT");
    }

    @Test
    public void getProductMax() throws IOException {
        Assertions.assertEquals(SqlCommands.getProductMaxSql(),
                "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
    }
}
