# Financial Tracker

## Description of the Project

This java application tracks ingoing and outgoing finances. It functions as an electronic version of a checkbook ledger, and it's target audience is your everyday worker. This solves a few problems,
including misplacing a physical checkbook, budget mismanagement, and tracking the flow of money over different financial apps that do not integrate with each other, such as a couple with individual accounts
tracking their expenses as a house.

## User Stories

List the user stories that guided the development of your application. Format these stories as: "As a [type of user], I want [some goal] so that [some reason]."

- As a user, I want to add and track deposits and payments to keep my account up to date.
- As a user, I want a menu with clear directions so that I can easily navigate where I need to go.
- As a user, I want the ability to filter my ledger to only show deposits or payments so that I can better track my money.
- As a user, I want the ability to navigate in and out of different parts of the menu without closing the application so that I can save time and effort.
- As a user, I want the ability to view a list of past transactions to moniotr my spending and income.
- As a user, I want the ability to generate reports on my spending and income at a specific vendor or timeframe so that I can make clear decisions about my budget.
- As a user, I want the ability to save my added transactions so that they are still there when I exit and restart the application.

## Setup

Instructions on how to set up and run the project using IntelliJ IDEA.

### Prerequisites

- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK is installed and configured in IntelliJ.

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the `public static void main(String[] args)` method. This is in the class titled "FinancialTracker".
5. Right-click on the file and select 'Run 'FinancialTracker.main()'' to start the application.

## Technologies Used

- Java: Java 17


## Demo

Here are some screenshots of the program in action:

![Main Menu](src/main/resources/mainMenu.png)
![Add Deposit](src/main/resources/addDeposit.png)
![Invalid Format](src/main/resources/invalidFormat.png)
![Ledger Menu](src/main/resources/ledgerMenu.png)
![Reports](src/main/resources/reports.png)

## Future Work

Additional work that could be done with this project:
-The ability to perform custom searches based on all fields
-The ability to perform searches with partial matches for key terms
-Adding the ability to leave fields blank when creating entries
-Not including seconds in the time field
-Cleaning up code with better logic. I'm sure there's a better way to implement some of these methods.
-Adding a GUI

## Resources

In addition to the material covered in class, the following tutorials and documentation were a great help to me:

- [Java Create and Write to Files](https://www.w3schools.com/java/java_files_create.asp)
- [Java Time LocalDate Class](https://www.tutorialspoint.com/javatime/javatime_localdate.htm)
- [Temporal Adjuster](https://docs.oracle.com/javase/tutorial/datetime/iso/adjusters.html)
- [Compare Two LocalDate Instances in Java](https://howtodoinjava.com/java/date-time/compare-localdates/#:~:text=LocalDate%20equals()%20Method,can%20use%20equals()%20method.)


## Team Members

- **Raymond Maroun** - Contributed skeleton code for initial commit.


## Thanks


- Many thanks to Raymond, my Java bootcamp instructor, for being on standby to help with issues in my code and navigating git/github.
- A big thank you to Cameron for helping me debug my writeToFile method.
- Special thanks to Carmen and Aaron for their motivation, advice, help finding curly braces, and teaching me about the temporal adjuster.