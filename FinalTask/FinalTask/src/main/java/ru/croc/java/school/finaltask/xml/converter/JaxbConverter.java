package ru.croc.java.school.finaltask.xml.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;

/**
 * Сериализация и десириализиция объектов.
 */
public class JaxbConverter {
    /**
     * Переобразует объект в xml.
     *
     * @param obj объект
     * @return строкове представлени xml
     */
    public static String toXml(Object obj) throws JsonProcessingException {
        XmlMapper mapper = createXmlMapper();
        return mapper.writeValueAsString(obj);
    }

    /**
     * Преобразует xml в заданый объект.
     *
     * @param xml  xml строка
     * @param type тип к которому необходимо преобразовать
     * @return объект
     */
    public static <T> T fromXml(String xml, Class<T> type) throws IOException {
        XmlMapper mapper = createXmlMapper();
        return mapper.readValue(xml, type);
    }

    /**
     * Метод создание XmlMapper'a.
     *
     * @return XmlMapper
     */
    private static XmlMapper createXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        mapper.registerModule(module);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}
