package com.galaxy.StockAdviser;

import com.galaxy.StockAdviser.adviser.StockBuyAdviser;
import com.galaxy.StockAdviser.adviser.StockDividendAnalyzer;
import com.galaxy.StockAdviser.adviser.StockTradeAdviser;
import com.galaxy.StockAdviser.constants.MessageConstants;
import com.galaxy.StockAdviser.converter.UnmarshallHelper;
import com.galaxy.StockAdviser.model.MyStockPurchase;
import com.galaxy.StockAdviser.util.IOUtil;
import com.galaxy.StockAdviser.util.StockUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.logging.LogManager;

@SpringBootApplication
public class StockAdviserApplication {

	public static void main(String[] args) {

		LogManager.getLogManager().reset();
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
		String myStocksData = IOUtil.getFileContent(args[0]);
		List<MyStockPurchase> myStocksList = UnmarshallHelper.unMarshalltoStockSellAdviser(myStocksData);
		List<String> myStockCodesList = StockUtil.getStockCodesFromMyStocks(myStocksList);
		StockUtil.initiateAllStocks(myStockCodesList);
		StockTradeAdviser.printSuggestions(myStocksList);
		if(args.length>1 && "HYS".equals(args[1])) {
			StockDividendAnalyzer.printStockDividendAnalysis();
		}
		if(args.length>2 && "RDS".equals(args[2])) {
			StockBuyAdviser.printRanDownStocks(StockUtil.getAllStocks());
		}
	}

}
