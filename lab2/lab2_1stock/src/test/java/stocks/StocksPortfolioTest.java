package stocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @Mock
    IStockMarketService mockStockMarket = Mockito.mock(IStockMarketService.class);

    @InjectMocks
    StocksPortfolio stocksPortfolio;

    @Test
    void getTotalValue() {
        when(mockStockMarket.lookUpPrice("JPGMD")).thenReturn(2.0);
        when(mockStockMarket.lookUpPrice("URMM")).thenReturn(0.1);

        stocksPortfolio.addStock(new Stock("JPGMD", 2));
        stocksPortfolio.addStock(new Stock("URMM", 10));

        assertEquals(5.0, stocksPortfolio.getTotalValue());
    }
}