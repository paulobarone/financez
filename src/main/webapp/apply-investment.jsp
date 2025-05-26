<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <title>FinanceZ | Aplicar Investimento</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/custom.css">
  </head>
  <body class="background-page apply-investment-body">
    <div class="container d-flex flex-column gap-2 mt-5">
      <h1 class="text-white text-center"><%= request.getAttribute("investmentErrorMessage") != null ? request.getAttribute("investmentErrorMessage") : "" %></h1>
    </div>
    <form method="post" action="apply-investment" novalidate class="container flex-column justify-content-center align-items-center gap-4 p-4" style="max-width: 500px;">
      <div class="d-flex flex-column gap-1">
        <div class="mb-3">
          <label for="amount" class="form-label cursor-pointer text-white">Valor:</label>
          <input type="number" class="form-control custom-input" name="amount" id="amount" placeholder="Digite o valor do investimento" required>
        </div>
        <span class="text-danger mb-3">
          <%= request.getAttribute("investmentErrorMessage") != null ? request.getAttribute("investmentErrorMessage") : "" %>
        </span>
      </div>
      <div class="d-flex flex-column gap-2 align-items-center text-center">
        <button type="submit" class="primary-button">Investir</button>
        <a href="home" class="secondary-button">Cancelar</a>
      </div>
    </form>
  </body>
</html>