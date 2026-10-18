public class Stock {

    // data fields
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    // construct a stock with a specified symbol and name
    public Stock() {
        symbol = "DAL";
        name = "Delta Air Lines, Inc.";
    }

    // setter methods
    public void setSymbol(String newSymbol) {
        symbol = newSymbol;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setPreviousClosingPrice(double newPreviousClosingPrice) {
        previousClosingPrice = newPreviousClosingPrice;
    }

    public void setCurrentPrice(double newCurrentPrice) {
        // if company goes bankrupt
        if (newCurrentPrice < 0) {
            currentPrice = 0;
        } else {
            currentPrice = newCurrentPrice;
        }
    }

    // getter methods
    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPreviousClosingPrice() {
        return previousClosingPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    // calculate percentage change from previousClosingPrice to currentPrice
    public double getChangePercent(double previousClosingPrice, double currentPrice) {
        return ((currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }

}
