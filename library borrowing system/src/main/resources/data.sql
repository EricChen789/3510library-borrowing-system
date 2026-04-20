INSERT INTO users (email, password, full_name, is_admin) VALUES
                                                             ('admin@library.com', 'admin123', 'Administrator', TRUE),
                                                             ('john@example.com', 'password123', 'John Doe', FALSE),
                                                             ('jane@example.com', 'password123', 'Jane Smith', FALSE),
                                                             ('alice@example.com', 'password123', 'Alice Johnson', FALSE),
                                                             ('bob@example.com', 'password123', 'Bob Williams', FALSE);


INSERT INTO books (title, author, isbn, description, image_url, available_copies, total_copies) VALUES
                                                                                                    ('The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 'A story of the mysteriously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan.', 'https://covers.openlibrary.org/b/isbn/9780743273565-M.jpg', 4, 4),
                                                                                                    ('Pride and Prejudice', 'Jane Austen', '9780141439518', 'A classic romantic novel that charts the emotional development of protagonist Elizabeth Bennet.', 'https://covers.openlibrary.org/b/isbn/9780141439518-M.jpg', 5, 5),
                                                                                                    ('War and Peace', 'Leo Tolstoy', '9780140447934', 'Epic novel chronicling the French invasion of Russia and its impact on Tsarist society.', 'https://covers.openlibrary.org/b/isbn/9780140447934-M.jpg', 3, 3),
                                                                                                    ('Moby-Dick', 'Herman Melville', '9780142437247', 'The saga of Captain Ahab''s obsessive quest to kill the white whale, Moby Dick.', 'https://covers.openlibrary.org/b/isbn/9780142437247-M.jpg', 6, 6),
                                                                                                    ('Crime and Punishment', 'Fyodor Dostoevsky', '9780143058144', 'A man''s murderous plan leads to psychological torment and moral dilemmas.', 'https://covers.openlibrary.org/b/isbn/9780143058144-M.jpg', 4, 4),
                                                                                                    ('Les Misérables', 'Victor Hugo', '9780451521575', 'A story of redemption, revolution, and the struggle for justice in 19th-century France.', 'https://covers.openlibrary.org/b/isbn/9780451521575-M.jpg', 5, 5),
                                                                                                    ('Jane Eyre', 'Charlotte Brontë', '9780141441146', 'An orphaned girl''s journey through hardship, love, and self-discovery.', 'https://covers.openlibrary.org/b/isbn/9780141441146-M.jpg', 3, 3),
                                                                                                    ('Wuthering Heights', 'Emily Brontë', '9781853260018', 'A tale of passionate and destructive love on the Yorkshire moors.', 'https://covers.openlibrary.org/b/isbn/9781853260018-M.jpg', 5, 5),
                                                                                                    ('Don Quixote', 'Miguel de Cervantes', '9780142437239', 'The adventures of an aging gentleman who sets out to revive chivalry.', 'https://covers.openlibrary.org/b/isbn/9780142437239-M.jpg', 6, 6),
                                                                                                    ('The Odyssey', 'Homer', '9780140268867', 'The epic journey of Odysseus as he attempts to return home after the Trojan War.', 'https://covers.openlibrary.org/b/isbn/9780140268867-M.jpg', 4, 4),
                                                                                                    ('The Iliad', 'Homer', '9780140275363', 'The story of the Trojan War and the wrath of Achilles.', 'https://covers.openlibrary.org/b/isbn/9780140275363-M.jpg', 3, 3),
                                                                                                    ('Anna Karenina', 'Leo Tolstoy', '9780143035008', 'A tragic story of love, betrayal, and social conventions in Imperial Russia.', 'https://covers.openlibrary.org/b/isbn/9780143035008-M.jpg', 5, 5),
                                                                                                    ('The Brothers Karamazov', 'Fyodor Dostoevsky', '9780374528379', 'A philosophical novel about faith, reason, free will, and moral responsibility.', 'https://covers.openlibrary.org/b/isbn/9780374528379-M.jpg', 4, 4),
                                                                                                    ('Madame Bovary', 'Gustave Flaubert', '9780140449129', 'The story of a doctor''s wife who seeks escape from her mundane life through affairs.', 'https://covers.openlibrary.org/b/isbn/9780140449129-M.jpg', 6, 6),
                                                                                                    ('One Hundred Years of Solitude', 'Gabriel García Márquez', '9780060883287', 'The multi-generational story of the Buendía family in the fictional town of Macondo.', 'https://covers.openlibrary.org/b/isbn/9780060883287-M.jpg', 3, 3),
                                                                                                    ('The Old Man and the Sea', 'Ernest Hemingway', '9780684801223', 'An old fisherman''s epic struggle with a giant marlin in the Gulf Stream.', 'https://covers.openlibrary.org/b/isbn/9780684801223-M.jpg', 5, 5),
                                                                                                    ('The Picture of Dorian Gray', 'Oscar Wilde', '9780141439570', 'A young man sells his soul for eternal youth and beauty.', 'https://covers.openlibrary.org/b/isbn/9780141439570-M.jpg', 4, 4),
                                                                                                    ('Frankenstein', 'Mary Shelley', '9780141439471', 'A scientist creates a living being and is horrified by his creation.', 'https://covers.openlibrary.org/b/isbn/9780141439471-M.jpg', 5, 5),
                                                                                                    ('Dracula', 'Bram Stoker', '9780141439846', 'The classic vampire tale of Count Dracula''s attempt to move from Transylvania to England.', 'https://covers.openlibrary.org/b/isbn/9780141439846-M.jpg', 3, 3),
                                                                                                    ('The Count of Monte Cristo', 'Alexandre Dumas', '9780140449264', 'A story of revenge, justice, and redemption set in post-Napoleonic France.', 'https://covers.openlibrary.org/b/isbn/9780140449264-M.jpg', 6, 6);


INSERT INTO borrow_records (user_id, book_id, borrow_date, due_date, returned) VALUES
                                                                                   (2, 1, DATEADD('DAY', -5, CURRENT_TIMESTAMP), DATEADD('DAY', 9, CURRENT_TIMESTAMP), FALSE),
                                                                                   (2, 3, DATEADD('DAY', -10, CURRENT_TIMESTAMP), DATEADD('DAY', 4, CURRENT_TIMESTAMP), FALSE),
                                                                                   (2, 5, DATEADD('DAY', -2, CURRENT_TIMESTAMP), DATEADD('DAY', 12, CURRENT_TIMESTAMP), FALSE);

INSERT INTO borrow_records (user_id, book_id, borrow_date, due_date, returned) VALUES
                                                                                   (3, 2, DATEADD('DAY', -7, CURRENT_TIMESTAMP), DATEADD('DAY', 7, CURRENT_TIMESTAMP), FALSE),
                                                                                   (3, 7, DATEADD('DAY', -12, CURRENT_TIMESTAMP), DATEADD('DAY', 2, CURRENT_TIMESTAMP), FALSE);

INSERT INTO borrow_records (user_id, book_id, borrow_date, return_date, due_date, returned) VALUES
                                                                                                (2, 4, DATEADD('DAY', -30, CURRENT_TIMESTAMP), DATEADD('DAY', -16, CURRENT_TIMESTAMP), DATEADD('DAY', -16, CURRENT_TIMESTAMP), TRUE),
                                                                                                (2, 6, DATEADD('DAY', -25, CURRENT_TIMESTAMP), DATEADD('DAY', -11, CURRENT_TIMESTAMP), DATEADD('DAY', -11, CURRENT_TIMESTAMP), TRUE),
                                                                                                (3, 1, DATEADD('DAY', -20, CURRENT_TIMESTAMP), DATEADD('DAY', -6, CURRENT_TIMESTAMP), DATEADD('DAY', -6, CURRENT_TIMESTAMP), TRUE),
                                                                                                (3, 4, DATEADD('DAY', -15, CURRENT_TIMESTAMP), DATEADD('DAY', -1, CURRENT_TIMESTAMP), DATEADD('DAY', -1, CURRENT_TIMESTAMP), TRUE),
                                                                                                (4, 2, DATEADD('DAY', -18, CURRENT_TIMESTAMP), DATEADD('DAY', -4, CURRENT_TIMESTAMP), DATEADD('DAY', -4, CURRENT_TIMESTAMP), TRUE),
                                                                                                (4, 8, DATEADD('DAY', -22, CURRENT_TIMESTAMP), DATEADD('DAY', -8, CURRENT_TIMESTAMP), DATEADD('DAY', -8, CURRENT_TIMESTAMP), TRUE);


INSERT INTO saved_books (user_id, book_id) VALUES
                                               (2, 5),
                                               (2, 6),
                                               (2, 9),
                                               (2, 12);

INSERT INTO saved_books (user_id, book_id) VALUES
                                               (3, 1),
                                               (3, 2),
                                               (3, 8),
                                               (3, 10),
                                               (3, 15);

INSERT INTO saved_books (user_id, book_id) VALUES
                                               (4, 3),
                                               (4, 7),
                                               (4, 14);

INSERT INTO saved_books (user_id, book_id) VALUES
                                               (5, 11),
                                               (5, 13),
                                               (5, 16),
                                               (5, 18);


SELECT 'Users Count: ' || COUNT(*) AS Result FROM users;

SELECT 'Books Count: ' || COUNT(*) AS Result FROM books;

SELECT 'Active Borrows: ' || COUNT(*) AS Result FROM borrow_records WHERE returned = FALSE;

SELECT 'Saved Books: ' || COUNT(*) AS Result FROM saved_books;