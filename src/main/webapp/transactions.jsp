<%@ page import="br.com.fiap.financez.model.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page import="static br.com.fiap.financez.util.Format.getFormattedAmount" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <title>FinanceZ | Transações</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/custom.css">
    <link rel="stylesheet" href="./resources/css/checkbox.css">
    <script defer src="https://kit.fontawesome.com/24d079d686.js" crossorigin="anonymous"></script>
  </head>
  <body class="background-page">
    <main class="container max-container d-flex flex-column gap-4 pt-4">
      <h1 class="text-white m-0">Transações</h1>
      <div class="custom-list">
        <%
          List<Transaction> transactions = (List<Transaction>) request.getAttribute("allTransactions");
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
            <span class="<%= color %>"><%= signal %> <%= getFormattedAmount(t.getAmount()) %></span>
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
    </main>
    <footer class="d-flex footer justify-content-center gap-4 align-items-center p-4 fixed-bottom">
      <a href="investments" class="icon-button page-button">
        <i class="fa-solid fa-dollar-sign fa-xl icon"></i>
      </a>
      <a href="home" class="icon-button page-button">
        <i class="fa-solid fa-house fa-xl icon"></i>
      </a>
      <a class="icon-button page-button page-selected">
        <i class="fa-solid fa-calendar-days fa-xl icon icon-page-selected"></i>
      </a>
    </footer>
  </body>
</html>