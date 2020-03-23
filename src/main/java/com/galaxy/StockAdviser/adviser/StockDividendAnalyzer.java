package com.galaxy.StockAdviser.adviser;

import com.galaxy.StockAdviser.constants.PrintConstants;
import com.galaxy.StockAdviser.model.StockPurchase;
import com.galaxy.StockAdviser.util.StockUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */

@Data
public class StockDividendAnalyzer {

    private static Map<String, Double> highYieldStocks = new TreeMap<>();

    public static void printStockDividendAnalysis() {

        Map<String, List<StockPurchase>> myStocks = StockTradeAdviser.getMyStocks();
        PrintConstants.getLS();
        PrintConstants.printDashLine();
        System.out.println("High Yield Stocks");
        PrintConstants.printDashLine();
        System.out.println("Stock" + PrintConstants.getTS(3) + "Annual-Yield%" + PrintConstants.getTS(2) + "MyShares");
        PrintConstants.printDashLine();

        for (String stock : highYieldStocks.keySet()) {
            long stockCount = 0;
            try {
                stockCount = myStocks.get(stock).size();
            } catch (Exception e) {

            }
            String paddedStockName = StringUtils.rightPad(stock, 4);
            System.out.print(paddedStockName + PrintConstants.getTS(3));
            System.out.format("%.2f", highYieldStocks.get(stock));
            System.out.println(PrintConstants.getTS(3) + stockCount);
        }
        PrintConstants.printDashLine();
    }

    public static void populateHighYieldStocks() {
        highYieldStocks = StockUtil.getHighYieldStocks();
    }

}
