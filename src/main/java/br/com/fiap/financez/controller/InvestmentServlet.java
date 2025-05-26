package br.com.fiap.financez.controller;

import br.com.fiap.financez.dao.InvestmentDao;
import br.com.fiap.financez.dao.InvestmentOptionDao;
import br.com.fiap.financez.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/investments")
public class InvestmentServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");
    if (user != null) {
      try {
        InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();
        List<InvestmentOption> investmentOptions = investmentOptionDao.getAll();

        InvestmentDao investmentDao = new InvestmentDao();
        List<Investment> investments = investmentDao.getAll();

        req.setAttribute("investmentOptions", investmentOptions);
        req.setAttribute("investments", investments);
      } catch (SQLException e) {
        req.setAttribute("errorMessage", e.getMessage());
        req.getRequestDispatcher("login.jsp").forward(req, resp);
      }
    } else {
      req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
    req.getRequestDispatcher("investments.jsp").forward(req, resp);
  }
}