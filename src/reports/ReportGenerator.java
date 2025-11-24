package reports;

import models.Expense;

import java.io.FileWriter;
import java.util.List;

public class ReportGenerator {
    public void exportReport(List<Expense> expenses) throws Exception {
        FileWriter fw = new FileWriter("ExpenseReport.txt");

        fw.write("===== MONTHLY EXPENSE REPORT =====\n\n");

        for (Expense e : expenses) {
            fw.write(e.toString() + "\n");
        }

        fw.close();
        System.out.println("Report generated: ExpenseReport.txt");
    }
}
