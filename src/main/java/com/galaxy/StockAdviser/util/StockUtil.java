package com.galaxy.StockAdviser.util;

import com.galaxy.StockAdviser.gateway.StockAccessor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class StockUtil {

    private static Map<String, Double> highYieldStocks = new TreeMap<>();

    private static List<String> stocks = Arrays.asList("ADC","AIZ","ALX","ARE","BFS","BKH","BLK","BMY","BXP","BXP","CCI","CMCSA",
            "CVS","D","DOW","DRI","DUK","EBF","ENB","EOG","ETN","EXC","FE","FRT","GIS","GLPI","HON","IBM","IRM","JNJ","KTB","LOW",
            "MAA","MCD","MCHP","MDLZ","MDP","MDT","MNR","MRK","NHI","NNN","NNN","NRG","NWE","O","OHI","OKE","OKE","PLD","PSA","PXD",
            "QCOM","REG","SKT","SLG","SO","SPG","SWM","SWX","T","TSN","UHT","UTX","UVV","VZ","WELL","WHG","XOM");

    static {
        for(String stock:stocks){
            highYieldStocks.put(stock, StockAccessor.getStock(stock).getDividend().getAnnualYieldPercent().doubleValue());
        }
        highYieldStocks = highYieldStocks.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static Map<String, Double> getHighYieldStocks(){
            return highYieldStocks;
    }

}