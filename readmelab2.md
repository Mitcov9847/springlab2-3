Лабораторная №2: Spring Boot приложение для управления библиотекой (DAO-реализация)

Введение

Данный проект представляет собой RESTful-приложение, разработанное на Spring Boot, для управления библиотекой. Он реализует 3 контроллера, 3 сервиса и 5 взаимосвязанных сущностей. Основной упор сделан на архитектурную раздельность, использование DTO вместо прямой работы с Entity, и реализацию собственного слоя DAO на основе EntityManager, без использования Spring Data JPA репозиториев.

Цели работы

Реализовать полноценное CRUD-приложение для управления библиотечными объектами.

Использовать EntityManager через собственные DAO-реализации.

Взаимодействовать с клиентом через DTO.

Обеспечить тестируемость через Swagger и HTTP-запросы.

Используемые технологии

Spring Boot 3.4.4

Spring Web (REST API)

Jakarta Persistence (JPA) с EntityManager

MSSQL Server (база данных)

Lombok (генерация кода)

Swagger (springdoc-openapi) — визуализация API

Maven — система сборки

Архитектура проекта

Слои приложения

Controller — REST API интерфейс

Service + Impl — бизнес-логика

Repository — интерфейсы DAO и их реализации (DaoImpl) с EntityManager

DTO — объекты передачи данных

Entity — JPA-сущности и их связи

Сущности и связи

Author

Один автор может написать множество книг (One-to-Many)

Связан с Book по authorId

Publisher

Один издатель может издать множество книг (One-to-Many)

Связан с Book по publisherId

Book

Каждая книга имеет одного автора и одного издателя (Many-to-One)

Каждая книга может принадлежать нескольким категориям (Many-to-Many)

Связи с Author, Publisher, Category

Category

Категория может быть связана с несколькими книгами (Many-to-Many)

Library

Представляет библиотеку как набор книг

Использует @ElementCollection для хранения списка bookIds

DTO и маппинг

Проект построен на использовании DTO, чтобы отделить внутреннюю модель данных (Entity) от внешнего API. Для каждой сущности создан соответствующий DTO:

AuthorDTO

BookDTO

PublisherDTO

CategoryDTO

LibraryDTO

Маппинг осуществляется вручную в сервисах или DAO.

Контроллеры и их методы

AuthorController

GET /authors — получить всех авторов

GET /authors/{id} — получить автора по ID

POST /authors — создать нового автора

PUT /authors/{id} — обновить автора

DELETE /authors/{id} — удалить автора

BookController

GET /books — получить все книги

GET /books/{id} — получить книгу по ID

POST /books — создать книгу

PUT /books/{id} — обновить книгу

DELETE /books/{id} — удалить книгу

PublisherController

GET /publishers — получить всех издателей

GET /publishers/{id} — получить издателя по ID

POST /publishers — создать издателя

PUT /publishers/{id} — обновить издателя

DELETE /publishers/{id} — удалить издателя

Как запустить проект

Установите Java 17 и Maven

Настройте базу данных MSSQL и укажите параметры в application.properties:

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=library_db
spring.datasource.username=your_username
spring.datasource.password=your_password

Выполните сборку:

mvn clean install

Запустите приложение:

mvn spring-boot:run

Перейдите по адресу http://localhost:8080/swagger-ui.html для взаимодействия с API

Примеры HTTP-запросов

Создание автора (POST /authors)

{
  "name": "Айзек Азимов"
}

Создание книги (POST /books)

{
  "title": "Основание",
  "year": 1951,
  "authorId": 1,
  "publisherId": 1,
  "categoryIds": [1, 2]
}

Заключение

Реализованное приложение демонстрирует использование Spring Boot и JPA с DAO-реализациями и DTO-архитектурой. Оно соответствует всем требованиям: соблюдены связи между сущностями, используется @ElementCollection, поддерживаются CRUD-операции и отделение модели от API. Проект легко масштабируется и может быть расширен для поддержки новых функций, таких как поиск книг, фильтрация по категориям, авторизация и т.д.
