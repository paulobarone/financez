package br.com.fiap.financez.controller;

import br.com.fiap.financez.dao.TransactionDao;
import br.com.fiap.financez.model.Transaction;
import br.com.fiap.financez.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static br.com.fiap.financez.util.Format.getFormattedAmount;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");
    if (user != null && user.getName() != null) {
      try {
        TransactionDao transactionDao = new TransactionDao();

        List<Transaction> recentTransactions = transactionDao.getLastTransactions(user, 6);

        req.setAttribute("balance", getFormattedAmount(user.getBalance()));
        req.setAttribute("transactions", recentTransactions);
      } catch (SQLException e) {
        req.setAttribute("errorMessage", e.getMessage());
        req.getRequestDispatcher("login.jsp").forward(req, resp);
      }

      String[] parts = user.getName().trim().split("\\s+");
      req.setAttribute("firstName", parts[0]);
    } else {
      req.setAttribute("firstName", "");
    }
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }
}