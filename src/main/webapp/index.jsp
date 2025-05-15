<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <title>FinanceZ | Início</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/custom.css">
  </head>
  <body class="background-page">
    <header class="header-content">
      <img class="bg-shape" src="./resources/img/bg-shape.svg" />
      <div class="d-flex justify-content-between align-items-center">
        <div class="welcome-content">
          <img class="icon" src="./resources/img/perfil-icon.svg" alt="Ícone de usuário" />
          <h1 class="text-black m-0">Olá Paulo!</h1>
        </div>
        <a href="/financez_war_exploded/login.jsp">
          <img class="cursor-pointer icon" src="./resources/img/exit-icon.svg" alt="Ícone de sair" />
        </a>
      </div>
    </header>
    <main class="main-background">
      <div class="box-float d-flex flex-column">
        <h2 class="small text-secondary text-uppercase m-0">Seu saldo</h2>
        <div class="d-flex gap-2">
          <span class="fs-4 text-white">R$ 1.526,35</span>
          <button class="hide-value-button">
            <img src="./resources/img/eye-icon.svg" alt="Ícone de visibilidade" />
          </button>
        </div>
        <div class="d-flex gap-2 align-items-center justify-content-center">
          <button class="d-flex align-items-center justify-content-center gap-2 bg-transparent border-0">
            <div class="icon-button">
              <img class="icon" src="./resources/img/expense-icon.svg" alt="Ícone de Despesa" />
            </div>
            <span class="text-white text-uppercase text-start">Adicionar despesa</span>
          </button>
          <div class="vertical-separator"></div>
          <button class="d-flex align-items-center justify-content-center gap-2 bg-transparent border-0">
            <div class="icon-button">
              <img class="icon" src="./resources/img/extract-icon.svg" alt="Ícone de Extrato" />
            </div>
            <span class="text-white text-uppercase text-start">Extrato de gastos</span>
          </button>
        </div>
      </div>
    </main>
  </body>
</html>