# Pet Management System

Система управления питомцами с REST API и JavaFX клиентом.

## Структура проекта

Проект состоит из двух частей:

1. **Spring Boot REST API** - серверная часть с REST контроллерами
2. **JavaFX Client** - графический интерфейс для управления данными

## Требования

- Java 17+
- Maven 3.6+
- MySQL 8.0+

## Запуск Spring Boot REST API

### 1. Настройка базы данных

Создайте базу данных MySQL:

```sql
CREATE DATABASE petdb;
```

### 2. Настройте application.properties

Отредактируйте `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/petdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

### 3. Запуск API

```bash
mvn clean install
mvn spring-boot:run
```

REST API будет доступен по адресу: `http://localhost:8080/api/pets`

## REST API эндпоинты

| Метод | URL | Описание |
|--------|--------------------------|-------------------------|
| GET    | `/api/pets`              | Получить всех питомцев |
| GET    | `/api/pets/{id}`         | Получить питомца по ID |
| POST   | `/api/pets`              | Создать нового питомца |
| PUT    | `/api/pets/{id}`         | Обновить данные питомца |
| DELETE | `/api/pets/{id}`         | Удалить питомца |

## Запуск JavaFX клиента

Подробную инструкцию смотрите в [javafx-client/README.md](javafx-client/README.md)

```bash
cd javafx-client
mvn clean install
mvn javafx:run
```

## Функционал JavaFX клиента

- ✅ Просмотр всех питомцев в таблице
- ✅ Добавление новых питомцев
- ✅ Редактирование данных о питомцах
- ✅ Удаление питомцев
- ✅ Обновление списка из базы данных

## Технологии

### Backend
- Spring Boot 3.1.5
- Spring Data JPA
- MySQL
- Lombok

### Frontend
- JavaFX 21
- OkHttp 4.12
- Gson 2.10

## Автор

magog-1