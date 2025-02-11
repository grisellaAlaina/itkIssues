### create department
```bash
curl -X POST -H "Content-Type: application/json" -d '{"name": "IT"}' http://localhost:8080/departments
```
### create employee
```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "firstName": "John",
    "lastName": "Doe",
    "position": "Developer",
    "salary": 5000,
    "department": {"id": 1}
}' http://localhost:8080/employees
```
```bash
curl http://localhost:8080/employees
```