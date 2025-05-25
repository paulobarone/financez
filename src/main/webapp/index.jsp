<%@ page import="br.com.fiap.financez.model.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page import="static br.com.fiap.financez.util.Format.getFormattedAmount" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <title>FinanceZ | Início</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/custom.css">
    <script defer src="https://kit.fontawesome.com/24d079d686.js" crossorigin="anonymous"></script>
  </head>
  <body class="background-page">
    <header class="header-content">
      <img class="bg-shape" src="./resources/img/bg-shape.svg" />
      <div class="d-flex justify-content-between align-items-center">
        <div class="welcome-content">
          <i class="fa-solid fa-user fa-xl"></i>
          <h1 class="text-black m-0">Olá <%= request.getAttribute("firstName") != null ? request.getAttribute("firstName") : "" %></h1>
        </div>
        <a href="logout">
          <i class="fa-solid fa-right-from-bracket fa-xl" style="color: #000;"></i>
        </a>
      </div>
    </header>
    <main class="main-background d-flex flex-column align-items-center py-4">
      <div class="box-float">
        <h2 class="small text-secondary text-uppercase m-0">Seu saldo</h2>
        <div class="d-flex gap-2">
          <span class="fs-4 text-white"><%= request.getAttribute("balance") != null ? request.getAttribute("balance") : "0,00" %></span>
          <button class="reset-button">
            <i class="fa-solid fa-eye" style="color: #fff;"></i>
          </button>
        </div>
        <div class="buttons-box">
          <a href="investments.jsp" class="d-flex align-items-center gap-2 bg-transparent border-0 text-decoration-none">
            <div class="icon-button">
              <i class="fa-solid fa-dollar-sign fa-lg icon"></i>
            </div>
            <span class="text-white text-uppercase text-start">Rendimentos</span>
          </a>
          <div class="separator"></div>
          <a href="transactions.jsp" class="d-flex align-items-center gap-2 bg-transparent border-0 text-decoration-none">
            <div class="icon-button">
              <i class="fa-solid fa-money-bill-transfer fa-lg icon"></i>
            </div>
            <span class="text-white text-uppercase text-start">Transações</span>
          </a>
        </div>
      </div>
      <div class="container d-flex flex-column gap-3" style="max-width: 600px">
        <h2 class="text-white m-0">Transações recentes</h2>
        <div class="d-flex custom-list flex-column gap-2">
          <%
            List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
            if (transactions != null && !transactions.isEmpty()) {
              for (Transaction t : transactions) {
                boolean isIncome = t.getAction().name().equalsIgnoreCase("INCOME");
                String icon = isIncome ? "fa-arrow-up" : "fa-arrow-down";
                String color = isIncome ? "text-success" : "text-danger";
                String signal = isIncome ? "+" : "-";
                String name = isIncome ? "Recebimento" : "Transferência";
                String description = isIncome ? "Você recebeu uma transferência" : "Você efetuou uma transferência";
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM");
                String date = sdf.format(t.getCreatedAt());
          %>
          <div class="d-flex custom-item justify-content-between gap-2 align-items-center">
            <div class="d-flex gap-2 align-items-center">
              <div class="icon-button transaction-button">
                <i class="fa-solid <%= icon %> fa-xl icon"></i>
              </div>
              <div class="d-flex flex-column">
                <h3 class="fs-6 text-white m-0 lh-sm"><%= name %></h3>
                <p class="text-secondary m-0 fs-6 lh-sm"><%= description %></p>
              </div>
            </div>
            <div class="d-flex col-3 flex-column align-items-end">
              <span class="<%= color %>"><%= signal %> R$ <%= getFormattedAmount(t.getAmount()) %></span>
              <span class="text-white"><%= date %></span>
            </div>
          </div>
          <%
            }
          } else {
          %>
            <div class="text-white">Nenhuma transação encontrada.</div>
          <%
            }
          %>
        </div>
        <div class="d-flex justify-content-center">
          <a href="transactions.jsp" class="primary-button see-more">Ver mais</a>
        </div>
      </div>
    </main>
    <footer class="d-flex footer-border justify-content-center gap-4 align-items-center p-4">
      <a href="investments.jsp" class="icon-button page-button">
        <i class="fa-solid fa-dollar-sign fa-xl icon"></i>
      </a>
      <a class="icon-button page-button page-selected">
        <i class="fa-solid fa-house fa-xl icon icon-page-selected"></i>
      </a>
      <a href="transactions.jsp" class="icon-button page-button">
        <i class="fa-solid fa-calendar-days fa-xl icon"></i>
      </a>
    </footer>
  </body>
</html>