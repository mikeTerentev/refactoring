package ru.akirakozov.sd.refactoring.commands;

import ru.akirakozov.sd.refactoring.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SqlCommands {
    private static final String SQL_SCRIPTS_PATH = "src/main/resources/sql";

    public static String insertProduct(String name, long price) throws IOException {
        return FileUtils.readFile(Paths.get(SQL_SCRIPTS_PATH + "/add-product.sql")) + " " + name + " " + price;
    }

    public static String getSelectAllSql() throws IOException {
        return FileUtils.readFile(Paths.get(SQL_SCRIPTS_PATH + "/get-product.sql"));
    }

    public static String getProductCountSql() throws IOException {
        return FileUtils.readFile(Paths.get(SQL_SCRIPTS_PATH + "/product-count.sql"));
    }

    public static String getProductSumSql() throws IOException {
        return FileUtils.readFile(Paths.get(SQL_SCRIPTS_PATH + "/product-sum.sql"));
    }

    public static String getProductMinPriceSql() throws IOException {
        return FileUtils.readFile(Paths.get(SQL_SCRIPTS_PATH + "/product-min.sql"));
    }

    public static String getProductMaxSql() throws IOException {
        return FileUtils.readFile(Paths.get(SQL_SCRIPTS_PATH + "/product-max.sql"));
    }

    public static String getProductMaxPriceSql() throws IOException {
        return FileUtils.readFile(Paths.get(SQL_SCRIPTS_PATH + "/product-max.sql"));
    }
}