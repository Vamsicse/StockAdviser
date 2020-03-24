package com.galaxy.StockAdviser.adviser;

import com.galaxy.StockAdviser.constants.MessageConstants;
import com.galaxy.StockAdviser.constants.PrintConstants;
import com.galaxy.StockAdviser.constants.StockConstants;
import com.galaxy.StockAdviser.model.MyStockPurchase;
import com.galaxy.StockAdviser.model.StockPurchase;
import com.galaxy.StockAdviser.util.PrintUtil;
import com.galaxy.StockAdviser.util.StockUtil;
import org.apache.commons.lang3.StringUtils;
import yahoofinance.Stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.galaxy.StockAdviser.constants.PrintConstants.getTS;
import static com.galaxy.StockAdviser.constants.PrintConstants.printDashLine;

/**
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class StockTradeAdviser {

    private static Map<String, Stock> allStockDataMap = StockUtil.getAllStocks();
    private static Map<String, List<StockPurchase>> myStockPurchasesMap = new HashMap<>();

    public static Map<String, List<StockPurchase>> getMyStocksList(){
        if(myStockPurchasesMap.size()<1){
            throw new RuntimeException(MessageConstants.ERROR + "Stock Purchases Map not yet initialized");
        }
        return myStockPurchasesMap;
    }

    public static void printSuggestions(List<MyStockPurchase> myStocksList) {
        for(MyStockPurchase myStockPurchase : myStocksList){
            myStockPurchasesMap.put(myStockPurchase.getName(), myStockPurchase.getStockPurchases());
        }
        PrintConstants.getLS();
        printDashLine();
        System.out.println("Stock" + getTS(1) + "Bought" + getTS(2) + "$CP" + getTS(2)
                + "Now" + getTS(2) + "Diff" + getTS(1) + "G/L" + getTS(1)
                + "DayOpen" + getTS(1) + "PrevClose" + getTS(1)
                + "DayLow" + getTS(1) + "DayHigh" + getTS(2)
                + "YearLow" + getTS(1) + "YearHigh" + getTS(1)
                + "$Avg50" + getTS(3) + "$Avg200" + getTS(2)
                + "Pay-Date" + getTS(1) + "AnnualYield" + getTS(1) + "AnnualYield%" );
        printDashLine();
        double totalInvest = 0, totalValue = 0;
        for (MyStockPurchase myStockPurchase : myStocksList) {
            String currStockName = myStockPurchase.getName();
            double currStockVal = allStockDataMap.get(currStockName).getQuote().getPrice().doubleValue();
            String paddedCurrStockName = StringUtils.rightPad(currStockName,4);
            System.out.print(paddedCurrStockName + getTS(1));                                  // StockName
            List<StockPurchase> stockPurchaseList = myStockPurchase.getStockPurchases();
            boolean flag = false;
            for (StockPurchase currStockPurchase : stockPurchaseList) {
                if (flag) {
                    System.out.print(getTS(1));
                }
                double cp = Double.valueOf(currStockPurchase.getCp());
                totalInvest += cp;
                System.out.print(currStockPurchase.getDate() + getTS(1));                       // Bought
                System.out.print("$");
                System.out.format("%.2f",cp);                                                      // CostPrice
                System.out.print(getTS(2));
                System.out.print("$");
                System.out.format("%.2f",currStockVal);                                             // Now
                System.out.print(getTS(2));
                totalValue += currStockVal;
                double result = currStockVal - cp;
                System.out.format("%.2f",Math.abs(result));                                                   // Diff
                System.out.print(getTS(1));
                String stat = (result < 0.01) ? "LOSS" : "GAIN";
                System.out.print(stat);                                                              // Profit/Loss

                PrintUtil.printMetaData(currStockName, flag, StockConstants.TRADE_ADVISER);

                System.out.println();
                flag = true;
            } // Exit Inner Loop
            System.out.println();
        } // Exit Outer Loop
        printDashLine();
        System.out.print("Total-Invest: $" + (int) totalInvest + getTS(3));
        System.out.print("Total-Current-Value: $" + (int) totalValue + getTS(3));
        if (totalValue - totalInvest > 0) {
            System.out.println("GAIN: $" + ((int)totalValue - (int)totalInvest));
        } else {
            System.out.println("LOSS: $" + ((int)totalInvest - (int)totalValue));
        }
        printDashLine();
        PrintConstants.getLS();
    }

}
