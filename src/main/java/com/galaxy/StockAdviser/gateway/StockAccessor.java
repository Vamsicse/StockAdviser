package com.galaxy.StockAdviser.gateway;

import yahoofinance.Stock;

/**
 *
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class StockAccessor {

    public static Stock getStock(String stockCode){

        Stock stock = null;
        try {
            stock = yahoofinance.YahooFinance.get(stockCode);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return stock;
    }

}
