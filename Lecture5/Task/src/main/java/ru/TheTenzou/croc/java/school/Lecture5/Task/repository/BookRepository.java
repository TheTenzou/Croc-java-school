package ru.TheTenzou.croc.java.school.Lecture5.Task.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.TheTenzou.croc.java.school.Lecture5.Task.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблице с данными о книгах (Books).
 */
public class BookRepository {

    private static final String TABLE_NAME = "book";

    private EmbeddedDataSource dataSource;

    /**
     * Конструктор.
     *
     * @param dataSource data source
     */
    public BookRepository(EmbeddedDataSource dataSource) {
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
                                + "title VARCHAR(255),"
                                + "author_full_name VARCHAR(255),"
                                + "is_new BOOLEAN,"
                                + "releas_date DATE,"
                                + "avaliability_date_time TIMESTAMP"
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
     * Мето поиска всех книг в БД.
     *
     * @return список все книг
     */
    public List<Book> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME)) {
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author_full_name"),
                        resultSet.getBoolean("is_new"),
                        resultSet.getDate("releas_date").toLocalDate(),
                        resultSet.getTimestamp("avaliability_date_time").toLocalDateTime()
                ));
            }
            return books;
        } catch (SQLException e) {
            System.out.println("faild find books");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Мето создания зиписи в БД.
     *
     * @return id созданной записи и -1 если запись небыла создана
     */
    public int createBook(Book book) {
        String selQuery = "INSERT INTO "
                + TABLE_NAME
                + " (title,"
                + " author_full_name,"
                + " is_new,"
                + " releas_date,"
                + " avaliability_date_time)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAthorFullName());
            statement.setBoolean(3, book.isNew());
            statement.setDate(4, java.sql.Date.valueOf(book.getReleaseDate()));
            statement.setTimestamp(5, java.sql.Timestamp.valueOf(book.getAvaliabilityDateTime()));
            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
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
     * Метод поиска заданой кники.
     *
     * @param id индетификатор киниги
     * @return книга
     */
    public Book getBook(int id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                return new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author_full_name"),
                        resultSet.getBoolean("is_new"),
                        resultSet.getDate("releas_date").toLocalDate(),
                        resultSet.getTimestamp("avaliability_date_time").toLocalDateTime()
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
     * @param book книга
     */
    public void updateBook(Book book) {
        String query = "UPDATE " + TABLE_NAME + " "
                + "SET "
                + "title=?, "
                + "author_full_name=?, "
                + "is_new=?, "
                + "releas_date=?, "
                + "avaliability_date_time=? "
                + "WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAthorFullName());
            statement.setBoolean(3, book.isNew());
            statement.setDate(4, java.sql.Date.valueOf(book.getReleaseDate()));
            statement.setTimestamp(5, java.sql.Timestamp.valueOf(book.getAvaliabilityDateTime()));
            statement.setInt(6, book.getId());

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод удаляет запись из таюлици book.
     *
     * @param book кинига
     */
    public void deleteBook(Book book) {
        if (book != null) {

            String query = "DELETE FROM " + TABLE_NAME + " WHERE id=?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, book.getId());
                statement.execute();
                book.setId(-1);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
