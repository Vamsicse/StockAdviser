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
public class StockBuyAdviser {

    public static void printRanDownStocks(Map<String, Stock> allStocks){

        Map<String, List<StockPurchase>> myStocks = StockTradeAdviser.getMyStocksList();
        PrintConstants.printDashLine();
        System.out.println("Ran Down Stocks (Now)");
        PrintConstants.printDashLine();
        System.out.println("Stock" + getTS(1) + "Share#" + getTS(1) + "Now" + getTS(1) + "Day-Low" + getTS(1) + "Year-Low" );
        PrintConstants.printDashLine();
        for(String currStockName : allStocks.keySet()){
            String paddedStockCode = StringUtils.rightPad(currStockName,4);
            double now = allStocks.get(currStockName).getQuote().getPrice().doubleValue();
            double dayLow = allStocks.get(currStockName).getQuote().getDayLow().doubleValue();
            double yearLow = allStocks.get(currStockName).getQuote().getYearLow().doubleValue();
            if(now%yearLow < 1.3){
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
                    System.out.print("~");
                }
                System.out.print(getTS(1));
                System.out.print(dayLow);
                System.out.print(getTS(1));
                System.out.print(now + getTS(1));
                System.out.println(yearLow);
            }
        }
        PrintConstants.printDashLine();
    }

}
