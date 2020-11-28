package ru.croc.java.school.finaltask.xml;

import ru.croc.java.school.finaltask.xml.converter.DateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Статистика по корона фирусной инфекции за задынный промкжуток.
 */
@XmlRootElement(name = "statistic")
@XmlType(propOrder = {"startDate", "endDate", "ratio"})
public class ResultStatistic {

    /**
     * Начало временного промежутка.
     */
    @XmlElement(name = "start-date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate startDate;

    /**
     * Конец временного промежутка.
     */
    @XmlElement(name = "end-date")
    @XmlJavaTypeAdapter(DateAdapter.class)
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

    /**
     * Пустой конструктор.
     */
    public ResultStatistic() {
        this(LocalDate.now(), LocalDate.now(), 1);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultStatistic that = (ResultStatistic) o;
        return Double.compare(that.ratio, ratio) == 0 &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, ratio);
    }
}
