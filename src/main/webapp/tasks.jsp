<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <title>FinanceZ | Tarefas</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/custom.css">
    <link rel="stylesheet" href="./resources/css/checkbox.css">
    <script defer src="https://kit.fontawesome.com/24d079d686.js" crossorigin="anonymous"></script>
  </head>
  <body class="background-page">
    <main class="container max-container d-flex flex-column gap-4 py-4">
      <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-white m-0">Tarefas</h1>
        <button class="reset-button">
          <i class="fa-solid fa-square-plus fa-xl simple-icon"></i>
        </button>
      </div>
      <div class="custom-list">
        <div class="d-flex custom-item justify-content-between align-items-center gap-2">
          <div class="d-flex gap-2 align-items-center">
            <div class="task-left-side">
              <div class="custom-checkbox-container">
                <input type="checkbox" id="1">
                <i class="fa-solid fa-check"></i>
              </div>
            </div>
            <label for="1" class="text-white fs-5">Ler um livro</label>
          </div>
          <div class="d-flex align-items-center">
            <button class="reset-button">
              <i class="fa-solid fa-clock fa-lg"></i>
            </button>
            <button class="reset-button">
              <i class="fa-solid fa-calendar-days fa-lg"></i>
            </button>
          </div>
        </div>
      </div>
    </main>
    <footer class="d-flex footer-border justify-content-center gap-4 align-items-center p-4">
      <a href="/financez_war_exploded/" class="icon-button page-button">
        <i class="fa-solid fa-dollar-sign fa-xl icon"></i>
      </a>
      <a href="/financez_war_exploded/" class="icon-button page-button">
        <i class="fa-solid fa-house fa-xl icon"></i>
      </a>
      <a class="icon-button page-button page-selected">
        <i class="fa-solid fa-calendar-days fa-xl icon icon-page-selected"></i>
      </a>
    </footer>
    <script src="https://kit.fontawesome.com/24d079d686.js" crossorigin="anonymous"></script>
  </body>
</html>