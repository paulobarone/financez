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

@WebServlet("/transactions")
public class TransactionsServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");
    if (user != null) {
      List<Transaction> transactions = null;

      try {
        TransactionDao transactionDao = new TransactionDao();

        transactions = transactionDao.getTransactionsUser(user);

        req.setAttribute("transactions", transactions);
      } catch (SQLException e) {
        req.setAttribute("errorMessage", e.getMessage());
        req.getRequestDispatcher("login.jsp").forward(req, resp);
      }

      req.setAttribute("allTransactions", transactions);
    }

    req.getRequestDispatcher("transactions.jsp").forward(req, resp);
  }
}