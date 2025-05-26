CREATE TABLE users
(
  id_user    NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name       VARCHAR2(100) NOT NULL,
  email      VARCHAR2(100) NOT NULL UNIQUE,
  password   VARCHAR2(100) NOT NULL,
  balance    NUMBER(10, 2) NOT NULL,
  cpf        VARCHAR2(20)  NOT NULL UNIQUE,
  created_at TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE TABLE transactions
(
  id_transaction NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_user        NUMBER(5)     NOT NULL,
  amount         NUMBER(10, 2) NOT NULL,
  action         VARCHAR2(7)   NOT NULL CHECK (action IN ('Income', 'Expense')),
  created_at     TIMESTAMP DEFAULT SYSTIMESTAMP,
  FOREIGN KEY (id_user) REFERENCES users (id_user)
);

CREATE TABLE investment_options
(
  id_investment_option NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name                 VARCHAR2(50) NOT NULL UNIQUE,
  risk_level           VARCHAR2(6)  NOT NULL CHECK (risk_level IN ('Low', 'Medium', 'High')),
  rate                 NUMBER(5, 2) NOT NULL,
  created_at           TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE TABLE investments
(
  id_investment        NUMBER(5) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_user              NUMBER(5)     NOT NULL,
  id_investment_option NUMBER(5)     NOT NULL,
  amount               NUMBER(10, 2) NOT NULL,
  created_at           TIMESTAMP DEFAULT SYSTIMESTAMP,
  FOREIGN KEY (id_user) REFERENCES users (id_user),
  FOREIGN KEY (id_investment_option) REFERENCES investment_options (id_investment_option)
);