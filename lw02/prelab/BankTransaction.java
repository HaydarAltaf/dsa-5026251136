import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransaction {
    public static void main(String[] args) {
        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();
        Queue<String[]> transactionQueue = new LinkedList<>();
        Stack<String[]> failedTransactions = new Stack<>();

        // 1. Membaca file transactions.txt menggunakan Scanner dan menyimpannya ke LinkedList
        try {
            Scanner scanner = new Scanner(new File("transactions.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] data = line.split(" ");
                    transactionList.add(data);

                    // 2. Menyimpan data customer unik dengan saldo awal 0 sesuai urutan kemunculan pertama
                    String customerName = data[0];
                    boolean exists = false;
                    for (String[] cust : customerList) {
                        if (cust[0].equals(customerName)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        customerList.add(new String[]{customerName, "0"});
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan. Pastikan lokasinya sudah benar.");
            return;
        }

        // 3. Memindahkan transaksi dari LinkedList ke Queue
        for (String[] tx : transactionList) {
            transactionQueue.add(tx);
        }

        // 4. Memproses transaksi secara FIFO menggunakan Queue dan Stack untuk transaksi gagal
        while (!transactionQueue.isEmpty()) {
            String[] currentTx = transactionQueue.poll();
            String name = currentTx[0];
            String type = currentTx[1];
            int amount = Integer.parseInt(currentTx[2]);

            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    int balance = Integer.parseInt(cust[1]);
                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        cust[1] = String.valueOf(balance);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > balance) {
                            failedTransactions.push(currentTx); // Masuk ke stack jika saldo kurang
                        } else {
                            balance -= amount;
                            cust[1] = String.valueOf(balance);
                        }
                    }
                    break;
                }
            }
        }

        // 5. Menampilkan hasil akhir saldo dan transaksi gagal sesuai format modul
        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}