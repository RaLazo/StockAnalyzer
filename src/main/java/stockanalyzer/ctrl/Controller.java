package stockanalyzer.ctrl;

import stockanalyzer.exception.YException;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;


public class Controller {

	Stock stock = null;
	Calendar from = null;
	/*** process
	 * @param ticker
	 */
	public void process(String ticker) throws YException {
		System.out.println("Start process");

		try{
			stock = YahooFinance.get(ticker);
			var results = new ArrayList<Double>();
			from = Calendar.getInstance();

			from.add(Calendar.WEEK_OF_MONTH, -40); // last 16 weeks

			results.add(getHighest(ticker));
			results.add(getAverage(ticker));
			results.add(getAmount(ticker));

			printResults(ticker,results);

		}catch(IOException e ){
			e.printStackTrace();
			throw new YException();
		}
	}



	/**
	 * printResults
	 * @param header
	 * @param results
	 */
	public void printResults(String header, List<Double> results){
		System.out.println("* * * * "+header+" * * * * *");
		System.out.println("Max: "+ results.get(0));
		System.out.println("Average: "+ results.get(1));
		System.out.println("Amount: "+ results.get(2));
		System.out.println("* * * * END * * * * *");
	}


	public Double getHighest(String ticker) throws IOException{
		var result = stock.getHistory(from, Interval.DAILY).stream()
				.mapToDouble(q -> q.getClose().doubleValue())
				.max()
				.orElse(0.0);
		return result;
	}

	public Double getAverage(String ticker) throws IOException{
		var result = stock.getHistory().stream()
				.mapToDouble(q -> q.getClose().doubleValue())
				.average()
				.orElse(0.0);
		return result;
	}

	public Double getAmount(String ticker) throws IOException{
		var result = stock.getHistory().stream()
				.mapToDouble(q -> q.getClose().doubleValue())
				.count();
		return (double) result;
	}



	/**
	 * getData
	 * @param searchString
	 * @return
	 */
	public Object getData(String searchString) {
		return null;
	}

	/**
	 * closeConnection
	 */
	public void closeConnection() {

	}
}
