
### Этот документ описывает, как выполнять запросы с помощью `cURL` для аутентификации и доступа к защищённым ресурсам вашего Spring Boot приложения с использованием JWT.


## Предварительные требования

Перед началом убедитесь, что:

- Ваше приложение запущено и доступно по адресу, например, `http://localhost:8080`.
- У вас есть созданный пользователь в базе данных с корректным `username` и `password`.

*Примечание:* В нашем примере мы используем следующие пользователи:

| Username | Password | Роль          |
|----------|----------|---------------|
| ivan     | sa       | USER          |
| moder    | sa       | MODERATOR     |
| admin    | sa       | SUPER_ADMIN   |

## Аутентификация и получение JWT токена

Для доступа к защищённым эндпоинтам необходимо сначала аутентифицироваться и получить JWT токен.

### Шаг 1: Отправка запроса на аутентификацию

Используйте следующую команду `cURL` для отправки POST-запроса на эндпоинт `/auth/login` с вашими учётными данными.


```bash
curl -X POST -k https://localhost:8443/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username":"ivan","password":"sa"}'
```

### Шаг 2: вставьте полученный jwt
```bash
curl -X GET -k https://localhost:8443/user \
     -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJzdWIiOiJpdmFuIiwiaWF0IjoxNzM5OTAxNzIxLCJleHAiOjE3Mzk5ODgxMjF9.xMFoBvPEj6IEoKH9gtS5er5fW1hr44d3psZyb1lnv7A"
```
### ожидаемый ответ  USER endpoint