import models.Expense;
import reports.ReportGenerator;
import services.AutoSaveThread;
import services.ExpenseService;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        ExpenseService service = new ExpenseService();
        ReportGenerator report = new ReportGenerator();

        // Multithreading autosave
        AutoSaveThread auto = new AutoSaveThread(service);
        auto.start();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== SMART EXPENSE TRACKER ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Total Spent");
            System.out.println("4. Export Report");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Category: ");
                    String cat = sc.next();

                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();

                    System.out.print("Date (DD-MM-YYYY): ");
                    String date = sc.next();

                    service.addExpense(cat, amt, date);
                }
                case 2 -> {
                    List<Expense> list = service.getAllExpenses();
                    list.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.println("Total Spent: ₹" + service.getTotalSpent());
                }
                case 4 -> {
                    try {
                        List<Expense> list = service.getAllExpenses();
                        report.exportReport(list);
                    } catch (Exception e) {
                        System.out.println("Error exporting report.");
                    }
                }
                case 5 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}
