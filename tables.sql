-- Table: reader_details
CREATE TABLE reader_details (
    reader_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    address VARCHAR(100),
    phone_number VARCHAR(15),
    email VARCHAR(50)
);

-- Table: book_details
CREATE TABLE book_details (
    book_id INT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(50),
    genre VARCHAR(50),
    publication_year INT,
    available_quantity INT
);

-- Table: issue_details
CREATE TABLE issue_details (
    issue_id INT PRIMARY KEY,
    reader_id INT,
    book_id INT,
    issue_date DATE,
    due_date DATE,
    FOREIGN KEY (reader_id) REFERENCES reader_details(reader_id),
    FOREIGN KEY (book_id) REFERENCES book_details(book_id)
);
