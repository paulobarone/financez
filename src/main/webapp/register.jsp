<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <title>FinanceZ | Registro</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/custom.css">
  </head>
  <body class="background-page">
    <div class="container d-flex flex-column gap-2 mt-5">
      <div class="text-center">
        <h1 class="m-0 text-white">Finance<span class="branding-text-color">Z</span></h1>
        <p class="text-white">Gerenciador de Finanças</p>
      </div>
      <h1 class="text-white text-center">Registre-se!</h1>
    </div>
    <form method="post" action="register-user" novalidate class="container flex-column justify-content-center align-items-center gap-4 p-4" style="max-width: 500px;">
      <div class="d-flex flex-column gap-1">
        <div class="mb-3">
          <label for="name" class="form-label cursor-pointer text-white">Nome:</label>
          <input type="text" autocomplete="off" class="form-control custom-input custom-input" name="name" required id="name" placeholder="Digite seu nome">
        </div>
        <div class="mb-3">
          <label for="email" class="form-label cursor-pointer text-white">E-mail:</label>
          <input type="email" autocomplete="off" class="form-control custom-input" name="email" required id="email" placeholder="Digite seu e-mail">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label cursor-pointer text-white">Senha:</label>
          <input type="password" autocomplete="off" class="form-control custom-input" name="password" required id="password" placeholder="Digite sua senha">
        </div>
        <div class="mb-3">
          <label for="cpf" class="form-label cursor-pointer text-white">CPF:</label>
          <input type="text" autocomplete="off" class="form-control custom-input" name="cpf" required id="cpf" placeholder="Digite seu CPF">
        </div>
        <span class="text-danger mb-3" name="error-message">
          <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
        </span>
      </div>
      <div class="d-flex flex-column gap-1 align-items-center text-center">
        <button type="submit" class="primary-button">Registrar</button>
        <span class="text-white">Já é um membro? <a href="login.jsp" class="branding-text-color text-decoration-underline">Faça login!</a></span>
      </div>
    </form>
  </body>
</html>