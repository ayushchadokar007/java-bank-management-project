import java.util.*;

class Account {

    private String accountNumber;
    private String name;
    private String pin;
    private double balance;
    private boolean isBlocked;
    private int wrongAttempts;
    private ArrayList<Transaction> transactions;

    public Account(String accountNumber, String name, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.isBlocked = false;
        this.wrongAttempts = 0;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public double getBalance() {
        return balance;
    }

    public boolean verifyPin(String inputPin) {
        if (isBlocked) {
            System.out.println("Account is BLOCKED.");
            return false;
        }

        if (pin.equals(inputPin)) {
            wrongAttempts = 0;
            return true;
        } else {
            wrongAttempts++;
            if (wrongAttempts >= 3) {
                isBlocked = true;
                System.out.println("Account Blocked due to 3 wrong attempts.");
            }
            return false;
        }
    }

    public void changePin(String newPin) {
        this.pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposit", amount);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient Balance.");
            return false;
        }
        balance -= amount;
        addTransaction("Withdraw", amount);
        return true;
    }

    public void transfer(Account receiver, double amount) {
        if (withdraw(amount)) {
            receiver.deposit(amount);
            addTransaction("Transfer To " + receiver.getAccountNumber(), amount);
        }
    }

    private void addTransaction(String type, double amount) {
        Transaction t = new Transaction(type, amount);
        transactions.add(t);
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No Transactions Found.");
            return;
        }
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void showMiniStatement() {
        int size = transactions.size();
        if (size == 0) {
            System.out.println("No Transactions Found.");
            return;
        }

        int lastNum = Math.max(0, size-5);
        System.out.println("Last 5 Transactions:");
        for (int i = size - 1; i >= lastNum; i--) {
            System.out.println(transactions.get(i));
        }
    }
}
