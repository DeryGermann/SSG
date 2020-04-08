import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;

public class StockGenerator {

    public static void main(String[] args) {
        // Read JSON data
        readJsonData();

        // Turn JSON into HTML


        // HTML to PDF

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
            stockList.forEach( stock -> testPrint( (JSONObject) stock ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void testPrint(JSONObject thing) {
//        System.out.println(thing.get("account_number"));
//        System.out.println("\n");

        Long accNumber = (Long) thing.get("account_number");
        String ssn = (String) thing.get("ssn");
        String fName = (String) thing.get("first_name");
        String lName = (String) thing.get("last_name");
        String email = (String) thing.get("email");
        String phone = (String) thing.get("phone");

        String bBalance = (String) thing.get("beginning_balance");
        String balance = bBalance.replaceAll("[$]", "");

        JSONArray sTrade = (JSONArray) thing.get("stock_trades");

        Account account = new Account(accNumber, fName, lName, ssn, email, phone, Double.parseDouble(balance), sTrade);

//        System.out.println(account.toString());

        account.parseStockArray(sTrade);
    }
}
