### 1. Создание новой книги (`POST /api/books`)

```bash
curl -X POST http://localhost:8080/api/books \
-H "Content-Type: application/json" \
-d '{
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "publicationYear": 2008
}'
```
### 2. Получение всех книг (`GET /api/books`)

```bash
curl -X GET http://localhost:8080/api/books
```

### 3. Получение книги по ID (`GET /api/books/{id}`)

```bash
curl -X GET http://localhost:8080/api/books/1
```
### 4. Обновление книги (`PUT /api/books/{id}`)

```bash
curl -X PUT http://localhost:8080/api/books/1 \
-H "Content-Type: application/json" \
-d '{
    "title": "Refactoring: Improving the Design of Existing Code",
    "author": "Martin Fowler",
    "publicationYear": 1999
}'
```
### 5. Удаление книги (`DELETE /api/books/{id}`)

```bash
curl -X DELETE http://localhost:8080/api/books/1
```