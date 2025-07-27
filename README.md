# Library
A simple app to track and manage my books.

## Features

* Create shelves
* Search for books
* Add books to shelves

## Screenshots

![Home page.](./screenshots/home.png "Home page.")
![Books in a shelf.](./screenshots/shelf-books.png "Books in a shelf.")
![Book information and metadata.](./screenshots/book.png "Book information and metadata.")
![Settings to manage shelves.](./screenshots/settings.png "Settings to manage shelves.")

## Installation

### Prerequisite
* hardcover api key
    1. You need to have a [hardcover.app](https://hardcover.app/) account.
    2. Go to https://hardcover.app/account/api.
    3. Copy the long code that starts with "Bearer " and save it for later.

* docker


### Docker Installation
The app can only be installed using docker currently

```
services:
  library_server:
    image: sultanlin/library_server:beta
    container_name: library_server
    environment:
      - AUTHORIZATION=$AUTHORIZATION # hardcover api
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=password
      - POSTGRES_HOST=
      - POSTGRES_DB=postgres
    ports:
      - 8080:8080 # Maps port 8080 in the container to port 18080 on the host.
    depends_on:
      - db
    restart: unless-stopped

  library_client:
    image: sultanlin/library_client:beta
    container_name: library_client
    ports:
      - 18000:8080 
    depends_on:
      - library_server
    restart: unless-stopped

  db:
    image: postgres:17-bookworm
    container_name: db
    environment:
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=password
      - PGDATA=/var/lib/postgresql/data/pgdata # Leave it alone
    # volumes:
      # persist database
      # - /database/path/here:/var/lib/postgresql/data
    ports:
      - 5432:5432
    restart: unless-stopped
```


#### Environment variables

**postgres db**

| Env var  | explanation |
| ------------- |:-------------:|
| **POSTGRES_USER**      | Your database's username. |
| **POSTGRES_PASSWORD**      | Your database's password. |

| Volume  | explanation |
| ------------- |:-------------:|
| /database/path/here  | Path to persist/save your database outside of docker (ex. ./database) |

**library_server**

| Env var  | explanation |
| ------------- |:-------------:|
| **AUTHORIZATION**     | Hardcover.app API token |
| **POSTGRES_USER**     | From postgres container. |
| **POSTGRES_DB**      | Same as POSTGRES_USER. |
| **POSTGRES_PASSWORD**      | From postgres container. |
| **POSTGRES_HOST**      | The computer's IP address and postgres container's post. Ex. 192.168.1.44:5432. |
