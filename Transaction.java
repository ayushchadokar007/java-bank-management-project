import java.util.*;

class Transaction {

    private static int counter = 1000;
    private int transactionId;
    private String type;
    private double amount;
    private Date date;

    public Transaction(String type, double amount) {
        this.transactionId = counter++;
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    public String toString() {
        return "Transaction ID: " + transactionId +
                " | Type: " + type +
                " | Amount: " + amount +
                " | Date: " + date;
    }
}