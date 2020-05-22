CREATE TABLE books (
    id bigint IDENTITY(1,1) NOT NULL,
    author [varchar](255) NOT NULL,
    launch_date datetime NOT NULL,
    price decimal NOT NULL,
    title [varchar](255) NOT NULL
    )
