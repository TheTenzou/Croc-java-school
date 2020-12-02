package ru.croc.java.school.finaltask.database.repository;

import ru.croc.java.school.finaltask.model.dto.Record;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Инициализаци и удаление таблицы.
 */
public class RecordTable {

    /**
     * Инициализация БД.
     */
    public static void init(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(
                    null,
                    null,
                    Record.getTableName().toUpperCase(),
                    new String[]{"TABLE"}
            );
            if (resultSet.next()) {
                System.out.println("table already exist");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + Record.getTableName()
                                + " ("
                                + "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                                + "city VARCHAR(255),"
                                + "date_measure DATE,"
                                + "infected INTEGER,"
                                + "recover INTEGER,"
                                + "died INTEGER"
                                + ")"
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("failed to create table");
            e.printStackTrace();
        }
    }

    /**
     * Удаление таблицы.
     */
    static public void drop(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE " + Record.getTableName());
        } catch (SQLException e) {
            System.out.println("failed to delete table");
            e.printStackTrace();
        }
    }
}
