package ru.croc.java.school.finaltask.database.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.java.school.finaltask.model.dto.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий с записями.
 */
public class RecordRepository {

    /**
     * DataSource.
     */
    private EmbeddedDataSource dataSource;

    /**
     * Конструктор.
     *
     * @param dataSource data source
     */
    public RecordRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        RecordTable.init(this.dataSource);
    }

    /**
     * Удаление таблицы.
     */
    public void dropTable() {
        RecordTable.drop(dataSource);
    }

    /**
     * Метод поиска всех книг в БД.
     *
     * @return список все книг
     */
    public List<Record> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Record.getTableName())) {
            List<Record> records = new ArrayList<>();
            while (resultSet.next()) {
                records.add(new Record(
                        resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getDate("date_measure").toLocalDate(),
                        resultSet.getInt("infected"),
                        resultSet.getInt("recover"),
                        resultSet.getInt("died")
                ));
            }
            return records;
        } catch (SQLException e) {
            System.out.println("failed find books");
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
                + Record.getTableName()
                + " (city,"
                + " date_measure,"
                + " infected,"
                + " recover,"
                + " died)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, record.getCity());
            statement.setDate(2, java.sql.Date.valueOf(record.getDate()));
            statement.setInt(3, record.getInfectedCount());
            statement.setInt(4, record.getRecoverCount());
            statement.setInt(5, record.getDiedCount());
            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    record.setId(generatedKeys.getInt(1));
                    return generatedKeys.getInt(1);
                } else {
                    System.out.println("Create book failed");
                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println("Create book failed");
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
        String query = "SELECT * FROM " + Record.getTableName() + " WHERE id=?";

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
                        resultSet.getInt("infected"),
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
     * Метод обноляет запись в таблице.
     *
     * @param record запись
     */
    public void update(Record record) {
        String query = "UPDATE " + Record.getTableName() + " "
                + "SET "
                + "city=?, "
                + "date_measure=?, "
                + "infected=?, "
                + "recover=?, "
                + "died=? "
                + "WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, record.getCity());
            statement.setDate(2, java.sql.Date.valueOf(record.getDate()));
            statement.setInt(3, record.getInfectedCount());
            statement.setInt(4, record.getRecoverCount());
            statement.setInt(5, record.getDiedCount());
            statement.setInt(6, record.getId());

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод удаляет запись из таблици.
     *
     * @param record запись
     */
    public void delete(Record record) {
        if (record != null) {

            String query = "DELETE FROM " + Record.getTableName() + " WHERE id=?";

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
