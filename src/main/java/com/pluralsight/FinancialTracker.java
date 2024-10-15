package com.pluralsight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTracker {

    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static final String FILE_NAME = "transactions.csv";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static void main(String[] args) {
        loadTransactions(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

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

    public static void loadTransactions(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File not found; creating new file.");
            } catch (Exception e) {
                System.out.println("Could not create file.");
            }
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    processLine(line);
                }
            } catch (Exception e) {
                System.out.println("Error reading file.");
            }
        }
        // This method should load transactions from a file with the given file name.
        // If the file does not exist, it should be created.
        // The transactions should be stored in the `transactions` ArrayList.
        // Each line of the file represents a single transaction in the following format:
        // <date>|<time>|<description>|<vendor>|<amount>
        // For example: 2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
        // After reading all the transactions, the file should be closed.
        // If any errors occur, an appropriate error message should be displayed.
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

        // Transaction is successfully added
        System.out.println("Transaction added successfully!");
        // You can continue with additional logic here
    }



        private static void addPayment (Scanner scanner){
            // This method should prompt the user to enter the date, time, description, vendor, and amount of a payment.
            // The user should enter the date and time in the following format: yyyy-MM-dd HH:mm:ss
            // The amount received should be a positive number then transformed to a negative number.
            // After validating the input, a new `Transaction` object should be created with the entered values.
            // The new payment should be added to the `transactions` ArrayList.
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
            // This method should display a table of all transactions in the `transactions` ArrayList.
            // The table should have columns for date, time, description, vendor, and amount.
        }

        private static void displayDeposits () {
            // This method should display a table of all deposits in the `transactions` ArrayList.
            // The table should have columns for date, time, description, vendor, and amount.
        }

        private static void displayPayments () {
            // This method should display a table of all payments in the `transactions` ArrayList.
            // The table should have columns for date, time, description, vendor, and amount.
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
                        // Generate a report for all transactions within the current month,
                        // including the date, time, description, vendor, and amount for each transaction.
                    case "2":
                        // Generate a report for all transactions within the previous month,
                        // including the date, time, description, vendor, and amount for each transaction.
                    case "3":
                        // Generate a report for all transactions within the current year,
                        // including the date, time, description, vendor, and amount for each transaction.

                    case "4":
                        // Generate a report for all transactions within the previous year,
                        // including the date, time, description, vendor, and amount for each transaction.
                    case "5":
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
        }

        private static void filterTransactionsByVendor (String vendor){
            // This method filters the transactions by vendor and prints a report to the console.
            // It takes one parameter: vendor, which represents the name of the vendor to filter by.
            // The method loops through the transactions list and checks each transaction's vendor name against the specified vendor name.
            // Transactions with a matching vendor name are printed to the console.
            // If no transactions match the specified vendor name, the method prints a message indicating that there are no results.
        }
    }
