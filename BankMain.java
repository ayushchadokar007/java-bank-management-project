import java.util.*;

public class BankMain {
    static Scanner sc = new Scanner(System.in);
    static BankService bank = new BankService();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Customer Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    String accNo = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Set PIN: ");
                    String pin = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    bank.createAccount(accNo, name, pin, balance);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    String loginAcc = sc.nextLine();

                    System.out.print("Enter PIN: ");
                    String loginPin = sc.nextLine();

                    Account user = bank.login(loginAcc, loginPin);

                    if (user == null) {
                        System.out.println("Invalid Card or PIN!");
                    } else {
                        accLogin(user);
                    }
                    break;

                case 3:
                    System.out.print("Enter Admin ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Admin Password: ");
                    String pass = sc.nextLine();

                    if (bank.adminLogin(id, pass)) {
                        admLogin();
                    }
                    break;

                case 4:
                    System.out.println("Thank You for Using Bank System.");
                    System.exit(0);

                default:
                    System.out.println("Invalid Option.");
            }
        }

    }

    public static void accLogin(Account user) {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Transaction History");
            System.out.println("6. Mini Statement");
            System.out.println("7. Change PIN");
            System.out.println("8. Logout");
            System.out.print("Choose option: ");
            int userChoice = sc.nextInt();
            sc.nextLine();

            switch (userChoice) {
                case 1:
                    System.out.println("Balance: " + user.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    double dep = sc.nextDouble();
                    user.deposit(dep);
                    break;
                case 3:
                    System.out.print("Enter amount: ");
                    double with = sc.nextDouble();
                    user.withdraw(with);
                    break;
                case 4:
                    System.out.print("Enter Receiver Account Number: ");
                    String receiverAcc = sc.nextLine();
                    Account receiver = bank.getAccount(receiverAcc);
                    if (receiver == null) {
                        System.out.println("Receiver not found.");
                    } else {
                        System.out.print("Enter amount: ");
                        double amt = sc.nextDouble();
                        user.transfer(receiver, amt);
                    }
                    break;
                case 5:
                    user.showTransactions();
                    break;
                case 6:
                    user.showMiniStatement();
                    break;
                case 7:
                    System.out.print("Enter New PIN: ");
                    String newPin = sc.nextLine();
                    user.changePin(newPin);
                    break;
                case 8:
                    System.out.println("Logged Out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            if (userChoice == 8)
                break;
        }
    }

    public static void admLogin() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Accounts");
            System.out.println("2. Search Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Total Bank Balance");
            System.out.println("5. Total Customers");
            System.out.println("6. Logout");
            System.out.print("Choose option: ");
            int adminChoice = sc.nextInt();
            sc.nextLine();
            switch (adminChoice) {
                case 1:
                    bank.viewAllAccounts();
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    String searchAcc = sc.nextLine();
                    bank.searchAccount(searchAcc);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    String deleteAcc = sc.nextLine();
                    bank.deleteAccount(deleteAcc);
                    break;
                case 4:
                    bank.totalBankBalance();
                    break;
                case 5:
                    bank.totalCustomers();
                    break;
                case 6:
                    System.out.println("Admin Logged Out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            if (adminChoice == 6)
                break;
        }
    }
}
