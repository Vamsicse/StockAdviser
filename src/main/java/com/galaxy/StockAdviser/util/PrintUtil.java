package com.galaxy.StockAdviser.util;

import com.galaxy.StockAdviser.constants.StockConstants;
import yahoofinance.Stock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import static com.galaxy.StockAdviser.constants.PrintConstants.getTS;

/**
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class PrintUtil {

    private static Map<String, Stock> allStockDataMap = StockUtil.getAllStocks();

    public static void printMetaData(String currStockName, boolean flag, StockConstants invoker) {
        System.out.print(getTS(1));
        System.out.format("%.2f", allStockDataMap.get(currStockName).getQuote().getOpen().doubleValue());              // Open
        System.out.print(getTS(1));
        System.out.format("%.2f", allStockDataMap.get(currStockName).getQuote().getPreviousClose().doubleValue());    // Previous-Close

        System.out.print(getTS(2));
        if(invoker.equals(StockConstants.DIVIDEND_ANALYZER)){
            System.out.print(getTS(4));
        }
        System.out.format("%.2f", allStockDataMap.get(currStockName).getQuote().getDayLow().doubleValue());  // Day-Low
        System.out.print(getTS(1));
        System.out.format("%.2f", allStockDataMap.get(currStockName).getQuote().getDayHigh().doubleValue()); // Day-High

        System.out.print(getTS(2));
        System.out.format("%.2f", allStockDataMap.get(currStockName).getQuote().getYearLow().doubleValue());  // Year-Low
        System.out.print(getTS(1));
        System.out.format("%.2f", allStockDataMap.get(currStockName).getQuote().getYearHigh().doubleValue()); // Year-High

        System.out.print(getTS(2));
        System.out.format("%.2f", allStockDataMap.get(currStockName).getQuote().getPriceAvg50().doubleValue());  // Avg-50
        System.out.print(getTS(3));
        System.out.format("%.2f", allStockDataMap.get(currStockName).getQuote().getPriceAvg200().doubleValue()); // Avg-200

        if (!flag) {
            double annualYield = 0;
            double annualYieldPercent = 0;
            Calendar calendar = null;
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            boolean perk = true;
            try {
                calendar = allStockDataMap.get(currStockName).getDividend().getPayDate();
                annualYield = allStockDataMap.get(currStockName).getDividend().getAnnualYield().doubleValue();
                annualYieldPercent = allStockDataMap.get(currStockName).getDividend().getAnnualYieldPercent().doubleValue();
            } catch (Exception e) {
                perk = false;
            }
            if (perk) {
                if(null==calendar){
                    System.out.print(getTS(2) + "****-**-**");                                // Pay-Date
                }
                else{
                    System.out.print(getTS(2) + format1.format(calendar.getTime()));                                // Pay-Date
                }
                System.out.print(getTS(1)); System.out.format("%.2f", annualYield);                             // Annual-Yield
                System.out.print(getTS(2)); System.out.format("%.2f", annualYieldPercent);                      // Annual-Yield-%
            }
        }
    }

}
