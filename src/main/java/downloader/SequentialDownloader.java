package downloader;


import stockanalyzer.exception.YException;

import java.util.List;

public class SequentialDownloader extends Downloader {

    public int process(List<String> tickers) throws YException {
        int count = 0;
        long startTime = System.currentTimeMillis(); // Get the start Time
        for (String ticker : tickers) {
            String fileName = saveJson2File(ticker);
            if(fileName != null)
                count++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Duration: "+ (endTime-startTime)+"ms"); // Print the difference in seconds
        return count;
    }
}
