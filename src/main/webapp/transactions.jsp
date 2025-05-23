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
    <main class="container max-container d-flex flex-column gap-4 py-4">
      <h1 class="text-white m-0">Transações</h1>
      <div class="custom-list">
        <div class="d-flex custom-item justify-content-between gap-2 align-items-center">
          <div class="d-flex gap-2 align-items-center">
            <div class="icon-button transaction-button">
              <i class="fa-solid fa-arrow-up fa-xl icon"></i>
            </div>
            <div class="d-flex flex-column">
              <h3 class="fs-6 text-white m-0 lh-sm">Recebimento</h3>
              <p class="text-secondary m-0 fs-6 lh-sm">Você recebeu uma transferência</p>
            </div>
          </div>
          <div class="d-flex col-3 flex-column align-items-end">
            <span class="text-success">+ R$ 55,37</span>
            <span class="text-white">11/10</span>
          </div>
        </div>
        <div class="d-flex custom-item justify-content-between gap-2 align-items-center">
          <div class="d-flex gap-2 align-items-center">
            <div class="icon-button transaction-button">
              <i class="fa-solid fa-arrow-down fa-xl icon"></i>
            </div>
            <div class="d-flex flex-column">
              <h3 class="fs-6 text-white m-0 lh-sm">Transferência</h3>
              <p class="text-secondary m-0 fs-6 lh-sm">Você efetuou uma transferência</p>
            </div>
          </div>
          <div class="d-flex col-3 flex-column align-items-end">
            <span class="text-danger">- R$ 25,00</span>
            <span class="text-white">04/10</span>
          </div>
        </div>
      </div>
    </main>
    <footer class="d-flex footer-border justify-content-center gap-4 align-items-center p-4">
      <a href="investments.jsp" class="icon-button page-button">
        <i class="fa-solid fa-dollar-sign fa-xl icon"></i>
      </a>
      <a href="index.jsp" class="icon-button page-button">
        <i class="fa-solid fa-house fa-xl icon"></i>
      </a>
      <a class="icon-button page-button page-selected">
        <i class="fa-solid fa-calendar-days fa-xl icon icon-page-selected"></i>
      </a>
    </footer>
    <script src="https://kit.fontawesome.com/24d079d686.js" crossorigin="anonymous"></script>
  </body>
</html>