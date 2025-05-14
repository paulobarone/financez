CREATE TABLE users
(
  id_user   NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name       VARCHAR2(100) NOT NULL,
  email      VARCHAR2(100) NOT NULL UNIQUE,
  password   VARCHAR2(100) NOT NULL,
  rg         VARCHAR2(20),
  cpf        VARCHAR2(20) UNIQUE,
  gender     CHAR(1)       NOT NULL CHECK (gender IN ('M', 'F')),
  created_at TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE TABLE accounts
(
  id_account    NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_user       NUMBER(5)     NOT NULL,
  account_number VARCHAR2(20)  NOT NULL UNIQUE,
  agency         VARCHAR2(20)  NOT NULL,
  balance        NUMBER(10, 2) NOT NULL,
  created_at     TIMESTAMP DEFAULT SYSTIMESTAMP,
  FOREIGN KEY (id_user) REFERENCES users (id_user)
);

CREATE TABLE transactions
(
  id_transaction NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_account     NUMBER(5)     NOT NULL,
  amount          NUMBER(10, 2) NOT NULL,
  action          VARCHAR2(7)   NOT NULL CHECK (action IN ('Income', 'Expense')),
  description     VARCHAR2(100) NULL,
  created_at      TIMESTAMP DEFAULT SYSTIMESTAMP,
  FOREIGN KEY (id_account) REFERENCES accounts (id_account)
);

CREATE TABLE investment_options
(
  id_investment_option NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name                  VARCHAR2(50) NOT NULL UNIQUE,
  risk_level            VARCHAR2(6)  NOT NULL CHECK (risk_level IN ('Low', 'Medium', 'High')),
  rate                  NUMBER(5, 2) NOT NULL,
  created_at            TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE TABLE investments
(
  id_investment        NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_account           NUMBER(5)     NOT NULL,
  id_investment_option NUMBER(5)     NOT NULL,
  amount                NUMBER(10, 2) NOT NULL,
  description           VARCHAR2(100) NULL,
  created_at            TIMESTAMP DEFAULT SYSTIMESTAMP,
  FOREIGN KEY (id_account) REFERENCES ACCOUNTS (id_account),
  FOREIGN KEY (id_investment_option) REFERENCES investment_options (id_investment_option)
);
