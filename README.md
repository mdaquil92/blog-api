# Blog REST API

A production-ready Blog REST API built with **Spring Boot 3**, **Spring Security**, **JWT Authentication**, **MySQL**, and **Swagger UI** documentation.

---

## Tech Stack

| Technology          | Purpose                        |
|---------------------|--------------------------------|
| Spring Boot 3.2     | Core framework                 |
| Spring Security     | Authentication & Authorization |
| JWT (jjwt 0.11.5)  | Stateless token auth           |
| Spring Data JPA     | Database ORM                   |
| MySQL               | Relational database            |
| Hibernate           | JPA implementation             |
| ModelMapper         | DTO ↔ Entity mapping           |
| Lombok              | Boilerplate reduction          |
| SpringDoc OpenAPI   | Swagger UI documentation       |
| Maven               | Build tool                     |

---

## Project Structure

```
src/main/java/com/blogapi/
├── BlogApiApplication.java       # Entry point
├── config/
│   ├── AppConfig.java            # ModelMapper bean
│   └── SecurityConfig.java       # Spring Security + JWT setup
├── controller/
│   ├── AuthController.java       # /api/auth/login, /register
│   ├── PostController.java       # /api/posts
│   ├── CommentController.java    # /api/posts/{id}/comments
│   └── CategoryController.java  # /api/categories
├── dto/
│   ├── PostDto.java
│   ├── CommentDto.java
│   ├── CategoryDto.java
│   ├── LoginDto.java
│   ├── RegisterDto.java
│   ├── JwtAuthResponse.java
│   └── PostResponse.java         # Paginated response wrapper
├── entity/
│   ├── User.java
│   ├── Role.java
│   ├── Post.java
│   ├── Comment.java
│   └── Category.java
├── exception/
│   ├── ResourceNotFoundException.java
│   ├── BlogApiException.java
│   └── GlobalExceptionHandler.java  # @ControllerAdvice
├── repository/
│   ├── UserRepository.java
│   ├── RoleRepository.java
│   ├── PostRepository.java
│   ├── CommentRepository.java
│   └── CategoryRepository.java
├── security/
│   ├── JwtTokenProvider.java         # Generate & validate JWT
│   ├── JwtAuthenticationFilter.java  # Intercept every request
│   └── CustomUserDetailsService.java # Load user from DB
├── service/
│   ├── AuthService.java / AuthServiceImpl.java
│   ├── PostService.java / PostServiceImpl.java
│   ├── CommentService.java / CommentServiceImpl.java
│   └── CategoryService.java / CategoryServiceImpl.java
└── utils/
    └── AppConstants.java             # Pagination defaults
```

---

## Getting Started

### Prerequisites
- Java 17+
- MySQL 8+
- Maven 3.8+

### 1. Clone the repository
```bash
git clone https://github.com/your-username/blog-api.git
cd blog-api
```

### 2. Create the MySQL database
```sql
CREATE DATABASE blog_db;
```

### 3. Insert default roles
```sql
USE blog_db;
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
```

### 4. Configure application.properties
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
app.jwt.secret=YOUR_SECRET_KEY_MIN_32_CHARS
```

### 5. Run the application
```bash
mvn spring-boot:run
```

The API will start at `http://localhost:8080`

---

## API Endpoints

### Auth
| Method | Endpoint            | Description         | Auth Required |
|--------|---------------------|---------------------|---------------|
| POST   | /api/auth/register  | Register new user   | No            |
| POST   | /api/auth/login     | Login & get token   | No            |

### Posts
| Method | Endpoint          | Description                      | Auth Required |
|--------|-------------------|----------------------------------|---------------|
| GET    | /api/posts        | Get all posts (paginated)        | No            |
| GET    | /api/posts/{id}   | Get post by ID                   | No            |
| POST   | /api/posts        | Create a post                    | Admin only    |
| PUT    | /api/posts/{id}   | Update a post                    | Admin only    |
| DELETE | /api/posts/{id}   | Delete a post                    | Admin only    |

### Comments
| Method | Endpoint                              | Description             | Auth Required |
|--------|---------------------------------------|-------------------------|---------------|
| GET    | /api/posts/{postId}/comments          | Get all comments        | No            |
| GET    | /api/posts/{postId}/comments/{id}     | Get comment by ID       | No            |
| POST   | /api/posts/{postId}/comments          | Add a comment           | Yes           |
| PUT    | /api/posts/{postId}/comments/{id}     | Update a comment        | Yes           |
| DELETE | /api/posts/{postId}/comments/{id}     | Delete a comment        | Yes           |

### Categories
| Method | Endpoint              | Description             | Auth Required |
|--------|-----------------------|-------------------------|---------------|
| GET    | /api/categories       | Get all categories      | No            |
| GET    | /api/categories/{id}  | Get category by ID      | No            |
| POST   | /api/categories       | Create a category       | Admin only    |
| PUT    | /api/categories/{id}  | Update a category       | Admin only    |
| DELETE | /api/categories/{id}  | Delete a category       | Admin only    |

---

## Authentication Flow

1. **Register** → `POST /api/auth/register` with name, username, email, password
2. **Login** → `POST /api/auth/login` → receive JWT token
3. **Use token** → Add header `Authorization: Bearer <your_token>` to protected requests

---

## Swagger UI

Once the app is running, visit:
```
http://localhost:8080/swagger-ui.html
```
Click "Authorize" → paste your Bearer token → test all endpoints interactively.

---

## Key Concepts Demonstrated

- **Layered Architecture** — Controller → Service → Repository
- **JWT Stateless Authentication** — No sessions, fully REST-compliant
- **Role-Based Access Control** — ROLE_USER and ROLE_ADMIN
- **Global Exception Handling** — `@ControllerAdvice` with structured error responses
- **Input Validation** — `@Valid`, `@NotEmpty`, `@Size`, `@Email`
- **Pagination & Sorting** — Spring Data `Pageable`
- **DTO Pattern** — Entities never exposed directly
- **OpenAPI Documentation** — Auto-generated Swagger UI

---

## Sample Request — Register

```json
POST /api/auth/register
{
  "name": "John Doe",
  "username": "johndoe",
  "email": "john@example.com",
  "password": "password123"
}
```

## Sample Request — Create Post

```json
POST /api/posts
Authorization: Bearer <token>

{
  "title": "My First Blog Post",
  "description": "An introduction to Spring Boot",
  "content": "Spring Boot makes it easy to create stand-alone, production-grade Spring applications.",
  "categoryId": 1
}
```
