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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class StockUtil {

    private static Map<String, Double> highYieldStocks = new HashMap<>();
    private static Map<String, Stock> allStocks = new LinkedHashMap<>();

    private static Set<String> backupStockCodes = new TreeSet<>(Arrays.asList("AAL","AAPL","ABBV","ABT","ADC","AIZ","ALX","ARE","BA","BAC","BFS","BKH","BLK","BMY","BAC","BXP","BXP",
            "CCI","CMCSA","CNC","CVS","D","DAL","DGRO","DIS","DOW","DRI","DUK","EBF","ENB","EOG","ETN","EXC","F","FB","FE","FRT","GE","GILD","GIS","GLPI","GM","ILMN",
            "HDV","HON","IBM","IRM","JNJ","KTB","KO","LOW","LXRX","MAA","MCD","MCHP","MDLZ","MDP","MDT","MGM","MNR","MPW","MRNA","MSFT","MRK",
            "NHI","NKE","NNN","NNN","NRG","NVAX","NWE","O","OHI","OKE","ORCL","OTEX","PFE","PLD","PPL","PSA","PXD","PYPL","QCOM","REG","ROKU",
            "SKT","SLG","SO","SPG","STOR","SWM","SWX","T","TSLA","TSN", "UAL","UHT","UTX", "UVV","VNQ","VOO","VPU","VXRT","VYM","VZ","WELL","WHG","WIX","WMT","WYNN","XOM"));

    public static Set<String> getBackupStockCodes(){
        return backupStockCodes;
    }

    public static void initiateAllStocks(List<String> allStockCodesList) {
        System.out.println(MessageConstants.INFO + "Initiating All Stocks Data. Please wait...");
        PrintConstants.getLS();
        for (String currStockCode : allStockCodesList) {
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

    public static Set<String> getWatchListStocks(String filePath){
        Set<String> watchListStocks = new LinkedHashSet<>();
        try {
            String watchListFileContent = IOUtil.getFileContent(filePath);
            String[] watchListStockCodes = watchListFileContent.split("\n");
            for (String stockCode : watchListStockCodes) {
                watchListStocks.add(stockCode);
            }
        }
        catch (Exception e){
            System.out.println(MessageConstants.ERROR + "Exception while fetching watch list stocks");
            e.printStackTrace();
        }
        return watchListStocks;
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
