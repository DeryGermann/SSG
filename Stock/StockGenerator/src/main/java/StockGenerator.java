import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class StockGenerator {
    private static Account account;
    private static List<StockItem> stockItemList;

    public static void main(String[] args) throws IOException {
        // Read JSON data
        readJsonData();

        // HTML to PDF
        iterateThroughHTMLFiles();
    }


    public static void readJsonData() {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("stock_transations.by.account.holder.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray stockList = (JSONArray) obj;
            //System.out.println(stockList);

            //Iterate over employee array
            stockList.forEach( stock -> {
                try {
                    setUpJSON( (JSONObject) stock );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void setUpJSON(JSONObject stock) throws IOException {
        Long accNumber = (Long) stock.get("account_number");
        String ssn = (String) stock.get("ssn");
        String fName = (String) stock.get("first_name");
        String lName = (String) stock.get("last_name");
        String email = (String) stock.get("email");
        String phone = (String) stock.get("phone");

        String bBalance = (String) stock.get("beginning_balance");
        String balance = bBalance.replaceAll("[$]", "");

        JSONArray sTrade = (JSONArray) stock.get("stock_trades");

        account = new Account(accNumber, fName, lName, ssn, email, phone, Double.parseDouble(balance));

        stockItemList = new ArrayList<>();
        sTrade.forEach(stockItems -> stockItemList.add(printStockInfo( (JSONObject) stockItems )));

        account.setStockItems(stockItemList);

        turnJSONtoHTML(account);
    }

    // Turn JSON into HTML
    public static void turnJSONtoHTML(Account account) throws IOException {
        OutputStream os = new FileOutputStream(String.format("./outHTML/%d.html", account.getAccNum()));
        OutputStreamWriter osw = new OutputStreamWriter(os);

        osw.write("<!DOCTYPE html>");
        osw.write("<html><body>");

        osw.write(String.format("%s", account.toString()));
        account.getStockItems().forEach(stockItem -> {
            try {
                osw.write(String.format("%s", stockItem.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        osw.write("</body><html>");
        osw.close();
    }

    private static StockItem printStockInfo(JSONObject stockItems) {
        String type = (String) stockItems.get("type");
//        System.out.println(type);

        String stockSymbol = (String) stockItems.get("stock_symbol");
//        System.out.println(stockSymbol);

        Long countShares = (Long) stockItems.get("count_shares");
//        System.out.println(countShares);

        String pricePerShare = (String) stockItems.get("price_per_share");
        Double price = Double.parseDouble(pricePerShare.replaceAll("[$]", ""));
//        System.out.println(price);

        StockItem stockItem = new StockItem(type, stockSymbol, price, countShares.doubleValue());

//        System.out.println(stockItem.getTotalAmount());

        return decreaseBalance(stockItem);
    }

    private static StockItem decreaseBalance(StockItem stockItem) {
        if (stockItem.getType() == "Buy") {
            account.setBalance(account.getBalance() - stockItem.getTotalAmount());
        } else {
            account.setBalance(account.getBalance() + stockItem.getTotalAmount());
        }

        return stockItem;
    }

    private static void iterateThroughHTMLFiles() {
        File dir = new File("./outHTML/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File f : directoryListing) {
                convertFromHTMLtoPDF(f);
            }
        } else {
            System.out.println("Directory does not exist.");
        }
    }

    private static void convertFromHTMLtoPDF(File f) {

    }

//    public static void testPrint(JSONObject thing) {
//        System.out.println(thing.get("account_number"));
//        System.out.println("\n");
//
//        Long accNumber = (Long) thing.get("account_number");
//        String ssn = (String) thing.get("ssn");
//        String fName = (String) thing.get("first_name");
//        String lName = (String) thing.get("last_name");
//        String email = (String) thing.get("email");
//        String phone = (String) thing.get("phone");
//
//        String bBalance = (String) thing.get("beginning_balance");
//        String balance = bBalance.replaceAll("[$]", "");
//
//        JSONArray sTrade = (JSONArray) thing.get("stock_trades");
//
//        Account account = new Account(accNumber, fName, lName, ssn, email, phone, Double.parseDouble(balance), sTrade);
//
//        System.out.println(account.toString());
//
//        account.parseStockArray(sTrade);
//    }
}
