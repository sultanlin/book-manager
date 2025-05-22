CREATE TABLE IF NOT EXISTS shelf_book (
    book_id INTEGER NOT NULL REFERENCES book(id),
    shelf_id INTEGER NOT NULL REFERENCES shelf(id) ON DELETE CASCADE,
    PRIMARY KEY(book_id, shelf_id)
);
