package downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader {

    @Override
    public int process(List<String> tickers) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        var tickerDownloads = new ArrayList<Future<Integer>>();
        int count = 0;
        long startTime = System.currentTimeMillis(); // Get the start Time
        for(String ticker: tickers){
            tickerDownloads.add(executorService.submit(() -> saveJson2File(ticker) != null ? 1 : 0));
        }
        for(Future<Integer> tickerDownload : tickerDownloads){
            try {
              count = count + tickerDownload.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Duration: "+ (endTime-startTime) +"ms"); // Print the difference in seconds
        return count;
    }
}
