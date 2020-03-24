package com.galaxy.StockAdviser.adviser;

import com.galaxy.StockAdviser.constants.MessageConstants;
import com.galaxy.StockAdviser.constants.PrintConstants;
import com.galaxy.StockAdviser.constants.StockConstants;
import com.galaxy.StockAdviser.model.StockPurchase;
import com.galaxy.StockAdviser.util.PrintUtil;
import com.galaxy.StockAdviser.util.StockUtil;
import lombok.Data;
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

@Data
public class StockDividendAnalyzer {

    public static void printStockDividendAnalysis() {
        Map<String, Double> highYieldStocks = StockUtil.getHighYieldStocks();
        Map<String, List<StockPurchase>> myStocks = StockTradeAdviser.getMyStocksList();
        Map<String, Stock> allStocks = StockUtil.getAllStocks();

        PrintConstants.getLS();
        PrintConstants.printDashLine();
        System.out.println("High Yield Stocks");
        PrintConstants.printDashLine();

        System.out.println("Stock" + getTS(1) + "Share#" + getTS(1)
                + "Now" + getTS(1) + "DayOpen" + getTS(1) + "PrevClose" + getTS(5)
                + "DayLow" + getTS(1) + "DayHigh" + getTS(2)
                + "YearLow" + getTS(1) + "YearHigh" + getTS(1)
                + "$Avg50" + getTS(3) + "$Avg200" + getTS(2)
                + "Pay-Date" + getTS(1) + "AnnualYield" + getTS(1) + "AnnualYield%" );
        PrintConstants.printDashLine();

        for (String currStock : highYieldStocks.keySet()) {
            double currStockVal = 0;
            try{
                currStockVal = allStocks.get(currStock).getQuote().getPrice().doubleValue();
            }
            catch(NullPointerException npe){
                System.out.println(MessageConstants.ERROR + "Could not find data for the stock: " + currStock);
                continue;
            }
            String paddedStockName = StringUtils.rightPad(currStock, 4);
            System.out.print(paddedStockName + getTS(1));                    // Stock
            int stockCount = 0;
            try {
                stockCount = myStocks.get(currStock).size();
            } catch (Exception e) {}
            if(stockCount>0){
                System.out.print(stockCount);
            }                                                                                   // Share#
            else{
                System.out.print("-");
            }
            System.out.print(getTS(1));
            System.out.print("$");
            System.out.format("%.2f", currStockVal);                                             // Now

            PrintUtil.printMetaData(currStock, false, StockConstants.DIVIDEND_ANALYZER);
            System.out.println();
        }
        PrintConstants.printDashLine();
        PrintConstants.getLS();
    }

}
