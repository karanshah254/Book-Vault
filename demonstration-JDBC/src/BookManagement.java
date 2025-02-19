import java.sql.*;
import java.util.Scanner;

public class BookManagement {
    private static final String URL = "jdbc:mysql://localhost:3306/booklib";
    private static final String USER = "root";
    private static final String PASSWORD = "ce.karan25";
    private static Connection conn;

    public static void main(String[] args) {
        try {
            // Load the JDBC driver and establish a connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nBook Management System");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Modify Book");
                System.out.println("4. Delete Book");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addBook(scanner);
                        break;
                    case 2:
                        viewBooks();
                        break;
                    case 3:
                        modifyBook(scanner);
                        break;
                    case 4:
                        deleteBook(scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        conn.close();
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addBook(Scanner scanner) {
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            System.out.print("Enter author: ");
            String author = scanner.nextLine();

            System.out.print("Enter price: ");
            while (!scanner.hasNextDouble()) { // Ensures input is a valid double
                System.out.println("Invalid input! Please enter a valid price.");
                scanner.next(); // Discard invalid input
            }
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setDouble(3, price);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Book added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewBooks() {
        try {
            String sql = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\nBook List:");
            System.out.println("ID | Title | Author | Price");
            System.out.println("-----------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("author") + " | " +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void modifyBook(Scanner scanner) {
        try {
            System.out.print("Enter book ID to modify: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new author: ");
            String author = scanner.nextLine();
            System.out.print("Enter new price: ");
            double price = scanner.nextDouble();

            String sql = "UPDATE books SET title = ?, author = ?, price = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book details updated successfully.");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBook(Scanner scanner) {
        try {
            System.out.print("Enter book ID to delete: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
