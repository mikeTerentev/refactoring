package ru.akirakozov.sd.refactoring.commands;

import ru.akirakozov.sd.refactoring.utils.FileUtils;

import javax.print.DocFlavor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

abstract class SqlCommands {

    String insertProductCommand;

    String selectAllCommand;
}

class SqlCommandsImpl extends SqlCommands {

    SqlCommandsImpl(Path pathToSql) throws IOException {
        if (pathToSql == null) {
            throw new IllegalArgumentException();
        }

        this.insertProductCommand = FileUtils.readFile(pathToSql.resolve("add-product.sql"));
        this.selectAllCommand = FileUtils.readFile(pathToSql.resolve("select-all.sql"));
    }

}