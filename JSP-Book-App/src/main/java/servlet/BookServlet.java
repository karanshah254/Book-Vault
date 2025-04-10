package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/books")
public class BookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("save".equals(action)) {
            String bookName = request.getParameter("title");
            String authorName = request.getParameter("author");
            double price = Double.parseDouble(request.getParameter("price"));

            try (Connection conn = DBConnection.getConnection()) {
                String query = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, bookName);
                    stmt.setString(2, authorName);
                    stmt.setDouble(3, price);
                    stmt.executeUpdate();
                    response.sendRedirect("view.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Error: " + e.getMessage());
            }
        } else if ("view".equals(action)) {
            try (Connection conn = DBConnection.getConnection()) {
                String query = "SELECT * FROM books";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    ResultSet rs = stmt.executeQuery();
                    request.setAttribute("books", rs);
                    request.getRequestDispatcher("view.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Error: " + e.getMessage());
            }
        }
    }
}
