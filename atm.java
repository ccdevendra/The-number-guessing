import java.util.InputMismatchException;
import java.util.Scanner;

// BankAccount Class
class BankAccount {
    private double balance;
    private String lastTransaction;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.lastTransaction = "No transactions yet.";
    }

    public double getBalance() {
        return balance;
    }

    public String getLastTransaction() {
        return lastTransaction;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            lastTransaction = "Deposited: ₹" + String.format("%.2f", amount);
            System.out.println("✅ Successfully deposited ₹" + String.format("%.2f", amount));
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            lastTransaction = "Withdrawn: ₹" + String.format("%.2f", amount);
            System.out.println("✅ Successfully withdrawn ₹" + String.format("%.2f", amount));
        } else if (amount > balance) {
            System.out.println("⚠️ Insufficient balance.");
        } else {
            System.out.println("❌ Invalid withdrawal amount.");
        }
    }
}

// ATM Class
class ATM {
    private BankAccount account;
    private Scanner input = new Scanner(System.in);

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        while (true) {
            System.out.println("\n====== ATM MENU ======");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Last Transaction");
            System.out.println("5. Exit");
            System.out.print("👉 Choose an option: ");

            int choice = getValidChoice();

            switch (choice) {
                case 1:
                    System.out.println("💰 Current Balance: ₹" + String.format("%.2f", account.getBalance()));
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = getValidAmount();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = getValidAmount();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("📜 Last Transaction: " + account.getLastTransaction());
                    break;
                case 5:
                    System.out.println("🙏 Thank you for using the ATM!");
                    input.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    // Handle invalid menu choice input safely
    private int getValidChoice() {
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine(); // clear invalid input
            return -1; // invalid choice
        }
    }

    // Handle invalid money input safely
    private double getValidAmount() {
        try {
            return input.nextDouble();
        } catch (InputMismatchException e) {
            input.nextLine(); // clear invalid input
            System.out.println("❌ Invalid amount entered.");
            return -1; // invalid amount
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initial balance
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
