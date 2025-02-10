

## 1. Пример ответа API с пагинацией

Пример запроса:

```bash
curl -X GET "http://localhost:8080/api/books?page=0&size=3&sort=title,asc"