package br.com.fiap.financez.controller;

import br.com.fiap.financez.exception.UserRegistrationException;
import br.com.fiap.financez.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      UserService userService = new UserService();

      String name = req.getParameter("name");
      String email = req.getParameter("email");
      String password = req.getParameter("password");
      String cpf = req.getParameter("cpf");

      userService.registerUser(name, email, password, cpf);

      resp.sendRedirect("index.jsp");
    } catch (UserRegistrationException e) {
      req.setAttribute("errorMessage", e.getMessage());
      req.getRequestDispatcher("register.jsp").forward(req, resp);
    } catch (IllegalArgumentException e) {
      req.setAttribute("errorMessage", e.getMessage());
      req.getRequestDispatcher("register.jsp").forward(req, resp);
    } catch (SQLException e) {
      req.setAttribute("errorMessage", e.getMessage());
      req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
  }
}
