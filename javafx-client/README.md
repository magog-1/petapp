# Pet Management JavaFX Client

Графический клиент для управления питомцами на JavaFX.

## Требования

- Java 17+
- Maven 3.6+
- Запущенный Spring Boot REST API на `http://localhost:8080`

## Запуск приложения

1. Перейдите в каталог проекта:
```bash
cd javafx-client
```

2. Соберите проект:
```bash
mvn clean install
```

3. Запустите приложение:
```bash
mvn javafx:run
```

## Возможности

- Просмотр всех питомцев в таблице
- Добавление новых питомцев
- Редактирование данных о питомцах
- Удаление питомцев
- Обновление списка из базы данных

## Структура проекта

```
javafx-client/
├── src/main/
│   ├── java/com/petapp/client/
│   │   ├── PetApplication.java       # Главный класс приложения
│   │   ├── model/Pet.java            # Модель данных
│   │   ├── service/PetApiService.java # Сервис для REST запросов
│   │   └── controller/MainController.java # Контроллер UI
│   └── resources/fxml/main.fxml   # FXML разметка
└── pom.xml
```