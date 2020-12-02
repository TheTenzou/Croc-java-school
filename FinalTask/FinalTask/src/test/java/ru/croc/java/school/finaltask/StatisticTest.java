package ru.croc.java.school.finaltask;

import org.junit.jupiter.api.*;
import ru.croc.java.school.finaltask.database.datasource.DataSourceProvider;
import ru.croc.java.school.finaltask.database.model.Record;
import ru.croc.java.school.finaltask.database.repository.RecordRepository;
import ru.croc.java.school.finaltask.xml.RatioStatistic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;

public class StatisticTest {

    /**
     * Записи.
     */
    private RecordRepository recordRepository;

    private final File testXml = new File("temp.xml");

    /**
     * Инициализация репозитория.
     */
    @BeforeEach
    public void initRepository() throws IOException {
        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        recordRepository = new RecordRepository(dataSourceProvider.getDataSource());
    }

    /**
     * Удаление таблицы.
     */
    @AfterEach
    public void dropTable() throws IOException {
        recordRepository.dropTable();
        Files.deleteIfExists(testXml.toPath());
    }


    /**
     * Проверка вычисления статистики.
     */
    @Test
    @DisplayName("Проверка вычисления статистики")
    public void testCalculate() {

        Record record = new Record(
                "city 17", LocalDate.of(2020, 4, 12),
                2, 8, 4);
        recordRepository.create(record);

        Statistic statistic = new Statistic(recordRepository);

        LocalDate startDate = LocalDate.of(2020, 4, 10);
        LocalDate endDate = LocalDate.of(2020, 4, 20);
        statistic.calculate(startDate, endDate);

        RatioStatistic resultStatistic = new RatioStatistic(startDate, endDate, 4);

        Assertions.assertEquals(resultStatistic, statistic.getRatioStatistic());

    }

    /**
     * Проверка вычисления статистики с несколькими записями.
     */
    @Test
    @DisplayName("Проверка вычисления статистики с несколькими записями")
    public void testCalculateWithSeveralRecords() {

        List<Record> recordList = Arrays.asList(
                new Record("city 17", LocalDate.of(2020, 4, 12),
                        2, 8, 4),
                new Record("city 27", LocalDate.of(2020, 4, 12),
                        1, 7, 4),
                new Record("city 17", LocalDate.of(2020, 4, 14),
                        3, 8, 4));
        recordList.forEach(record -> recordRepository.create(record));

        Statistic statistic = new Statistic(recordRepository);

        LocalDate startDate = LocalDate.of(2020, 4, 10);
        LocalDate endDate = LocalDate.of(2020, 4, 13);
        statistic.calculate(startDate, endDate);

        RatioStatistic resultStatistic = new RatioStatistic(startDate, endDate, 5);

        Assertions.assertEquals(resultStatistic, statistic.getRatioStatistic());

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

        RatioStatistic resultStatistic = new RatioStatistic(startDate, endDate, NaN);

        Assertions.assertEquals(resultStatistic, statistic.getRatioStatistic());
    }

    /**
     * Проверка вычислений статистики при отсутствии новых зараженых.
     */
    @Test
    @DisplayName("Проверка вычислений статистики при отсутствии новых зараженых")
    public void testWithNoInfected() {

        Record record = new Record(
                "city 17", LocalDate.of(2020, 4, 12),
                0, 8, 4);
        recordRepository.create(record);

        Statistic statistic = new Statistic(recordRepository);

        LocalDate startDate = LocalDate.of(2020, 4, 10);
        LocalDate endDate = LocalDate.of(2020, 4, 13);
        statistic.calculate(startDate, endDate);

        RatioStatistic resultStatistic = new RatioStatistic(startDate, endDate, POSITIVE_INFINITY);

        Assertions.assertEquals(resultStatistic, statistic.getRatioStatistic());
    }

    /**
     * Проверка сохраненрия в файл.
     */
    @Test
    @DisplayName("Проверка сохраненрия в файл")
    public void testSaveResultsToFile() throws IOException {

        Record record = new Record(
                "city 17", LocalDate.of(2020, 4, 12),
                2, 8, 4);
        recordRepository.create(record);

        Statistic statistic = new Statistic(recordRepository);

        LocalDate startDate = LocalDate.of(2020, 4, 10);
        LocalDate endDate = LocalDate.of(2020, 4, 20);
        statistic.calculate(startDate, endDate);
        statistic.saveResultsToFile(testXml);

        Assertions.assertTrue(testXml.exists());

        Path path = Paths.get("src/test/resources", "saveTest.xml");
        StringBuilder expectedXmlStringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(s -> expectedXmlStringBuilder.append(s).append(System.lineSeparator()));
        }
        String expectedXml = expectedXmlStringBuilder.toString();

        StringBuilder xmlStringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(testXml.toPath())) {
            stream.forEach(s -> xmlStringBuilder.append(s).append(System.lineSeparator()));
        }
        String xml = xmlStringBuilder.toString();

        Assertions.assertEquals(expectedXml, xml);
    }
}
