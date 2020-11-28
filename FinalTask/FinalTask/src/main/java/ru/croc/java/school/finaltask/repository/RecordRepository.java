package ru.croc.java.school.finaltask.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.java.school.finaltask.model.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordRepository {

    /**
     * Название таблицы.
     */
    private static final String TABLE_NAME = "corono_virus_record";

    private EmbeddedDataSource dataSource;

    /**
     * Конструктор.
     *
     * @param dataSource data source
     */
    public RecordRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Инициализация БД.
     */
    private void initTable() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"}
            );
            if (resultSet.next()) {
                System.out.println("table already exist");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                                + "city VARCHAR(255),"
                                + "date_measure DATE,"
                                + "sick INTEGER,"
                                + "recover INTEGER,"
                                + "died INTEGER"
                                + ")"
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("faild to create talble");
            e.printStackTrace();
        }
    }

    /**
     * Удаление таблицы.
     */
    public void dropTable() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE " + TABLE_NAME);
        } catch (SQLException e) {
            System.out.println("faild to delete table");
            e.printStackTrace();
        }
    }

    /**
     * Метод поиска всех книг в БД.
     *
     * @return список все книг
     */
    public List<Record> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME)) {
            List<Record> records = new ArrayList<>();
            while (resultSet.next()) {
                records.add(new Record(
                        resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getDate("date_measure").toLocalDate(),
                        resultSet.getInt("sick"),
                        resultSet.getInt("recover"),
                        resultSet.getInt("died")
                ));
            }
            return records;
        } catch (SQLException e) {
            System.out.println("faild find books");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Метод создания зиписи в БД.
     *
     * @return id созданной записи и -1 если запись небыла создана
     */
    public int create(Record record) {
        String selQuery = "INSERT INTO "
                + TABLE_NAME
                + " (city,"
                + " date_measure,"
                + " sick,"
                + " recover,"
                + " died)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, record.getCity());
            statement.setDate(2, java.sql.Date.valueOf(record.getDate()));
            statement.setInt(3, record.getSickCount());
            statement.setInt(4, record.getRecoverCount());
            statement.setInt(5, record.getDiedCount());
            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    record.setId(generatedKeys.getInt(1));
                    return generatedKeys.getInt(1);
                } else {
                    System.out.println("Create book faild");
                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println("Create book faild");
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Метод поиска заданой.
     *
     * @param id индетификатор записи
     * @return запись
     */
    public Record get(int id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                return new Record(
                        resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getDate("date_measure").toLocalDate(),
                        resultSet.getInt("sick"),
                        resultSet.getInt("recover"),
                        resultSet.getInt("died")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Метод обноляет запись в таблице books.
     *
     * @param record запись
     */
    public void update(Record record) {
        String query = "UPDATE " + TABLE_NAME + " "
                + "SET "
                + "city=?, "
                + "date_measure=?, "
                + "sick=?, "
                + "recover=?, "
                + "died=? "
                + "WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, record.getCity());
            statement.setDate(2, java.sql.Date.valueOf(record.getDate()));
            statement.setInt(3, record.getSickCount());
            statement.setInt(4, record.getRecoverCount());
            statement.setInt(5, record.getDiedCount());
            statement.setInt(6, record.getId());

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод удаляет запись из таюлици.
     *
     * @param record запись
     */
    public void delete(Record record) {
        if (record != null) {

            String query = "DELETE FROM " + TABLE_NAME + " WHERE id=?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, record.getId());
                statement.execute();
                record.setId(-1);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
