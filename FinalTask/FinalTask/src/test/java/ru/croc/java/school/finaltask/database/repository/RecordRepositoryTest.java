package ru.croc.java.school.finaltask.database.repository;

import org.junit.jupiter.api.*;
import ru.croc.java.school.finaltask.database.datasource.DataSourceProvider;
import ru.croc.java.school.finaltask.model.dto.Record;

import javax.imageio.IIOException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class RecordRepositoryTest {
    /**
     * Репозиторй.
     */
    private RecordRepository recordRepository;

    /**
     * Список записей.
     */
    private List<Record> records = Arrays.asList(
            new Record("City 17", LocalDate.of(2020, 4, 13),
                    27, 7, 1),
            new Record("City 17", LocalDate.of(2020, 4, 14),
                    17, 8, 0),
            new Record("City 18", LocalDate.of(2020, 4, 13),
                    14, 6, 0)
    );


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

        recordRepository = new RecordRepository(dataSourceProvider.getDataSource());

        for (Record record : records) {
            recordRepository.create(record);
        }
    }

    /**
     * Удаление таблицы.
     */
    @AfterEach
    public void dropTable() {
        recordRepository.dropTable();
    }

    /**
     * Проверка доваления данных в репозиторий.
     */
    @Test
    @DisplayName("Проверка доваления данных в репозиторий")
    public void testCreate() {
        Assertions.assertEquals(records, recordRepository.findAll());
    }

    /**
     * Проверка метода get.
     */
    @Test
    @DisplayName("Проверка метода get")
    public void testGet() {
        Assertions.assertEquals(records.get(0), recordRepository.get(records.get(0).getId()));
        Assertions.assertEquals(records.get(1), recordRepository.get(records.get(1).getId()));

        Record record = new Record("City 18",
                LocalDate.of(2020, 4, 13),
                14, 6, 0);

        Assertions.assertNull(recordRepository.get(record.getId()));
    }

    /**
     * Проверка метода update.
     */
    @Test
    @DisplayName("Проверка метода update")
    public void testUpdate() {
        Record record = recordRepository.get(1);
        record.setCity("new city");
        record.setDate(LocalDate.now());
        record.setRecoverCount(100);

        recordRepository.update(record);

        Assertions.assertEquals(record, recordRepository.get(1));
    }

    /**
     * Проверка метода delete.
     */
    @Test
    @DisplayName("Проверка метода delete")
    public void testDelete() {

        recordRepository.delete(records.get(0));

        List<Record> expectedList = Arrays.asList(records.get(1), records.get(2));

        Assertions.assertEquals(-1, records.get(0).getId());
        Assertions.assertEquals(expectedList, recordRepository.findAll());
    }
}
