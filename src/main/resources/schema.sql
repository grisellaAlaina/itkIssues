CREATE TABLE author (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        first_name VARCHAR(255) NOT NULL,
                        last_name VARCHAR(255) NOT NULL
);

CREATE TABLE book (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(255) NOT NULL,
                      isbn VARCHAR(255) NOT NULL,
                      publication_year INT NOT NULL,
                      author_id BIGINT,
                      FOREIGN KEY (author_id) REFERENCES author(id)
);