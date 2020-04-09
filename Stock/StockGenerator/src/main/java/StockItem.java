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
        this.totalAmount = numOfShares * pricePerShare;
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
        return totalAmount;
    }

    @Override
    public String toString() {
        return "<table>" +
                "<tr>" +
                    "<td><strong>Type</strong></td>"+
                    "<td><strong>Stock Symbol</strong></td>"+
                    "<td><strong>Price Per Share</strong></td>"+
                    "<td><strong>Number of Shares Bought or Sold</strong></td>"+
                    "<td><strong>Total Amount of the Transaction</strong></td>"+
                "</tr>" +
                "<tr>" +
                    "<td>"+ type +"</td>"+
                    "<td>"+ symbol +"</td>"+
                    "<td>"+ pricePerShare +"</td>"+
                    "<td>"+ numOfShares +"</td>"+
                    "<td>"+ totalAmount +"</td>"+
                "</tr>" +
                "</table>";
    }
}
