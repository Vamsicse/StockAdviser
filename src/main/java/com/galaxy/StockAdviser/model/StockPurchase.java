package com.galaxy.StockAdviser.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 *
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockPurchase {

    private String date, cp;

    public StockPurchase(){}

    public StockPurchase(String date, String cp){
        this.date = date;
        this.cp = cp;
    }

}
