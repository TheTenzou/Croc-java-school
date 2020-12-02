package ru.croc.java.school.finaltask;

import ru.croc.java.school.finaltask.model.dto.Record;
import ru.croc.java.school.finaltask.database.repository.RecordRepository;
import ru.croc.java.school.finaltask.model.dto.RatioStatistic;
import ru.croc.java.school.finaltask.xml.converter.JaxbConverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Класс для вычисления статистики.
 */
public class Statistic {
    /**
     * Данные по короновирусной инфекции.
     */
    private RecordRepository recordRepository;

    /**
     * Результаты вычислений статистики.
     */
    private RatioStatistic ratioStatistic;

    /**
     * Конструктор.
     *
     * @param recordRepository репозторий с записями
     */
    public Statistic(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
        this.ratioStatistic = null;
    }

    /**
     * Высчислить статистику за заданный промежуток.
     *
     * @param startDate начало промежутка
     * @param endDate   конец промежутка
     */
    public void calculate(LocalDate startDate, LocalDate endDate) {
        List<Record> recordList = recordRepository.findAll();

        int recoverCount = recordList.stream().
                filter(r -> r.getDate().isAfter(startDate) && r.getDate().isBefore(endDate)).
                mapToInt(Record::getRecoverCount).sum();

        int infected = recordList.stream().
                filter(r -> r.getDate().isAfter(startDate) && r.getDate().isBefore(endDate)).
                mapToInt(Record::getInfectedCount).sum();

        this.ratioStatistic = new RatioStatistic(startDate, endDate, recoverCount / (double) infected);
    }

    /**
     * Сохранение результатов в файл.
     *
     * @param file файл куда будут сохранены данные
     * @throws IOException ошибки при соохранении файла
     */
    public void saveResultsToFile(File file) throws IOException {
        JaxbConverter jaxbConverter = new JaxbConverter();
        String xml = jaxbConverter.toXml(ratioStatistic);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(xml);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public RecordRepository getRecordRepository() {
        return recordRepository;
    }

    public RatioStatistic getRatioStatistic() {
        return ratioStatistic;
    }
}
