import java.net.*;
import java.io.*;
import java.sql.*;

public class server {
    private static final int PORT = 5000;
    private static Connection conn;

    public static void main(String[] args) {
        try {
            // Establish database connection
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booklib", "root", "ce.karan25");
            System.out.println("Server is running and waiting for client connection...");

            // Start server socket
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected!");

                    // Handle client request
                    handleClient(clientSocket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String searchTitle = input.readLine();
            System.out.println("Received request: " + searchTitle);

            // Fetch book details
            String result = searchBook(searchTitle);
            output.println(result);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String searchBook(String title) {
        String query = "SELECT * FROM books WHERE title LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + title + "%");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return "Book Found: " + rs.getString("title") + " by " + rs.getString("author") + ", Price: $"
                        + rs.getDouble("price");
            } else {
                return "No matching book found.";
            }
        } catch (SQLException e) {
            return "Error fetching book details: " + e.getMessage();
        }
    }
}
