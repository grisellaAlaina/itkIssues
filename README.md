# Примеры тестирования API для управления заказами



**Запрос:**
```bash
curl -X POST -H "Content-Type: application/json" \
-d '{
    "name": "Laptop", 
    "description": "High-end gaming laptop", 
    "price": 1500, 
    "quantityInStock": 10
}' \
http://localhost:8080/api/products
```

```bash
curl -X POST -H "Content-Type: application/json" \
-d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "contactNumber": "+1234567890"
}' \
http://localhost:8080/api/customers

```

```bash
curl -X POST -H "Content-Type: application/json" \
-d '{
    "customer": {"customerId": 1},
    "products": [{"productId": 1}],
    "shippingAddress": "123 Street",
    "totalPrice": 1500
}' \
http://localhost:8080/api/orders
```

```bash
curl http://localhost:8080/api/products
```

```bash
curl http://localhost:8080/api/orders/1
```