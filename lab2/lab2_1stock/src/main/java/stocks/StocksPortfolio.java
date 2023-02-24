package stocks;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private final List<Stock> stocks = new ArrayList<Stock>();
    private final IStockMarketService stockmarket;

    public StocksPortfolio(IStockMarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double result = 0.0;
        for (Stock stock : stocks) {
            result += stock.getQuantity() * stockmarket.lookUpPrice(stock.getLabel());
        }
        return result;
    }
}
