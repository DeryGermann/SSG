import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private Long accNum;
    private String fName;
    private String lName;
    private String ssn;
    private String email;
    private String phoneNumber;
    private Double balance;
    private JSONArray stock;

    public Account(Long accNum, String fName, String lName, String ssn, String email, String phoneNumber, Double balance,JSONArray stock) {
        this.accNum = accNum;
        this.fName = fName;
        this.lName = lName;
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.stock = stock;
    }

    public Long getAccNum() {
        return accNum;
    }

    public void setAccNum(Long accNum) {
        this.accNum = accNum;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public JSONArray getStock() {
        return stock;
    }

    public void setStock(JSONArray stock) {
        this.stock = stock;
    }

    public void parseStockArray(JSONArray stockInfo) {
//        System.out.println(stockInfo);
        stockInfo.forEach(stockItems -> printStockInfo( (JSONObject) stockItems ));
    }

    private void printStockInfo(JSONObject stockItems) {
        String type = (String) stockItems.get("type");
        System.out.println(type);

        String stockSymbol = (String) stockItems.get("stock_symbol");
        System.out.println(stockSymbol);

        Long countShares = (Long) stockItems.get("count_shares");
        System.out.println(countShares);

        String pricePerShare = (String) stockItems.get("price_per_share");
        Double price = Double.parseDouble(pricePerShare.replaceAll("[$]", ""));
        System.out.println(price);

        StockItem stockItem = new StockItem(type, stockSymbol, price, countShares.doubleValue());

        System.out.println(stockItem.getTotalAmount());

        decreaseBalance(stockItem);
    }

    private void decreaseBalance(StockItem stockItem) {
        System.out.println("Before");
        System.out.println(this.getBalance());

        if (stockItem.getType() == "Buy") {
            this.setBalance(this.balance - stockItem.getTotalAmount());
        } else {
            this.setBalance(this.balance + stockItem.getTotalAmount());
        }

        System.out.println("After");
        System.out.println(this.getBalance());
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        return "Date: " + dtf.format(LocalDateTime.now()) + "\n" +
                "Account Number: " + accNum + "\n" +
                "Account Holder's Full Name: " + fName + " " + lName + "\n" +
                "Social Security Number: " + ssn + "\n" +
                "Email Address: " + email + "\n" +
                "Phone Number: " + phoneNumber + "\n\n";
    }
}
