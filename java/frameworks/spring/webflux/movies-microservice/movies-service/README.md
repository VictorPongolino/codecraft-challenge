
# Movie Reactive Project

Welcome to the Movie Reactive Project! This project is a study project that uses Java with Reactor WebFlux to create a reactive movie information system.

## Endpoints

### 1. Get All Movie Infos

- **Endpoint:** `GET http://localhost:8080/1/movieinfos`
- **Description:** Retrieve information about all movies.
- **Example Request:**
  ```bash
  curl -X GET http://localhost:8080/1/movieinfos
- **Example Response:**
  ```json
  [
    {
      "id": "1",
      "title": "Movie 1",
      "cast": ["Actor 1", "Actor 2"],
      "year": 2022,
      "release_date": "2022-01-01"
    },
    {
      "id": "2",
      "title": "Movie 2",
      "cast": ["Actor 3", "Actor 4"],
      "year": 2023,
      "release_date": "2023-02-15"
    }
    // ... more movies
  ]

### 2. Get Movie Info by ID

- **Endpoint:** `GET http://localhost:8080/1/movieinfos/{id}`
- **Description:** Retrieve information about a specific movie by its ID.
- **Example Request:**
  ```bash
  curl -X GET http://localhost:8080/1/movieinfos/1
- **Example Response:**
  ```json
  {
    "id": "1",
    "title": "Movie 1",
    "cast": ["Actor 1", "Actor 2"],
    "year": 2022,
    "release_date": "2022-01-01"
  }
### 3. Update Movie Info by ID

- **Endpoint:** `PUT http://localhost:8080/1/movieinfos/{id}`
- **Description:** Update information about a specific movie by its ID.
- **Example Request:**
  ```bash
  curl -X PUT -H "Content-Type: application/json" -d '{"title": "Updated Movie 1", "cast": ["Actor X", "Actor Y"], "year": 2022, "release_date": "2022-03-10"}' http://localhost:8080/1/movieinfos/1
- **Example Response:**
  ```json
  {
    "id": "1",
    "title": "Updated Movie 1",
    "cast": ["Actor X", "Actor Y"],
    "year": 2022,
    "release_date": "2022-03-10"
  }
### 4. Create a New Movie Info

- **Endpoint:** `POST http://localhost:8080/1/movieinfos`
- **Description:** Create a new movie entry.
- **Example Request:**
  ```bash
  curl -X POST -H "Content-Type: application/json" -d '{"title": "New Movie", "cast": ["Actor A", "Actor B"], "year": 2023, "release_date": "2023-05-20"}' http://localhost:8080/1/movieinfos
- **Example Response:**
  ```json
  {
    "id": "3",
    "title": "New Movie",
    "cast": ["Actor A", "Actor B"],
    "year": 2023,
    "release_date": "2023-05-20"
  }
### 5. Delete Movie by ID

- **Endpoint:** `DELETE http://localhost:8080/1/movieinfos/{id}`
- **Description:** Delete a specific movie by its ID.
- **Example Request:**
  ```bash
  curl -X DELETE http://localhost:8080/1/movieinfos/1