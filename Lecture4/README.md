## Задание 1

Реализовать класс преобразующий xml. На вход поступает xml, в виде строки, содержащая список фильмов, актеров каждого фильма и их роли. Необходимо получить на выходе xml, в виде строки, содержащую список актеров, фильмы в которых они участвовали и роли. Пример xml ниже:

```xml
<films>
  <film>
    <title>Фильм 1</title>
    <description>Описание 1</description>
    <actors>
      <actor name="Актер 1" age="30" role="Роль 1"/>
      <actor name="Актер 2" age="23" role="Роль 2"/>
      <actor name="Актер 3" age="40" role="Роль 3"/>
    </actors>
  </film>
  <film>
    <title>Фильм 2</title>
    <description>Описание 2</description>
    <actors>
      <actor name="Актер 4" age="70" role="Роль 4"/>
      <actor name="Актер 2" age="23" role="Роль 5"/>
      <actor name="Актер 3" age="40" role="Роль 6"/>
    </actors>
  </film>
</films>

<actors>
  <actor>
    <name>Актер 1</name>
    <age>30</age>
    <films>
      <film title="Фильм 1" role="Роль 1"/>
    </films>
  </actor>
  <actor>
    <name>Актер 2</name>
    <age>23</age>
    <films>
      <film title="Фильм 1" role="Роль 2"/>
      <film title="Фильм 2" role="Роль 5"/>
    </films>
  </actor>
  <actor>
    <name>Актер 3</name>
    <age>40</age>
    <films>
      <film title="Фильм 1" role="Роль 3"/>
      <film title="Фильм 2" role="Роль 6"/>
    </films>
  </actor>
  <actor>
    <name>Актер 4</name>
    <age>70</age>
    <films>
      <film title="Фильм 2" role="Роль 4"/>
    </films>
  </actor>
</actors>
```

## Задание 2

Создать собственные аннотации `@MapKeyFail` и `@MapValueFail`. Создать runtime исключения
PutMapKeyFailException и PutMapValueFailException. Расширить класс `HashMap<Object, Object>`
таким образом, что:

1. если в метод put поступает ключ отмеченный аннотацией `@MapKeyFail` выбрасывается
   `PutMapKeyFailException`.
2. если в метод put поступает значение отмеченное аннотацией `@MapValueFail` выбрасывается
   `PutMapValueFailException`.
