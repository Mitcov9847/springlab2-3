# Лабораторная №2: Spring Boot приложение для управления библиотекой (DAO-реализация)

## Введение

В рамках данной лабораторной работы я разработал RESTful-приложение на основе Spring Boot для управления библиотекой. В проекте реализованы 3 контроллера, 3 сервиса и 5 взаимосвязанных сущностей. Основное внимание уделено архитектурной раздельности, использованию DTO вместо прямой работы с Entity, а также реализации собственного слоя DAO с использованием EntityManager, без использования Spring Data JPA репозиториев.

## Цели работы

- Реализовать полноценное CRUD-приложение для управления библиотечными объектами.
- Использовать EntityManager через собственные DAO-реализации.
- Взаимодействовать с клиентом через DTO.
- Обеспечить тестируемость через Swagger и HTTP-запросы.

## Используемые технологии

- Spring Boot 3.4.4
- Spring Web (REST API)
- Jakarta Persistence (JPA) с EntityManager
- MSSQL Server (база данных)
- Lombok (генерация кода)
- Swagger (springdoc-openapi) — визуализация API
- Maven — система сборки

## Архитектура проекта

### Слои приложения

- **Controller** — REST API интерфейс
- **Service + Impl** — бизнес-логика
- **Repository** — интерфейсы DAO и их реализации (DaoImpl) с EntityManager
- **DTO** — объекты передачи данных
- **Entity** — JPA-сущности и их связи

### Сущности и связи

- **Author**  
  Один автор может написать множество книг (One-to-Many).  
  Связан с Book по `authorId`.

- **Publisher**  
  Один издатель может издать множество книг (One-to-Many).  
  Связан с Book по `publisherId`.

- **Book**  
  Каждая книга имеет одного автора и одного издателя (Many-to-One).  
  Каждая книга может принадлежать нескольким категориям (Many-to-Many).  
  Связи с Author, Publisher, Category.

- **Category**  
  Категория может быть связана с несколькими книгами (Many-to-Many).

- **Library**  
  Представляет библиотеку как набор книг.  
  Использует `@ElementCollection` для хранения списка `bookIds`.

## DTO и маппинг

В процессе разработки я использовал DTO, чтобы отделить внутреннюю модель данных (Entity) от внешнего API. Для каждой сущности созданы соответствующие DTO:

- **AuthorDTO**
- **BookDTO**
- **PublisherDTO**
- **CategoryDTO**
- **LibraryDTO**

Маппинг выполняется вручную в сервисах или DAO.

## Контроллеры и их методы

### AuthorController

- **GET /authors** — получить всех авторов
- **GET /authors/{id}** — получить автора по ID
- **POST /authors** — создать нового автора
- **PUT /authors/{id}** — обновить автора
- **DELETE /authors/{id}** — удалить автора

### BookController

- **GET /books** — получить все книги
- **GET /books/{id}** — получить книгу по ID
- **POST /books** — создать книгу
- **PUT /books/{id}** — обновить книгу
- **DELETE /books/{id}** — удалить книгу

### PublisherController

- **GET /publishers** — получить всех издателей
- **GET /publishers/{id}** — получить издателя по ID
- **POST /publishers** — создать издателя
- **PUT /publishers/{id}** — обновить издателя
- **DELETE /publishers/{id}** — удалить издателя

## Как запустить проект

1. Установил Java 17 и Maven.
2. Настроил базу данных MSSQL и указал параметры в `application.properties`:
   ```properties
   spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=library_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
## Выполнил сборку:
mvn clean install
Запустил приложение:
mvn spring-boot:run
Перешел по адресу http://localhost:8080/swagger-ui.html для взаимодействия с API.

## Пример работы с Swagger
После запуска приложения я перешел на Swagger UI, где увидел визуализированный интерфейс для работы с API. В Swagger доступны все методы, описанные в контроллерах, и возможность отправлять HTTP-запросы.

## Действия в Swagger:
Перешел в раздел /authors, чтобы увидеть доступные методы для работы с авторами. Выбрал метод GET /authors, чтобы получить список авторов.

Попробовал создать нового автора. Выбрал метод POST /authors и заполнил форму:

Проверил созданного автора с помощью метода GET /authors/{id}, где ввел ID нового автора, чтобы получить информацию о нем.

## Скриншот интерфейса Swagger

## Самый важный код
1. Entity Classes (Сущности)
Сущности являются основой для работы с базой данных и описывают структуру данных. Они также служат для отображения объектов в базе данных.

Author.java
java
Копировать
Редактировать
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "author")
    private List<Book> books;
    
    // Getters and Setters
}
Book.java
java
Копировать
Редактировать
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private int year;
    
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    
    @ManyToMany
    @JoinTable(
      name = "book_category", 
      joinColumns = @JoinColumn(name = "book_id"), 
      inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
    
    // Getters and Setters
}
## 2. DTO Classes (Объекты передачи данных)
DTO используется для разделения внутренней модели (Entity) и внешнего API, чтобы минимизировать риски утечек данных.

AuthorDTO.java
java
Копировать
Редактировать
public class AuthorDTO {
    private Long id;
    private String name;
    
    // Getters and Setters
}
BookDTO.java
java
Копировать
Редактировать
public class BookDTO {
    private Long id;
    private String title;
    private int year;
    private Long authorId;
    private Long publisherId;
    private List<Long> categoryIds;
    
    // Getters and Setters
}
## 3. DAO Layer (Слой DAO)
Для работы с базой данных я использовал EntityManager в репозиториях, чтобы избежать использования Spring Data JPA.

AuthorDaoImpl.java
java
Копировать
Редактировать
@Repository
public class AuthorDaoImpl implements AuthorDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    @Override
    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        Author author = findById(id);
        if (author != null) {
            entityManager.remove(author);
        }
    }
}
## 4. Service Layer (Бизнес-логика)
В сервисах выполняется бизнес-логика и маппинг между сущностями и DTO.

AuthorService.java
java
Копировать
Редактировать
@Service
public class AuthorService {

    private final AuthorDao authorDao;
    
    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public AuthorDTO getAuthor(Long id) {
        Author author = authorDao.findById(id);
        return new AuthorDTO(author.getId(), author.getName());
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorDao.findAll();
        return authors.stream()
                      .map(a -> new AuthorDTO(a.getId(), a.getName()))
                      .collect(Collectors.toList());
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        authorDao.save(author);
        return new AuthorDTO(author.getId(), author.getName());
    }


### 5. **Controller Layer (Контроллеры)**

Контроллеры управляют HTTP-запросами и отправляют данные в клиентский интерфейс.

#### AuthorController.java
```java
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}

## Заключение
Данная лабораторная работа позволила мне реализовать полноценное Spring Boot приложение с использованием архитектуры с разделением слоев: контроллеры, сервисы, DAO и DTO. Также была продемонстрирована интеграция с Swagger для тестирования API.
