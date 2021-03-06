package com.galaxy.StockAdviser.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxy.StockAdviser.constants.MessageConstants;
import com.galaxy.StockAdviser.model.MyStockPurchase;

import java.util.List;

/**
 *
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class UnmarshallHelper {

    public static List<MyStockPurchase> unMarshalltoStockSellAdviser(String stocksData) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<MyStockPurchase> stockPurchases = null;
        try {
            stockPurchases = objectMapper.readValue(stocksData, new TypeReference<List<MyStockPurchase>>(){});
        } catch (JsonProcessingException e) {
            System.out.println(MessageConstants.ERROR + "Exception while unmarshalling Stocks file");
            e.printStackTrace();
        }
        return stockPurchases;
    }


}
