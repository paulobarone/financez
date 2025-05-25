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
    <main class="container max-container d-flex flex-column gap-4 py-4">
      <div class="d-flex flex-column gap-4">
        <h1 class="text-white">Seus Investimentos</h1>
        <div class="table-responsive">
          <table class="table table-dark table-striped overflow-x-auto">
            <tr>
              <th>Nome</th>
              <th class="text-center">Taxa</th>
              <th class="text-center">Valor</th>
              <th class="text-end">Data</th>
            </tr>
            <tr>
              <td>CDB</td>
              <td class="text-center">5.5%</td>
              <td class="text-center">R$ 450,00</td>
              <td class="text-end">10/06/2025</td>
            </tr>
          </table>
        </div>
      </div>
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
            <tr>
              <td>CDB</td>
              <td class="text-center">Baixo</td>
              <td class="text-center">5.5%</td>
              <td class="text-end">
                <button class="reset-button">
                  <i class="fa-solid fa-sack-dollar simple-icon fa-xl"></i>
                </button>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </main>
    <footer class="d-flex footer-border justify-content-center gap-4 align-items-center p-4">
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
    <script src="https://kit.fontawesome.com/24d079d686.js" crossorigin="anonymous"></script>
  </body>
</html>