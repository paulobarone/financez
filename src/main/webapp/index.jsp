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
          <h1 class="text-black m-0">Olá Paulo!</h1>
        </div>
        <a href="/financez_war_exploded/login.jsp">
          <i class="fa-solid fa-right-from-bracket fa-xl" style="color: #000;"></i>
        </a>
      </div>
    </header>
    <main class="main-background d-flex flex-column align-items-center py-4">
      <div class="box-float">
        <h2 class="small text-secondary text-uppercase m-0">Seu saldo</h2>
        <div class="d-flex gap-2">
          <span class="fs-4 text-white">R$ 1.526,35</span>
          <button class="reset-button">
            <i class="fa-solid fa-eye" style="color: #fff;"></i>
          </button>
        </div>
        <div class="buttons-box">
          <a href="/financez_war_exploded/" class="d-flex align-items-center gap-2 bg-transparent border-0 text-decoration-none">
            <div class="icon-button">
              <i class="fa-solid fa-list fa-lg icon"></i>
            </div>
            <span class="text-white text-uppercase text-start">Adicionar despesa</span>
          </a>
          <div class="separator"></div>
          <a href="/financez_war_exploded/" class="d-flex align-items-center gap-2 bg-transparent border-0 text-decoration-none">
            <div class="icon-button">
              <i class="fa-solid fa-money-bill-transfer fa-lg icon"></i>
            </div>
            <span class="text-white text-uppercase text-start">Extrato de gastos</span>
          </a>
        </div>
      </div>
      <div class="container d-flex flex-column gap-3" style="max-width: 600px">
        <h2 class="text-white m-0">Transações recentes</h2>
        <div class="d-flex custom-list flex-column gap-2">
          <div class="d-flex custom-item container justify-content-between gap-2 align-items-center">
            <div class="d-flex gap-2 align-items-center">
              <div class="icon-button">
                <i class="fa-solid fa-arrow-up fa-xl icon"></i>
              </div>
              <div class="d-flex flex-column">
                <h3 class="fs-6 text-white m-0 lh-sm">Recebimento confirmado</h3>
                <p class="text-secondary m-0 fs-6 lh-sm">Você recebeu uma transferência</p>
              </div>
            </div>
            <div class="d-flex col-3 flex-column align-items-end">
              <span class="text-success">+ R$ 55,37</span>
              <span class="text-white">11/10</span>
            </div>
          </div>
          <div class="d-flex custom-item container justify-content-between gap-2 align-items-center">
            <div class="d-flex gap-2 align-items-center">
              <div class="icon-button">
                <i class="fa-solid fa-arrow-down fa-xl icon"></i>
              </div>
              <div class="d-flex flex-column">
                <h3 class="fs-6 text-white m-0 lh-sm">Transferência enviada</h3>
                <p class="text-secondary m-0 fs-6 lh-sm">Você efetuou uma transferência</p>
              </div>
            </div>
            <div class="d-flex col-3 flex-column align-items-end">
              <span class="text-danger">- R$ 25,00</span>
              <span class="text-white">04/10</span>
            </div>
          </div>
        </div>
        <div class="d-flex justify-content-center">
          <button class="primary-button see-more">Ver mais</button>
        </div>
      </div>
    </main>
    <footer class="d-flex footer-border justify-content-center gap-4 align-items-center p-4">
      <a href="/financez_war_exploded/investments.jsp" class="icon-button page-button">
        <i class="fa-solid fa-dollar-sign fa-xl icon"></i>
      </a>
      <a class="icon-button page-button page-selected">
        <i class="fa-solid fa-house fa-xl icon icon-page-selected"></i>
      </a>
      <a href="/financez_war_exploded/tasks.jsp" class="icon-button page-button">
        <i class="fa-solid fa-calendar-days fa-xl icon"></i>
      </a>
    </footer>
  </body>
</html>