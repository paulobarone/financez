package br.com.fiap.financez.controller;

import br.com.fiap.financez.dao.AccountDao;
import br.com.fiap.financez.model.Account;
import br.com.fiap.financez.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static br.com.fiap.financez.util.Format.getFormattedAmount;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");
    if (user != null && user.getName() != null) {
      try {
        AccountDao accountDao = new AccountDao();
        Account account = accountDao.getAccountByUserId(user.getId());

        if (account != null) {
          req.setAttribute("balance", getFormattedAmount(account.getBalance()));
        } else {
          req.setAttribute("errorMessage", "Account not found.");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

      String[] parts = user.getName().trim().split("\\s+");
      req.setAttribute("firstName", parts[0]);
    } else {
      req.setAttribute("firstName", "");
    }
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }
}