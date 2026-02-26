import java.util.*;

class BankService {

    private HashMap<String, Account> accounts;
    private String adminId = "admin";
    private String adminPassword = "1234";

    public BankService() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accNo, String name, String pin, double balance) {

        if (accounts.containsKey(accNo)) {
            System.out.println("Account already exists!");
            return;
        }

        Account acc = new Account(accNo, name, pin, balance);
        accounts.put(accNo, acc);

        System.out.println("Account created successfully.");
    }

    public Account login(String accNo, String pin) {

        Account acc = accounts.get(accNo);

        if (acc == null) {
            System.out.println("Account not found.");
            return null;
        }

        if (acc.verifyPin(pin)) {
            System.out.println("Login successful.");
            return acc;
        } else {
            System.out.println("Invalid PIN.");
            return null;
        }
    }

    public boolean adminLogin(String id, String password) {

        if (adminId.equals(id) && adminPassword.equals(password)) {
            System.out.println("Admin Login Successful.");
            return true;
        } else {
            System.out.println("Invalid Admin Credentials.");
            return false;
        }
    }

    public void viewAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        for (Account acc : accounts.values()) {
            System.out.println("Account No: " + acc.getAccountNumber()
                    + " | Name: " + acc.getName()
                    + " | Balance: " + acc.getBalance()
                    + " | Blocked: " + acc.isBlocked());
        }
    }

    public void searchAccount(String accNo) {

        Account acc = accounts.get(accNo);

        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Account Found:");
        System.out.println("Name: " + acc.getName());
        System.out.println("Balance: " + acc.getBalance());
        System.out.println("Blocked: " + acc.isBlocked());
    }

    public void deleteAccount(String accNo) {

        if (accounts.remove(accNo) != null) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void totalBankBalance() {

        double total = 0;

        for (Account acc : accounts.values()) {
            total += acc.getBalance();
        }

        System.out.println("Total Bank Balance: " + total);
    }

    public void totalCustomers() {
        System.out.println("Total Customers: " + accounts.size());
    }

    public Account getAccount(String accNo) {
        return accounts.get(accNo);
    }
}