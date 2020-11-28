package ru.croc.java.school.finaltask;

import org.junit.jupiter.api.*;
import ru.croc.java.school.finaltask.database.DataSourceProvider;
import ru.croc.java.school.finaltask.model.Record;
import ru.croc.java.school.finaltask.repository.RecordRepository;
import ru.croc.java.school.finaltask.xml.ResultStatistic;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;

public class StatisticTest {

    /**
     * Записи.
     */
    private RecordRepository recordRepository;

    @BeforeEach
    public void initRepository() throws IOException {
        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        recordRepository = new RecordRepository(dataSourceProvider.getDataSource());
    }

    /**
     * Удаление таблицы.
     */
    @AfterEach
    public void dropTable() {
        recordRepository.dropTable();
    }


    /**
     * Проверка вычисления статистики.
     */
    @Test
    @DisplayName("Проверка вычисления статистики")
    public void testCalculate() {

        Record record = new Record(
                "city 17", LocalDate.of(2020,4,12),
                2, 8, 4);
        recordRepository.create(record);

        Statistic statistic = new Statistic(recordRepository);

        LocalDate startDate = LocalDate.of(2020, 4, 10);
        LocalDate endDate = LocalDate.of(2020, 4, 20);
        statistic.calculate(startDate, endDate);

        ResultStatistic resultStatistic = new ResultStatistic(startDate, endDate, 4);

        Assertions.assertEquals(resultStatistic, statistic.getResultStatistic());

    }

    /**
     * Проверка вычисления статистики с несколькими записями.
     */
    @Test
    @DisplayName("Проверка вычисления статистики с несколькими записями")
    public void testCalculateWithSeveralRecords() {

        List<Record> recordList = Arrays.asList(
                new Record("city 17", LocalDate.of(2020,4,12),
                        2, 8, 4),
                new Record("city 27", LocalDate.of(2020,4,12),
                        1, 7, 4),
                new Record("city 17", LocalDate.of(2020,4,14),
                        3, 8, 4));
        recordList.forEach(record -> recordRepository.create(record));

        Statistic statistic = new Statistic(recordRepository);

        LocalDate startDate = LocalDate.of(2020, 4, 10);
        LocalDate endDate = LocalDate.of(2020, 4, 13);
        statistic.calculate(startDate, endDate);

        ResultStatistic resultStatistic = new ResultStatistic(startDate, endDate, 5);

        Assertions.assertEquals(resultStatistic, statistic.getResultStatistic());

    }

    /**
     * Проверка вычислений статистики при пустой БД.
     */
    @Test
    @DisplayName("Проверка вычислений статистики при пустой БД")
    public void testEmptyRepository() {
        Statistic statistic = new Statistic(recordRepository);

        LocalDate startDate = LocalDate.of(2020, 4, 10);
        LocalDate endDate = LocalDate.of(2020, 4, 13);
        statistic.calculate(startDate, endDate);

        ResultStatistic resultStatistic = new ResultStatistic(startDate, endDate, NaN);

        Assertions.assertEquals(resultStatistic, statistic.getResultStatistic());
    }

    /**
     * Проверка вычислений статистики при отсутствии новых зараженых.
     */
    @Test
    @DisplayName("Проверка вычислений статистики при отсутствии новых зараженых")
    public void testWithNoInfected() {

        Record record = new Record(
                "city 17", LocalDate.of(2020,4,12),
                0, 8, 4);
        recordRepository.create(record);

        Statistic statistic = new Statistic(recordRepository);

        LocalDate startDate = LocalDate.of(2020, 4, 10);
        LocalDate endDate = LocalDate.of(2020, 4, 13);
        statistic.calculate(startDate, endDate);

        ResultStatistic resultStatistic = new ResultStatistic(startDate, endDate, POSITIVE_INFINITY);

        Assertions.assertEquals(resultStatistic, statistic.getResultStatistic());
    }
}
