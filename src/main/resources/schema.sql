-- Создание таблицы Department
CREATE TABLE Department (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

-- Создание таблицы Employee
CREATE TABLE Employee (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          first_name VARCHAR(255) NOT NULL,
                          last_name VARCHAR(255) NOT NULL,
                          position VARCHAR(255) NOT NULL,
                          salary DOUBLE NOT NULL,
                          department_id BIGINT,
                          FOREIGN KEY (department_id) REFERENCES Department(id)
);