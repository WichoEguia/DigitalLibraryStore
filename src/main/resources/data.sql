-- Insert books
INSERT INTO books (isbn, title, description, image_url, format, publish_date, pages_no, popularity)
VALUES
('978-3-16-148410-0', 'Book One', 'Description of Book One', 'http://example.com/image1.jpg', 'Paperback', '2020-01-15', 250, 7.5),
('978-1-23-456789-7', 'Book Two', 'Description of Book Two', 'http://example.com/image2.jpg', 'Hardcover', '2019-05-22', 300, 8.2),
('978-0-12-345678-9', 'Book Three', 'Description of Book Three', 'http://example.com/image3.jpg', 'Ebook', '2021-08-10', 150, 6.0),
('978-1-98-765432-1', 'Book Four', 'Description of Book Four', 'http://example.com/image4.jpg', 'Paperback', '2018-12-05', 400, 9.1),
('978-0-11-223344-5', 'Book Five', 'Description of Book Five', 'http://example.com/image5.jpg', 'Hardcover', '2022-03-01', 180, 7.0),
('978-0-12-987654-3', 'Book Six', 'Description of Book Six', 'http://example.com/image6.jpg', 'Paperback', '2020-11-25', 220, 5.4),
('978-1-23-456789-8', 'Book Seven', 'Description of Book Seven', 'http://example.com/image7.jpg', 'Ebook', '2017-02-14', 310, 8.0),
('978-0-11-335577-2', 'Book Eight', 'Description of Book Eight', 'http://example.com/image8.jpg', 'Hardcover', '2023-06-18', 270, 7.2),
('978-1-23-123456-1', 'Book Nine', 'Description of Book Nine', 'http://example.com/image9.jpg', 'Paperback', '2016-07-09', 350, 6.5),
('978-0-12-223344-6', 'Book Ten', 'Description of Book Ten', 'http://example.com/image10.jpg', 'Hardcover', '2015-05-12', 280, 9.0);

-- Insert users
INSERT INTO users (name, email, password, enabled, creation_date)
VALUES
('Alice Johnson', 'alice@example.com', 'password123', true, '2021-01-15T10:00:00'),
('Bob Smith', 'bob@example.com', 'password456', true, '2022-05-10T14:30:00'),
('Charlie Brown', 'charlie@example.com', 'password789', false, '2023-07-23T08:45:00');

-- Insert loans (Note: one user, Charlie Brown, has no loans)
INSERT INTO loans (user_id, book_id, loan_date, devolution_date)
VALUES
(1, 1, '2023-11-01T10:00:00', '2023-11-15T10:00:00'),
(1, 2, '2023-11-02T11:00:00', '2023-11-16T11:00:00'),
(2, 3, '2023-10-12T12:30:00', '2023-10-26T12:30:00'),
(2, 4, '2023-10-15T14:00:00', '2023-10-29T14:00:00');
