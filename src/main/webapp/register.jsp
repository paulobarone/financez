<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>FinanceZ | Registro</title>
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
  <link rel="stylesheet" href="./resources/css/custom.css">
</head>
<body class="background-page">
  <form class="container flex-column justify-content-center align-items-center gap-4 mt-5 p-4" style="max-width: 500px;">
    <div class="text-center">
      <h1 class="m-0 text-white">Finance<span class="branding-text-color">Z</span></h1>
      <p class="text-white">Gerenciador de Finanças</p>
    </div>
    <div class="mb-3">
      <label for="name" class="form-label cursor-pointer text-white">Nome:</label>
      <input type="text" class="form-control custom-input custom-input" name="name" id="name" placeholder="Digite seu nome">
    </div>
    <div class="mb-3">
      <label for="email" class="form-label cursor-pointer text-white">E-mail:</label>
      <input type="email" class="form-control custom-input" name="email" id="email" placeholder="Digite seu e-mail">
    </div>
    <div class="mb-3">
      <label for="password" class="form-label cursor-pointer text-white">Senha:</label>
      <input type="password" class="form-control custom-input" name="password" id="password" placeholder="Digite sua senha">
    </div>
    <div class="mb-3">
      <label for="password-confirm" class="form-label cursor-pointer text-white">Confirme sua senha:</label>
      <input type="password" class="form-control custom-input" name="password-confirm" id="password-confirm" placeholder="Confirme sua senha">
    </div>
    <div class="d-flex flex-column gap-1 align-items-center text-center">
      <button type="button" class="primary-button">Registrar</button>
      <span class="text-white">Já é um membro? <a href="/financez_war_exploded/login.jsp" class="branding-text-color text-decoration-underline">Faça login!</a></span>
    </div>
  </form>
</body>
</html>