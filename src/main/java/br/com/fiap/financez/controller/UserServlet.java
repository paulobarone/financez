package br.com.fiap.financez.controller;

import br.com.fiap.financez.dao.UserDao;
import br.com.fiap.financez.model.User;
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
      UserDao userDao = new UserDao();

      String name = req.getParameter("name");
      String email = req.getParameter("email");
      String password = req.getParameter("password");
      String cpf = req.getParameter("cpf");

      User newUser = new User(name, email, password, cpf);

      userDao.register(newUser);
      resp.sendRedirect("index.jsp");
    } catch (IllegalArgumentException e) {
      req.setAttribute("errorMessage", e.getMessage());
      req.getRequestDispatcher("register.jsp").forward(req, resp);
    } catch (SQLException e) {
      req.setAttribute("errorMessage", "Erro inesperado ao registrar usuário.");
      req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
  }
}
