package com.pluralsight;
//TODO: have a good presentation :)
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Scanner;


public class FinancialTracker {
    private static final ArrayList<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "transactions.csv";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static void main(String[] args) {
        loadTransactions(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        //Main menu
        while (running) {
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    addPayment(scanner);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        scanner.close();
    }

    public static void loadTransactions(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File not found; creating new file.");
            } catch (Exception e) {
                System.out.println("Could not create file: " + e.getMessage());
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split("\\|");
                    if (values.length == 5) {
                        Transaction transaction = new Transaction(
                                LocalDate.parse(values[0], DATE_FORMATTER),
                                LocalTime.parse(values[1], TIME_FORMATTER),
                                values[2],
                                values[3],
                                Double.parseDouble(values[4])
                        );
                        transactions.add(transaction);
                    } else {
                        System.out.println("Invalid line format: " + line);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }

    public static void processLine(String line) {
        String[] fields = line.split("\\|");

        try {
            LocalDate date = LocalDate.parse(fields[0], DATE_FORMATTER);
            LocalTime time = LocalTime.parse(fields[1], TIME_FORMATTER);
            String description = fields[2];
            String vendor = fields[3];
            double amount = Double.parseDouble(fields[4]);

            Transaction transaction = new Transaction(date, time, description, vendor, amount);

            transactions.add(transaction);
        } catch (Exception e) {
            System.out.println("Processing Error.");
        }
    }

    private static void addDeposit(Scanner scanner) {
        // Get the date of the transaction
        LocalDate date = null;
        while (date == null) {
            System.out.println("Enter the date of the transaction you would like to add. (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();

            try {
                date = LocalDate.parse(dateInput, DATE_FORMATTER);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        // Get the time of the transaction
        LocalTime time = null;
        while (time == null) {
            System.out.println("Enter the time of the transaction you would like to add. (HH:mm:ss)");
            String timeInput = scanner.nextLine();

            try {
                time = LocalTime.parse(timeInput, TIME_FORMATTER);
            } catch (Exception e) {
                System.out.println("Invalid time format. Please try again.");
            }
        }

        // Get the description of the transaction
        System.out.println("Enter the description of the transaction you would like to add. (Ex. 'Student Loan Payment.')");
        String description = scanner.nextLine();

        // Get the vendor name for the transaction
        System.out.println("Enter the name of the vendor for the transaction you would like to add.");
        String vendor = scanner.nextLine();

        // Get the amount of the transaction and validate
        double amount = 0;
        while (amount == 0) {
            System.out.println("Enter the amount of the transaction you would like to add in dollars and cents. (Ex. '19.95')");
            String amountInput = scanner.nextLine();

            try {
                amount = Double.parseDouble(amountInput);
                if (amount <= 0) {
                    throw new IllegalArgumentException("Amount must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Transaction added successfully!");
        // Create new Transaction object
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        writeToFile(transaction);
    }



    private static void addPayment (Scanner scanner){

        LocalDate date = null;
        while (date == null) {
            System.out.println("Enter the date of the transaction you would like to add. (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();

            try {
                date = LocalDate.parse(dateInput, DATE_FORMATTER);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        // Get the time of the transaction
        LocalTime time = null;
        while (time == null) {
            System.out.println("Enter the time of the transaction you would like to add. (HH:mm:ss)");
            String timeInput = scanner.nextLine();

            try {
                time = LocalTime.parse(timeInput, TIME_FORMATTER);
            } catch (Exception e) {
                System.out.println("Invalid time format. Please try again.");
            }
        }

        // Get the description of the transaction
        System.out.println("Enter the description of the transaction you would like to add. (Ex. 'Student Loan Payment.')");
        String description = scanner.nextLine();

        // Get the vendor name for the transaction
        System.out.println("Enter the name of the vendor for the transaction you would like to add.");
        String vendor = scanner.nextLine();

        // Get the amount of the transaction and validate
        double amount = 0;
        while (amount == 0) {
            System.out.println("Enter the amount of the transaction you would like to add in dollars and cents. (Ex. '19.95')");
            String amountInput = scanner.nextLine();

            try {
                amount = Double.parseDouble(amountInput);
                if (amount <= 0) {
                    throw new IllegalArgumentException("Amount must be positive.");
                }
                amount = amount * -1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println("Transaction added successfully!");
        // Create new Transaction object
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        writeToFile(transaction);
    }
    // Add deposits and payments input by user to file
    public static void writeToFile(Transaction transaction) {
        try (BufferedWriter myWriter = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            myWriter.write(
                    transaction.getDate().toString() + "|" +
                            transaction.getTime().toString() + "|" +
                            transaction.getDescription() + "|" +
                            transaction.getVendor() + "|" +
                            transaction.getAmount() + "\n"
            );

        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void ledgerMenu (Scanner scanner){
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    displayLedger();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportsMenu(scanner);
                    break;
                case "H":
                    running = false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void displayLedger () {
        //formatting ledger table
        System.out.printf("%-15s %-10s %-30s %-20s %-10s%n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("--------------------------------------------------------------------------------------");

        for (Transaction transaction: transactions) {
            System.out.printf("%-15s %-10s %-30s %-20s %10.2f%n",
                    transaction.getDate().toString(),
                    transaction.getTime().toString(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()
            );
        }
        // Display a table of all transactions in the `transactions` ArrayList.
        // Columns for date, time, description, vendor, and amount.
    }

    private static void displayDeposits () {
        System.out.printf("%-15s %-10s %-30s %-20s %-10s%n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("--------------------------------------------------------------------------------------");

        for (Transaction transaction: transactions) {
            if (transaction.getAmount() > 0) {
                System.out.printf("%-15s %-10s %-30s %-20s %10.2f%n",
                        transaction.getDate().toString(),
                        transaction.getTime().toString(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount()
                );
            }

        }
        // Display a table of all deposits in the `transactions` ArrayList.
        // Columns for date, time, description, vendor, and amount.
    }

    private static void displayPayments () {
        System.out.printf("%-15s %-10s %-30s %-20s %-10s%n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("--------------------------------------------------------------------------------------");

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.printf("%-15s %-10s %-30s %-20s %10.2f%n",
                        transaction.getDate().toString(),
                        transaction.getTime().toString(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount()
                );
            }
            // Display a table of all payments in the `transactions` ArrayList.
            // Columns for date, time, description, vendor, and amount.
        }
    }

    private static void reportsMenu (Scanner scanner){
        boolean running = true;
        while (running) {
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate today = LocalDate.now();
                    filterTransactionsByDate(startOfMonth, today);
                    break;
                    // Generate a report for all transactions within the current month

                case "2":
                    LocalDate startOfPreviousMonth = LocalDate.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate endOfPreviousMonth = LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
                    filterTransactionsByDate(startOfPreviousMonth, endOfPreviousMonth);
                    break;
                    // Generate a report for all transactions within the previous month

                case "3":
                    LocalDate startOfYear = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
                    LocalDate todayCase3 = LocalDate.now();
                    filterTransactionsByDate(startOfYear, todayCase3);
                    break;
                    // Generate a report for all transactions within the current year

                case "4":
                    LocalDate startOfPreviousYear = LocalDate.now().minusYears(1).with(TemporalAdjusters.firstDayOfYear());
                    LocalDate endOfPreviousYear = LocalDate.now().minusYears(1).with(TemporalAdjusters.lastDayOfYear());
                    filterTransactionsByDate(startOfPreviousYear, endOfPreviousYear);
                    break;
                    // Generate a report for all transactions within the previous year

                case "5":
                    System.out.println("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    filterTransactionsByVendor(vendor);
                    break;
                    // Prompt the user to enter a vendor name, then generate a report for all transactions
                    // with that vendor, including the date, time, description, vendor, and amount for each transaction.

                case "0":
                    running = false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }


    private static void filterTransactionsByDate (LocalDate startDate, LocalDate endDate){
        // This method filters the transactions by date and prints a report to the console.
        // It takes two parameters: startDate and endDate, which represent the range of dates to filter by.
        // The method loops through the transactions list and checks each transaction's date against the date range.
        // Transactions that fall within the date range are printed to the console.
        // If no transactions fall within the date range, the method prints a message indicating that there are no results.

        System.out.println("Transactions between " + startDate + "-" + endDate + ": \n");
        boolean found = false;

        for (Transaction transaction : transactions) {
            if ((transaction.getDate().isAfter(startDate) || transaction.getDate().equals(startDate)) && (transaction.getDate().isBefore(endDate)) || transaction.getDate().equals(endDate)) {
                System.out.println(transaction);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching entries were found.");
        }
    }

    private static void filterTransactionsByVendor (String vendor){
        // This method filters the transactions by vendor and prints a report to the console.
        // It takes one parameter: vendor, which represents the name of the vendor to filter by.
        // The method loops through the transactions list and checks each transaction's vendor name against the specified vendor name.
        // Transactions with a matching vendor name are printed to the console.
        // If no transactions match the specified vendor name, the method prints a message indicating that there are no results.

        System.out.println("Transactions involving '" + vendor + "' \n");
        boolean found = false;

        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transaction);
                found = true;

            }
        }
        if (!found) {
            System.out.println("No matching entries were found.");
        }
    }
}
