package com.galaxy.StockAdviser;

import com.galaxy.StockAdviser.adviser.StockTradeAdviser;
import com.galaxy.StockAdviser.constants.MessageConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockAdviserApplication {

	public static void main(String[] args) {

		SpringApplication.run(StockAdviserApplication.class, args);
		String note = "-\n" + "\n" + "/*\n" +
				"    @author:  Vamsi Krishna Myalapalli\n" +
				"    @version: 1.0\n" +
				"    @since:   2019-12-27\n" +
				"    @mail:    vamsikrishna.vasu@gmail.com\n" + "\n" +
				"    Courtesy to Yahoo Finance API\n" + "*/\n" + "\n" + "-\n";
		System.out.println(note);
		if(null==args || args.length<1){
			System.out.println(MessageConstants.ERROR + "Please pass your stock investments file path as argument.");
			System.exit(1);
		}
		StockTradeAdviser.suggestStockTrading(args[0]);
	}

}
