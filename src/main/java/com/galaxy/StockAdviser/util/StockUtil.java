package com.galaxy.StockAdviser.util;

import com.galaxy.StockAdviser.constants.MessageConstants;
import com.galaxy.StockAdviser.constants.PrintConstants;
import com.galaxy.StockAdviser.gateway.StockAccessor;
import com.galaxy.StockAdviser.model.MyStockPurchase;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class StockUtil {

    private static Map<String, Double> highYieldStocks = new HashMap<>();
    private static Map<String, Stock> allStocks = new HashMap<>();

    private static List<String> stockCodes = new ArrayList<>(Arrays.asList("ABBV", "ADC", "AIZ", "ALX", "ARE", "BA", "BAC", "BFS", "BKH", "BLK", "BMY", "BAC", "BXP", "BXP",
            "CCI", "CMCSA", "CVS", "D", "DAL", "DGRO", "DIS", "DOW", "DRI", "DUK", "EBF", "ENB", "EOG", "ETN", "EXC", "FE", "FRT", "GILD", "GIS", "GLPI",
            "HDV", "HON", "IBM", "IRM", "JNJ", "KTB", "KO", "LOW", "MAA", "MCD", "MCHP", "MDLZ", "MDP", "MDT", "MGM", "MNR", "MPW", "MSFT", "MRK",
            "NHI", "NNN", "NNN", "NRG", "NWE", "O", "OHI", "OKE", "OTEX", "PFE", "PLD", "PPL", "PSA", "PXD", "QCOM", "REG",
            "SKT", "SLG", "SO", "SPG", "STOR", "SWM", "SWX", "T", "TSN", "UAL", "UHT", "UTX", "UVV", "VNQ","VOO", "VPU", "VYM", "VZ", "WELL", "WHG", "WIX", "XOM"));

    public static void initiateAllStocks(List<String> myStockCodesList) {
        System.out.println(MessageConstants.INFO + "Initiating All Stocks Data. Please wait...");
        PrintConstants.getLS();
        stockCodes.addAll(myStockCodesList);
        for (String currStockCode : stockCodes) {
            allStocks.put(currStockCode, StockAccessor.getStock(currStockCode));
            double annualYield = 0;
            try {
                annualYield = allStocks.get(currStockCode).getDividend().getAnnualYieldPercent().doubleValue();
            } catch (Exception e) {

            }
            highYieldStocks.put(currStockCode, annualYield);
        }

        highYieldStocks = highYieldStocks.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static Map<String, Stock> getAllStocks() {
        return allStocks;
    }

    public static Map<String, Double> getHighYieldStocks() {
        return highYieldStocks;
    }

    public static List<String> getStockCodesFromMyStocks(List<MyStockPurchase> myStockPurchaseList){
        List<String> myStockCodes = new ArrayList<>();
        for(MyStockPurchase myStockPurchase : myStockPurchaseList){
            myStockCodes.add(myStockPurchase.getName());
        }
        return myStockCodes;
    }

}
