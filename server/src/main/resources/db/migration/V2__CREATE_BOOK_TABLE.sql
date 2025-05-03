CREATE TABLE IF NOT EXISTS book (
    id INTEGER PRIMARY KEY,
    title TEXT NOT NULL,
    author TEXT,
    pages INT,
    rating NUMERIC(4, 2),
    ratings_count INTEGER,
    cover TEXT,
    description TEXT,
    release_date DATE, 
    slug TEXT,
    subtitle TEXT
);
