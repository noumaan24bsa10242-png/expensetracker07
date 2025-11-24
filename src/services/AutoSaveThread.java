package services;

import java.io.FileWriter;

public class AutoSaveThread extends Thread {

    ExpenseService service;

    public AutoSaveThread(ExpenseService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            try {
                double total = service.getTotalSpent();

                FileWriter fw = new FileWriter("autosave.txt");
                fw.write("Auto Backup - Total Spent: ₹" + total);
                fw.close();

                System.out.println("[AutoSave] Backup complete.");

                Thread.sleep(10000); // every 10 seconds

            } catch (Exception e) {
                System.out.println("AutoSave Error: " + e.getMessage());
            }
        }
    }
}
