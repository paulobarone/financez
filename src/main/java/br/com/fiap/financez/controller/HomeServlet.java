package br.com.fiap.financez.controller;

import br.com.fiap.financez.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");
    if (user != null && user.getName() != null) {
      System.out.println(user.getName());

      String[] parts = user.getName().trim().split("\\s+");
      req.setAttribute("firstName", parts[0]);
    } else {
      req.setAttribute("firstName", "");
    }
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }
}