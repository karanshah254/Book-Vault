import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter book title to search (or type 'exit' to quit): ");
                String searchQuery = scanner.nextLine();

                if (searchQuery.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                // Connect to server
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Send search request
                output.println(searchQuery);

                // Receive response
                String response = input.readLine();
                System.out.println("Server Response: " + response);

                socket.close();
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
