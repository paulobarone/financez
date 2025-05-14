CREATE TABLE users2
(
  id_user2   NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name       VARCHAR2(100) NOT NULL,
  email      VARCHAR2(100) NOT NULL UNIQUE,
  password   VARCHAR2(100) NOT NULL,
  rg         VARCHAR2(20),
  cpf        VARCHAR2(20) UNIQUE,
  gender     CHAR(1)       NOT NULL CHECK (gender IN ('M', 'F')),
  created_at TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE TABLE accounts2
(
  id_account2    NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_user2       NUMBER(5)     NOT NULL,
  account_number VARCHAR2(20)  NOT NULL UNIQUE,
  agency         VARCHAR2(20)  NOT NULL,
  balance        NUMBER(10, 2) NOT NULL,
  created_at     TIMESTAMP DEFAULT SYSTIMESTAMP,
  FOREIGN KEY (id_user2) REFERENCES users2 (id_user2)
);

CREATE TABLE transactions2
(
  id_transaction2 NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_account2     NUMBER(5)     NOT NULL,
  amount          NUMBER(10, 2) NOT NULL,
  action          VARCHAR2(7)   NOT NULL CHECK (action IN ('Income', 'Expense')),
  description     VARCHAR2(100) NULL,
  created_at      TIMESTAMP DEFAULT SYSTIMESTAMP,
  FOREIGN KEY (id_account2) REFERENCES accounts2 (id_account2)
);

CREATE TABLE investment_options2
(
  id_investment_option2 NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name                  VARCHAR2(50) NOT NULL UNIQUE,
  risk_level            VARCHAR2(6)  NOT NULL CHECK (risk_level IN ('Low', 'Medium', 'High')),
  rate                  NUMBER(5, 2) NOT NULL,
  created_at            TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE TABLE investments2
(
  id_investment2        NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_account2           NUMBER(5)     NOT NULL,
  id_investment_option2 NUMBER(5)     NOT NULL,
  amount                NUMBER(10, 2) NOT NULL,
  description           VARCHAR2(100) NULL,
  created_at            TIMESTAMP DEFAULT SYSTIMESTAMP,
  FOREIGN KEY (id_account2) REFERENCES ACCOUNTS2 (id_account2),
  FOREIGN KEY (id_investment_option2) REFERENCES investment_options2 (id_investment_option2)
);
