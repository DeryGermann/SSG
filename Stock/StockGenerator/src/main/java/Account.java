import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Account {
    private Long accNum;
    private String fName;
    private String lName;
    private String ssn;
    private String email;
    private String phoneNumber;
    private Double balance;
    private List<StockItem> stockItems;

    public Account(Long accNum, String fName, String lName, String ssn, String email, String phoneNumber, Double balance) {
        this.accNum = accNum;
        this.fName = fName;
        this.lName = lName;
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
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

    public List<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        return "<p>Date: " + dtf.format(LocalDateTime.now()) + "</p>" +
                "<p>Account Number: " + accNum + "</p>" +
                "<p>Account Holder's Full Name: " + fName + " " + lName + "</p>" +
                "<p>Social Security Number: " + ssn + "</p>" +
                "<p>Email Address: " + email + "</p>" +
                "<p>Phone Number: " + phoneNumber + "</p>";
    }
}
