DROP TABLE IF EXISTS saved_books;
DROP TABLE IF EXISTS borrow_records;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(255),
                       is_admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE books (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       isbn VARCHAR(255),
                       description VARCHAR(255),
                       image_url VARCHAR(255),
                       available_copies INTEGER NOT NULL,
                       total_copies INTEGER NOT NULL
);

CREATE TABLE borrow_records (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                book_id BIGINT NOT NULL,
                                borrow_date TIMESTAMP NOT NULL,
                                return_date TIMESTAMP,
                                due_date TIMESTAMP NOT NULL,
                                returned BOOLEAN DEFAULT FALSE
);

CREATE TABLE saved_books (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             user_id BIGINT NOT NULL,
                             book_id BIGINT NOT NULL
);

ALTER TABLE borrow_records ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE borrow_records ADD FOREIGN KEY (book_id) REFERENCES books(id);
ALTER TABLE saved_books ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE saved_books ADD FOREIGN KEY (book_id) REFERENCES books(id);

ALTER TABLE saved_books ADD CONSTRAINT unique_user_book UNIQUE(user_id, book_id);