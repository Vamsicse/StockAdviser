package com.galaxy.StockAdviser.adviser;

import com.galaxy.StockAdviser.converter.UnmarshallHelper;
import com.galaxy.StockAdviser.gateway.StockAccessor;
import com.galaxy.StockAdviser.model.StockPurchase;
import com.galaxy.StockAdviser.model.StockSellAdviser;
import com.galaxy.StockAdviser.util.IOUtil;
import yahoofinance.Stock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class StockTradeAdviser {

    private static Map<String, Stock> stocks = new HashMap<>();

    public static void suggestStockTrading(String stocksFilePath) {
        String stocksData = IOUtil.getFileContent(stocksFilePath);
        List<StockSellAdviser> myStocks = UnmarshallHelper.unMarshalltoStockSellAdviser(stocksData);
        populateStocks(myStocks);
        getLS();
        printSuggestions(myStocks);
    }

    private static void populateStocks(List<StockSellAdviser> myStocks) {
        for (StockSellAdviser stockSellAdviser : myStocks) {
            String currentStockName = stockSellAdviser.getName();
            Stock currentStock = StockAccessor.getStock(currentStockName);
            stocks.put(currentStockName, currentStock);
        }
    }

    private static void printSuggestions(List<StockSellAdviser> myStocks) {
        printDashedLine();
        System.out.println("Stock" + getTS(1) + "Bought" + getTS(2) + "CostPrice" + getTS(1)
                + "Now" + getTS(1) + "Diff" + getTS(1) + "P/L" + getTS(1)
                + "DayOpen" + getTS(1) + "PrevClose" + getTS(1)
                + "DayLow" + getTS(1) + "DayHigh" + getTS(1)
                + "PriceAvg50" + getTS(1) + "PriceAvg200" + getTS(1)
                + "YearLow" + getTS(1) + "YearHigh" + getTS(1)
                + "Pay-Date" + getTS(1) + "AnnualYield" + getTS(1) + "AnnualYield%" );
        printDashedLine();
        double totalInvest = 0, totalValue = 0;
        for (StockSellAdviser stockSellAdviser : myStocks) {
            String currStockName = stockSellAdviser.getName();
            double currStockVal = stocks.get(currStockName).getQuote().getPrice().doubleValue();
            String paddedCurrStockName = (currStockName.length() == 4) ? currStockName : currStockName + " ";
            System.out.print(paddedCurrStockName + getTS(1));                                  // StockName
            List<StockPurchase> stockPurchaseList = stockSellAdviser.getStockPurchases();
            boolean flag = false;
            for (StockPurchase currStockPurchase : stockPurchaseList) {
                if (flag) {
                    System.out.print(getTS(1));
                }
                double cp = Double.valueOf(currStockPurchase.getCp());
                totalInvest += cp;
                System.out.print(currStockPurchase.getDate() + getTS(1));                  // Bought
                System.out.print("$");
                System.out.format("%.2f",cp);
                System.out.print(getTS(2));                                                // CostPrice
                System.out.print("$");
                System.out.format("%.2f",currStockVal);
                System.out.print(getTS(1));                                              // Now
                totalValue += currStockVal;
                double result = currStockVal - cp;
                System.out.print((int) result + getTS(1));                               // Diff
                String stat = (result < 0.1) ? "LOSS" : "PROFIT";
                System.out.print(stat);                                                              // Profit/Loss

                System.out.print(getTS(1));
                System.out.format("%.2f",stocks.get(currStockName).getQuote().getOpen());  // Open
                System.out.print(getTS(1));
                System.out.format("%.2f", stocks.get(currStockName).getQuote().getPreviousClose()); // Previous-Close

                System.out.print(getTS(2));
                System.out.format("%.2f",stocks.get(currStockName).getQuote().getDayLow());  // Day-Low
                System.out.print(getTS(1));
                System.out.format("%.2f", stocks.get(currStockName).getQuote().getDayHigh()); // Day-High

                System.out.print(getTS(1));
                System.out.format("%.2f",stocks.get(currStockName).getQuote().getPriceAvg50());  // Avg-50
                System.out.print(getTS(2));
                System.out.format("%.2f", stocks.get(currStockName).getQuote().getPriceAvg200()); // Avg-200

                System.out.print(getTS(2));
                System.out.format("%.2f",stocks.get(currStockName).getQuote().getYearLow());  // Year-Low
                System.out.print(getTS(1));
                System.out.format("%.2f", stocks.get(currStockName).getQuote().getYearHigh()); // Year-High
                if (!flag) {
                    double annualYield = 0;
                    double annualYieldPercent = 0;
                    Calendar calendar = null;
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    boolean perk = true;
                    try {
                        calendar = stocks.get(currStockName).getDividend().getPayDate();
                        annualYield = stocks.get(currStockName).getDividend().getAnnualYield().doubleValue();
                        annualYieldPercent = stocks.get(currStockName).getDividend().getAnnualYieldPercent().doubleValue();
                    } catch (Exception e) {
                        perk = false;
                    }
                    if (perk) {
                        System.out.print(getTS(2) + format1.format(calendar.getTime()));                                // Pay-Date
                        System.out.print(getTS(1)); System.out.format("%.2f", annualYield);                             // Annual-Yield
                        System.out.print(getTS(2)); System.out.format("%.2f", annualYieldPercent);                      // Annual-Yield-%
                    }
                }
                System.out.println();
                flag = true;
            } // Exit Inner Loop
            System.out.println();
        } // Exit Outer Loop
        printDashedLine();
        System.out.print("Total-Invest: $" + (int) totalInvest + getTS(3));
        System.out.print("Total-Current-Value: $" + (int) totalValue + getTS(3));
        if (totalValue - totalInvest > 0) {
            System.out.println("GAIN: $" + ((int)totalValue - (int)totalInvest));
        } else {
            System.out.println("LOSS: $" + ((int)totalInvest - (int)totalValue));
        }
        printDashedLine();
    }

    private static String getTS(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    private static void printDashedLine() {
        System.out.println("----------------------------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------------------------");
    }

    private static void getLS() {
        System.out.println("\n\n");
    }

}
