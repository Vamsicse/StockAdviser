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

    private String name;
    private List<StockPurchase> stockPurchases = new ArrayList<>();

    public StockSellAdviser(){}

    public StockSellAdviser(String name, List<StockPurchase> stockPurchases){
        this.name = name;
        this.stockPurchases = stockPurchases;
    }

}