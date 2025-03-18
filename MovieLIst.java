class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next, prev;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieList {
    private Movie head, tail;

    public MovieList() {
        head = tail = null;
    }

    // Insert at the beginning
    public void insertAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    // Insert at the end
    public void insertAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    // Insert at a specific position
    public void insertAtPosition(String title, String director, int year, double rating, int position) {
        if (position <= 0) {
            insertAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        for (int i = 1; temp != null && i < position; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            insertAtEnd(title, director, year, rating);
            return;
        }
        newMovie.next = temp.next;
        newMovie.prev = temp;
        if (temp.next != null) {
            temp.next.prev = newMovie;
        }
        temp.next = newMovie;
        if (newMovie.next == null) {
            tail = newMovie;
        }
    }

    // Remove movie by title
    public void removeMovie(String title) {
        if (head == null) {
            System.out.println("No movies to remove!");
            return;
        }
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Movie not found!");
            return;
        }
        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = temp.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        System.out.println("Movie '" + title + "' removed successfully.");
    }

    // Search movie by director
    public void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println("Found: " + temp.title + " (" + temp.year + ") - Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found by director: " + director);
    }

    // Search movie by rating
    public void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println("Found: " + temp.title + " (" + temp.year + ") - Directed by: " + temp.director);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found with rating: " + rating);
    }

    // Update movie rating by title
    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated for '" + title + "' to " + newRating);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found!");
    }

    // Display all movies in forward order
    public void displayMoviesForward() {
        if (head == null) {
            System.out.println("No movies available!");
            return;
        }
        Movie temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    // Display all movies in reverse order
    public void displayMoviesReverse() {
        if (tail == null) {
            System.out.println("No movies available!");
            return;
        }
        Movie temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.prev;
        }
    }

    public static void main(String[] args) {
        MovieList movieList = new MovieList();

        // Adding movies
        movieList.insertAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        movieList.insertAtEnd("Interstellar", "Christopher Nolan", 2014, 8.6);
        movieList.insertAtBeginning("Titanic", "James Cameron", 1997, 7.8);
        movieList.insertAtPosition("The Dark Knight", "Christopher Nolan", 2008, 9.0, 2);

        // Display all movies
        System.out.println("\nMovies List (Forward Order):");
        movieList.displayMoviesForward();

        System.out.println("\nMovies List (Reverse Order):");
        movieList.displayMoviesReverse();

        // Searching
        System.out.println("\nSearching for movies by 'Christopher Nolan':");
        movieList.searchByDirector("Christopher Nolan");

        System.out.println("\nSearching for movies with rating 8.8:");
        movieList.searchByRating(8.8);

        // Updating rating
        System.out.println("\nUpdating rating of 'Inception' to 9.1:");
        movieList.updateRating("Inception", 9.1);
        movieList.displayMoviesForward();

        // Removing a movie
        System.out.println("\nRemoving 'Titanic':");
        movieList.removeMovie("Titanic");
        movieList.displayMoviesForward();
    }
}
