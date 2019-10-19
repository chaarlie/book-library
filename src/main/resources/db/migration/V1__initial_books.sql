CREATE TABLE book (
                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(45) NOT NULL
);

INSERT INTO book VALUES
(0, 'El Coronel No Tiene Quien Le Escriba'),
(0, 'Crimen y Castigo');

CREATE TABLE book_page (
                           id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           content text NOT NULL,
                           book_id INT,
                           FOREIGN KEY (book_id) REFERENCES book(id)
);

INSERT INTO book_page VALUES
(0, 'Lorem Ipsum Dolor... ', 1),
(0, 'Dolor Ipsum Lorem...', 2);