package ru.TheTenzou.croc.java.school.Lecture5.Task.repository;

import org.junit.jupiter.api.*;
import ru.TheTenzou.croc.java.school.Lecture5.Task.DataBase.DataSourceProvider;
import ru.TheTenzou.croc.java.school.Lecture5.Task.model.Book;

import javax.imageio.IIOException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookRepositoryTest {

    /**
     * Репозиторй с книгами.
     */
    private BookRepository bookRepository;

    /**
     * Список книг.
     */
    private List<Book> books = Arrays.asList(
            new Book("title1", "author1", true,
                    LocalDate.of(2020, 10,10),
                    LocalDateTime.of(2020, 10, 10, 10, 10)),
            new Book("title2", "author2", false,
                    LocalDate.of(2020, 10,10),
                    LocalDateTime.of(2020, 10, 10, 10, 10)));


    /**
     * Инициализация репозитория и заполнение его данными.
     */
    @BeforeEach
    public void initRepository() throws IOException {
        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IIOException e) {
            System.out.println(e.getMessage());
            throw e;
        }

        bookRepository = new BookRepository(dataSourceProvider.getDataSource());

        for (Book book : books) {
            bookRepository.createBook(book);
        }
    }

    /**
     * Удаление таблицы.
     */
    @AfterEach
    public void dropTable() {
        bookRepository.dropTable();
    }

    /**
     * Проверка доваления данных в репозиторий.
     */
    @Test
    @DisplayName("Проверка доваления данных в репозиторий")
    public void testCreateBook() {
        Assertions.assertEquals(books, bookRepository.findAll());
    }

    /**
     * Проверка метода getBook.
     */
    @Test
    @DisplayName("Проверка метода getBook")
    public void testGetBook() {
        Assertions.assertEquals(books.get(0), bookRepository.getBook(books.get(0).getId()));
        Assertions.assertEquals(books.get(1), bookRepository.getBook(books.get(1).getId()));

        Book book = new Book("title", "author", true,
                        LocalDate.of(2020, 10,10),
                        LocalDateTime.of(2020, 10, 10, 10, 10));

        Assertions.assertNull(bookRepository.getBook(book.getId()));
    }

    /**
     * Проверка метода updateBook.
     */
    @Test
    @DisplayName("Проверка метода updateBook")
    public void testUpdateBook() {
        Book book = bookRepository.getBook(1);
        book.setTitle("NewTitle");
        book.setReleaseDate(LocalDate.now());
        book.setNew(false);

        bookRepository.updateBook(book);

        Assertions.assertEquals(book, bookRepository.getBook(1));
    }

    /**
     * Проверка метода deleteBook.
     */
    @Test
    @DisplayName("Проверка метода deleteBook")
    public void testDeleteBook() {

        bookRepository.deleteBook(books.get(0));

        List<Book> expectedList = Collections.singletonList(books.get(1));

        Assertions.assertEquals(-1, books.get(0).getId());
        Assertions.assertEquals(expectedList, bookRepository.findAll());
    }
}
