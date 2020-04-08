public class StockItem {
    private String type;
    private String symbol;
    private Double pricePerShare;
    private Double numOfShares;
    private Double totalAmount;

    public StockItem(String type, String symbol, Double pricePerShare, Double numOfShares) {
        this.type = type;
        this.symbol = symbol;
        this.pricePerShare = pricePerShare;
        this.numOfShares = numOfShares;
        this.totalAmount = totalAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(Double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public Double getNumOfShares() {
        return numOfShares;
    }

    public void setNumOfShares(Double numOfShares) {
        this.numOfShares = numOfShares;
    }

    public Double getTotalAmount() {
        return numOfShares * pricePerShare;
    }
}
