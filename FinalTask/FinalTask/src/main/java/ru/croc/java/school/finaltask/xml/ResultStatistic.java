package ru.croc.java.school.finaltask.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * Статистика по корона фирусной инфекции за задынный промкжуток.
 */
@XmlRootElement(name = "statistic")
public class ResultStatistic {

    /**
     * Начало временного промежутка.
     */
    @XmlElement(name = "start-date")
    private LocalDate startDate;

    /**
     * Конец временного промежутка.
     */
    @XmlElement(name = "end-date")
    private LocalDate endDate;

    /**
     * Соотношение между выписаными поциентами и новыми выявлеными случаями.
     */
    @XmlElement
    private double ratio;

    /**
     * Конструктор.
     *
     * @param startDate начало временного промежутка
     * @param endDate   конец временного промежутка
     * @param ratio     соотношение между выписаными поциентами и новыми выявлеными случаями
     */
    public ResultStatistic(LocalDate startDate, LocalDate endDate, double ratio) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.ratio = ratio;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getRatio() {
        return ratio;
    }
}
