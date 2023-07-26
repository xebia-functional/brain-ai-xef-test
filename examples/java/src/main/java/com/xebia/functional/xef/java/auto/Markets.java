package com.xebia.functional.xef.java.auto;

import com.xebia.functional.xef.agents.SearchDocs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Markets {
    public String news;
    public List<String> raisingStockSymbols;
    public List<String> decreasingStockSymbols;

    @Override
    public String toString() {
        return "Markets{" +
                "news='" + news + '\'' +
                ", raisingStockSymbols=" + raisingStockSymbols +
                ", decreasingStockSymbols=" + decreasingStockSymbols +
                '}';
    }

    private static CompletableFuture<Void> stockMarketSummary(AIScope scope) {
        String news = "|" +
                "|Write a short summary of the stock market results given the provided context.";

        return scope.prompt(news, Markets.class)
                .thenAccept(System.out::println);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (AIScope scope = new AIScope()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/M/yyyy");
            LocalDateTime now = LocalDateTime.now();
            var currentDate = dtf.format(now);

            scope.contextScopeAsync(
                    SearchDocs.search(currentDate + "Stock market results, raising stocks, decreasing stocks").get(),
                    Markets::stockMarketSummary
            ).get();
        }
    }
}
