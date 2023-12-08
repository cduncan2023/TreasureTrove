import org.json.JSONArray;
import org.json.JSONObject; // Added import statements for JSON classes

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    String title;
    String authors;
    String genre;
    double averageRating;
    int ratingsCount;

    public Book(String title, String authors, String genre, double averageRating, int ratingsCount) {
        this.title = title;
        this.authors = authors;
        this.genre = genre;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
    }
}

public class BookRecommendationSystem {
    private static final String API_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String API_KEY = "AIzaSyBQhTcQApy-Db58UiIlH-J9lWNYt3YNuS0";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for search or recommendation
        System.out.print("Do you want to (1) Search for a book or (2) Get recommendations? Enter 1 or 2: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            // Search for a book
            System.out.print("Enter search keywords (title, author, genre, etc.): ");
            String searchQuery = scanner.nextLine();

            List<Book> searchResults = searchBooks(searchQuery);

            // Display search results
            System.out.println("Search Results:");
            for (Book book : searchResults) {
                displayBookInfo(book);
            }
        } else if (choice == 2) {
            // Get personalized recommendations
            System.out.print("What genre do you prefer? ");
            String desiredGenre = scanner.nextLine();

            System.out.print("How many book recommendations would you like? ");
            int numberOfBooks = scanner.nextInt();

            System.out.print("What is the minimum rating you want the books to be? (0 to 5) ");
            double minRating = scanner.nextDouble();

            List<Book> recommendations = getBookRecommendations(desiredGenre, numberOfBooks, minRating);

            // Display recommendations
            System.out.println("Here are your book recommendations:");
            for (Book book : recommendations) {
                displayBookInfo(book);
            }
        }

        scanner.close();
    }

    private static void displayBookInfo(Book book) {
        System.out.println(book.title + " by " + book.authors);
        System.out.println(" - Genre: " + book.genre);
        System.out.println(" - Rating: " + book.averageRating + "/5");
        System.out.println(" - Number of Ratings: " + book.ratingsCount);
        System.out.println();
    }

    private static List<Book> searchBooks(String searchQuery) {
        List<Book> searchResults = new ArrayList<>();

        try {
            String apiUrl = API_BASE_URL + searchQuery;

            apiUrl += "&key=" + API_KEY;

            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray items = jsonResponse.getJSONArray("items");

                for (int i = 0; i < items.length(); i++) {
                    JSONObject volumeInfo = items.getJSONObject(i).getJSONObject("volumeInfo");
                    String title = volumeInfo.getString("title");
                    String authors = volumeInfo.getJSONArray("authors").getString(0);
                    String genre = volumeInfo.getJSONArray("categories").getString(0);
                    double averageRating = volumeInfo.optDouble("averageRating", 0.0);
                    int ratingsCount = volumeInfo.optInt("ratingsCount", 0);

                    Book book = new Book(title, authors, genre, averageRating, ratingsCount);
                    searchResults.add(book);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    private static List<Book> getBookRecommendations(String desiredGenre, int numberOfBooks, double minRating) {
        // Implementation for personalized recommendations remains the same
        // You can enhance this method with personalized recommendation algorithms
        List<Book> recommendations = new ArrayList<>();

        // ... existing implementation ...

        return recommendations;
    }
}
