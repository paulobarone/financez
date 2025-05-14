# FinanceZ

**FinanceZ** é um projeto acadêmico desenvolvido como parte do curso de **Análise e Desenvolvimento de Sistemas** na
**FIAP**. O objetivo é criar um sistema de gerenciamento de finanças que, ao final, será implementado como um site
utilizando **JSP**. Atualmente, o projeto está em fase de produção.

## Funcionalidades

- **Gerenciamento de Usuários**: Cadastro, listagem e exclusão de usuários.
- **Gerenciamento de Contas**: Cadastro, listagem e exclusão de contas bancárias associadas a usuários.
- **Gerenciamento de Transações**: Registro, listagem e exclusão de transações financeiras (rendimento ou despesa).
- **Gerenciamento de Investimentos**: Registro, listagem e exclusão de investimentos e opções de investimento.
- **Banco de Dados**: Integração com banco de dados Oracle para persistência dos dados.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **JSP**: Planejado para a implementação do site.
- **Maven**: Gerenciador de dependências.
- **SQL**: Scripts para criação e manipulação do banco de dados.
- **JDBC**: Conexão com o banco de dados Oracle.

## Estrutura do Projeto

- `src/main/java/org/financez/`: Contém o código-fonte principal.
    - **model**: Classes de modelo (Usuário, Conta, Transação, Investimento, etc.).
    - **dao**: Classes de acesso ao banco de dados (DAO).
    - **view**: Classes para exibição de dados no console.
    - **factory**: Classe para gerenciamento de conexões com o banco de dados.
    - **exception**: Classes de exceções personalizadas.
- `src/main/resources/db/`: Scripts SQL para criação das tabelas no banco de dados.
