package ru.croc.java.school.finaltask.database;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class DataSourceProvider {

    /**
     * DataSource.
     */
    private EmbeddedDataSource dataSource;

    /**
     * Параметры конфигурации из файла application.properties.
     */
    private Map<String, String> properties = new HashMap<>();

    /**
     * Конструктор.
     *
     * @throws IOException ошибка инициализации
     */
    public DataSourceProvider() throws IOException {
        loadProperties();
    }

    /**
     * Метод заагрузки настроек конфигурации в память.
     *
     * @throws IOException ошибка получения настороек
     */
    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Ошибка во время закрузки настороек");
            throw e;
        }
    }

    /**
     * Метод получения экзамляра Datasource'а.
     *
     * @return data source
     */
    public EmbeddedDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new EmbeddedDataSource();
            dataSource.setUser(properties.get("user"));
            dataSource.setPassword(properties.get("password"));
            dataSource.setDatabaseName(properties.get("databasename"));
            dataSource.setCreateDatabase("create");
        }

        return dataSource;
    }
}
