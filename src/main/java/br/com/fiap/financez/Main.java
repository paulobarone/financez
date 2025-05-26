package br.com.fiap.financez;

import br.com.fiap.financez.dao.*;
import br.com.fiap.financez.model.InvestmentOption;
import br.com.fiap.financez.model.enums.RiskLevel;

import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {
    InvestmentOptionDao investmentOptionDao = new InvestmentOptionDao();

    InvestmentOption io1 = new InvestmentOption("Tesouro Selic", RiskLevel.LOW, 0.08);
    InvestmentOption io2 = new InvestmentOption("CDB Pós-Fixado", RiskLevel.LOW, 0.09);
    InvestmentOption io3 = new InvestmentOption("LCI/LCA", RiskLevel.LOW, 0.07);
    InvestmentOption io4 = new InvestmentOption("Fundos DI", RiskLevel.LOW, 0.07);
    InvestmentOption io5 = new InvestmentOption("Debêntures Incentivadas", RiskLevel.MEDIUM, 0.11);
    InvestmentOption io6 = new InvestmentOption("Fundos Multimercado", RiskLevel.MEDIUM, 0.12);
    InvestmentOption io7 = new InvestmentOption("Ações de Grandes Empresas", RiskLevel.HIGH, 0.15);
    InvestmentOption io8 = new InvestmentOption("Criptomoedas", RiskLevel.HIGH, 0.25);
    InvestmentOption io9 = new InvestmentOption("Fundos de Ações", RiskLevel.HIGH, 0.18);
    InvestmentOption io10 = new InvestmentOption("Imóveis (Fundos Imobiliários)", RiskLevel.MEDIUM, 0.10);
    InvestmentOption io11 = new InvestmentOption("Previdência Privada (PGBL/VGBL)", RiskLevel.LOW, 0.06);
    InvestmentOption io12 = new InvestmentOption("Startups (Equity Crowdfunding)", RiskLevel.HIGH, 0.30);

    investmentOptionDao.register(new InvestmentOption[]{io1, io2, io3, io4, io5, io6, io7, io8, io9, io10, io11, io12});
  }
}