package com.galaxy.StockAdviser.adviser;

import com.galaxy.StockAdviser.constants.PrintConstants;
import com.galaxy.StockAdviser.model.StockPurchase;
import org.apache.commons.lang3.StringUtils;
import yahoofinance.Stock;

import java.util.List;
import java.util.Map;

import static com.galaxy.StockAdviser.constants.PrintConstants.getTS;

/**
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class StockTradeAdviser {

    public static void printRanDownStocks(Map<String, Stock> allStocks, Map<String, List<StockPurchase>> myStocks){

        PrintConstants.printDashLine();
        System.out.println("Ran Down Stocks (Now) [Consider Buying]");
        PrintConstants.printDashLine();
        System.out.println("Stock" + getTS(1) + "Share#" + getTS(1) + "Now" + getTS(1) + "Day-Low" + getTS(1) + "Year-Low" + getTS(1) + "Year-High");
        PrintConstants.printDashLine();
        for(String currStockName : allStocks.keySet()){
            String paddedStockCode = StringUtils.rightPad(currStockName,4);
            double now = allStocks.get(currStockName).getQuote().getPrice().doubleValue();
            double dayLow = allStocks.get(currStockName).getQuote().getDayLow().doubleValue();
            double yearLow = allStocks.get(currStockName).getQuote().getYearLow().doubleValue();
            double yearHigh = allStocks.get(currStockName).getQuote().getYearHigh().doubleValue();
            if(((now-yearLow)/100) < 0.04){
                System.out.print(paddedStockCode);
                System.out.print(getTS(1));
                int myStockCount=0;
                try {
                    myStockCount = myStocks.get(currStockName).size();
                } catch (Exception e) {}
                if(myStockCount>0){
                    System.out.printf("%d", myStockCount);                                          // Share#
                }
                else{
                    System.out.print("-");
                }
                System.out.print(getTS(1));
                System.out.printf("%.2f", dayLow);
                System.out.print(getTS(1));
                System.out.printf("%.2f", now);
                System.out.print(getTS(1));
                System.out.printf("%.2f", yearLow);
                System.out.print(getTS(2));
                System.out.printf("%.2f", yearHigh);
                System.out.println();
            }
        }
        PrintConstants.printDashLine();
    }

    /*
    public static void printRanUpStocks(Map<String, Stock> allStocks, Map<String, List<StockPurchase>> myStocks){
        System.out.println();
        PrintConstants.printDashLine();
        System.out.println("Ran Up Stocks (Now) [Consider Selling]");
        PrintConstants.printDashLine();
        System.out.println("Stock" + getTS(1) + "Share#" + getTS(1) + "Now" + getTS(1) + "Day-Low" + getTS(1) + "Year-Low" + getTS(1) + "Year-High");
        PrintConstants.printDashLine();
        for(String currStockName : allStocks.keySet()){
            String paddedStockCode = StringUtils.rightPad(currStockName,4);
            double now = allStocks.get(currStockName).getQuote().getPrice().doubleValue();
            double dayLow = allStocks.get(currStockName).getQuote().getDayLow().doubleValue();
            double yearLow = allStocks.get(currStockName).getQuote().getYearLow().doubleValue();
            double yearHigh = allStocks.get(currStockName).getQuote().getYearHigh().doubleValue();
            if(((yearHigh-now)/100) < 0.04){
                System.out.print(paddedStockCode);
                System.out.print(getTS(1));
                int myStockCount=0;
                try {
                    myStockCount = myStocks.get(currStockName).size();
                } catch (Exception e) {}
                if(myStockCount>0){
                    System.out.printf("%d", myStockCount);                                          // Share#
                }
                else{
                    System.out.print("-");
                }
                System.out.print(getTS(1));
                System.out.printf("%.2f", dayLow);
                System.out.print(getTS(1));
                System.out.printf("%.2f", now);
                System.out.print(getTS(1));
                System.out.printf("%.2f", yearLow);
                System.out.print(getTS(2));
                System.out.printf("%.2f", yearHigh);
                System.out.println();
            }
        }
        PrintConstants.printDashLine();
    }
    */

}
