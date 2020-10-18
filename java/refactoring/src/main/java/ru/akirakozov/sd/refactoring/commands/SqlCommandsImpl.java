package ru.akirakozov.sd.refactoring.commands;

import ru.akirakozov.sd.refactoring.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SqlCommandsImpl {

    public enum CommandsNames {
        INSERT_PRODUCT, SELECT_ALL, PRODUCT_SUM, PRODUCT_COUNT, PRODUCT_MIN_PRICE, PRODUCT_MAX_PRICE
    }

    public static String getSQLTemplate(CommandsNames name) throws IOException {
        Path pathToSql = Paths.get("src/main/resources/sql");

        switch (name) {
            case INSERT_PRODUCT: {
                return FileUtils.readFile(pathToSql.resolve("add-product.sql"));
            }
            case SELECT_ALL: {
                return FileUtils.readFile(pathToSql.resolve("get-product.sql"));
            }
            case PRODUCT_COUNT: {
                return  FileUtils.readFile(pathToSql.resolve("product-count.sql"));
            }
            case PRODUCT_SUM: {
                return  FileUtils.readFile(pathToSql.resolve("product-sum.sql"));
            }
            case PRODUCT_MIN_PRICE: {
                return  FileUtils.readFile(pathToSql.resolve("product-min.sql"));
            }
            case PRODUCT_MAX_PRICE: {
                return  FileUtils.readFile(pathToSql.resolve("product-max.sql"));
            }
            default:
                return "";
        }
    }

}