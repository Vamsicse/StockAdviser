package com.galaxy.StockAdviser.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockSellAdviser {

    String name;
    List<StockPurchase> stockPurchases = new ArrayList<>();
    double currentStockValue;
    double profit;
    String result;

    public StockSellAdviser(){}

    public StockSellAdviser(String name, List<StockPurchase> stockPurchases, double currentStockValue, double profit, String result){
        this.name = name;
        this.stockPurchases = stockPurchases;
        this.currentStockValue = currentStockValue;
        this.profit = profit;
        this.result = result;
    }

}