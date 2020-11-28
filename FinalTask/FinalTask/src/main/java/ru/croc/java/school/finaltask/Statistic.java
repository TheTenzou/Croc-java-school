package ru.croc.java.school.finaltask;

import ru.croc.java.school.finaltask.model.Record;
import ru.croc.java.school.finaltask.repository.RecordRepository;
import ru.croc.java.school.finaltask.xml.ResultStatistic;
import ru.croc.java.school.finaltask.xml.converter.JaxbConverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Statistic {
    /**
     * Данные по короновирусной инфекции.
     */
    private RecordRepository recordRepository;

    /**
     * Результаты вычислений статистики.
     */
    private ResultStatistic resultStatistic;

    /**
     * Конструктор.
     *
     * @param recordRepository репозторий с записями
     */
    public Statistic(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
        this.resultStatistic = null;
    }

    /**
     * Высчислить статистику за заданный промежуток.
     *
     * @param startDate начало промежутка
     * @param endDate   конец промежутка
     */
    public void calculate(LocalDate startDate, LocalDate endDate) {
        List<Record> recordList = recordRepository.findAll();
//        List<Record> filteredList = recordList.stream().
//                filter(r -> r.getDate().isAfter(startDate) && r.getDate().isBefore(endDate)).
//                collect(Collectors.toList());

        int recoverCount = recordList.stream().
                filter(r -> r.getDate().isAfter(startDate) && r.getDate().isBefore(endDate)).
                mapToInt(Record::getRecoverCount).sum();

        int sick = recordList.stream().
                filter(r -> r.getDate().isAfter(startDate) && r.getDate().isBefore(endDate)).
                mapToInt(Record::getSickCount).sum();

        this.resultStatistic = new ResultStatistic(startDate, endDate, recoverCount / (double) sick);
    }

    /**
     * Сохранение результатов в файл.
     *
     * @param file файл куда будут сохранены данные
     * @throws IOException ошибки при соохранении файла
     */
    public void saveResultsToFile(File file) throws IOException {
        if (resultStatistic != null) {
            JaxbConverter jaxbConverter = new JaxbConverter();
            String xml = jaxbConverter.toXml(resultStatistic);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        }
    }

    public RecordRepository getRecordRepository() {
        return recordRepository;
    }

    public ResultStatistic getResultStatistic() {
        return resultStatistic;
    }
}
