<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <title>FinanceZ | Login</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/custom.css">
  </head>
  <body class="background-page">
    <form class="container flex-column justify-content-center align-items-center gap-4 mt-5 p-4" style="max-width: 500px;">
      <div class="text-center">
        <h1 class="m-0 text-white">Finance<span class="branding-text-color">Z</span></h1>
        <p class="text-white">Gerenciador de Finanças</p>
      </div>
      <h1 class="text-white text-center my-4">Bem-vindo de volta!</h1>
      <div class="mb-3">
        <label for="email" class="form-label cursor-pointer text-white">E-mail:</label>
        <input type="email" class="form-control custom-input" name="email" id="email" placeholder="Digite seu e-mail">
      </div>
      <div class="mb-3">
        <label for="password" class="form-label cursor-pointer text-white">Senha:</label>
        <input type="password" class="form-control custom-input" name="password" id="password" placeholder="Digite sua senha">
      </div>
      <div class="d-flex flex-column gap-1 align-items-center text-center">
        <a href="index.jsp" type="button" class="primary-button">Login</a>
        <span class="text-white">Ainda não é um membro? <a href="register.jsp" class="branding-text-color text-decoration-underline">Registre-se!</a></span>
      </div>
    </form>
  </body>
</html>