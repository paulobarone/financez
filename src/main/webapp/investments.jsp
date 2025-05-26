<%@ page import="java.util.List" %>
<%@ page import="br.com.fiap.financez.model.InvestmentOption" %>
<%@ page import="static br.com.fiap.financez.util.Format.getFormattedRate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <title>FinanceZ | Investimentos</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/custom.css">
    <script defer src="https://kit.fontawesome.com/24d079d686.js" crossorigin="anonymous"></script>
  </head>
  <body class="background-page">
    <main class="container max-container d-flex flex-column gap-4 pt-4">
      <div class="d-flex flex-column gap-4">
        <h1 class="text-white">Investimentos Disponíveis</h1>
        <div class="table-responsive">
          <table class="table table-dark table-striped overflow-x-auto">
            <tr>
              <th>Nome</th>
              <th class="text-center">Risco</th>
              <th class="text-center">Taxa</th>
              <th></th>
            </tr>
            <%
              List<InvestmentOption> investmentOptions = (List<InvestmentOption>) request.getAttribute("investmentOptions");
              if (investmentOptions != null && !investmentOptions.isEmpty()) {
                for (InvestmentOption invOption : investmentOptions) {
            %>
            <tr>
              <td><%= invOption.getName() %></td>
              <td class="text-center"><%= invOption.getRiskLevel().getFormattedRisk() %></td>
              <td class="text-center"><%= getFormattedRate(invOption.getRate()) %></td>
              <td class="text-end">
                <a class="reset-button">
                  <i class="fa-solid fa-sack-dollar simple-icon fa-xl"></i>
                </a>
              </td>
            </tr>
            <%
              }
            } else {
            %>
            <tr>
              <td colspan="4" class="text-center">Nenhum investimento encontrado.</td>
            </tr>
            <% } %>
          </table>
        </div>
      </div>
    </main>
    <footer class="d-flex footer justify-content-center gap-4 align-items-center p-4 fixed-bottom">
      <a class="icon-button page-button page-selected">
        <i class="fa-solid fa-dollar-sign fa-xl icon icon-page-selected"></i>
      </a>
      <a href="home" class="icon-button page-button">
        <i class="fa-solid fa-house fa-xl icon"></i>
      </a>
      <a href="transactions" class="icon-button page-button">
        <i class="fa-solid fa-calendar-days fa-xl icon"></i>
      </a>
    </footer>
  </body>
</html>