package br.com.fiap.financez.controller;

import br.com.fiap.financez.dao.AccountDao;
import br.com.fiap.financez.dao.InvestmentDao;
import br.com.fiap.financez.dao.InvestmentOptionDao;
import br.com.fiap.financez.exception.UserRegistrationException;
import br.com.fiap.financez.model.Account;
import br.com.fiap.financez.model.Investment;
import br.com.fiap.financez.model.InvestmentOption;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/apply-investment")
public class ApplyInvestmentServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      String accountId = req.getParameter("accountId");
      String optionId = req.getParameter("optionId");
      String amount = req.getParameter("amount");

      if (accountId == null) {
        throw new IllegalArgumentException("Usuário não encontrado");
      } else if (optionId == null) {
        throw new IllegalArgumentException("Opção de investimento não encontrado");
      } else if (amount == null || amount.isEmpty()) {
        throw new IllegalArgumentException("Valor do investimento não pode ser vazio");
      }

      try {
        Double.parseDouble(amount);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Valor do investimento deve ser um número válido");
      }

      InvestmentDao investmentDao = new InvestmentDao();

      Investment newInvestment = new Investment(Integer.parseInt(accountId), Integer.parseInt(optionId), Double.parseDouble(amount));

      investmentDao.register(newInvestment);

      resp.sendRedirect("investments");
    } catch (UserRegistrationException | IllegalArgumentException | SQLException e) {
      req.setAttribute("errorMessage", e.getMessage());
      req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
  }
}
