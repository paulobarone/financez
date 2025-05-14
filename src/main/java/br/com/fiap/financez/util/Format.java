package br.com.fiap.financez.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Format {
  static Locale brasil = new Locale("pt", "BR");
  static NumberFormat formatCoin = NumberFormat.getCurrencyInstance(brasil);
  static DecimalFormat formatReturnRate = new DecimalFormat("#.##%");

  public static String getFormattedAmount(double amount) {
    return formatCoin.format(amount);
  }

  public static String getFormattedReturnAmount(double amount, double rate) {
    return formatCoin.format(amount * rate);
  }

  public static String getFormattedRate(double rate) {
    return formatReturnRate.format(rate);
  }
}
