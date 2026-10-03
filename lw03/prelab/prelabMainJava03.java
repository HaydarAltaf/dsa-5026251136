import java.io.*;
import java.util.*;

public class prelabMainJava03 {
    public static void main(String[] args) throws Exception {
        System.out.println("===== Problem 1 =====");
        problem1();
        
        System.out.println("\n===== Problem 2 =====");
        problem2();
        
        System.out.println("\n===== Problem 3 =====");
        problem3();
    }

    private static void problem1() throws Exception {
        List<String> playlist = new ArrayList<>();
        Scanner sc = new Scanner(new File("playlist.txt"));
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split(" ", 2);
            String command = parts[0];
            
            if (command.equals("ADD")) {
                playlist.add(parts[1]);
            } else if (command.equals("REMOVE")) {
                playlist.remove(parts[1]);
            } else if (command.equals("INSERT")) {
                String[] insertParts = parts[1].split(" ", 2);
                int index = Integer.parseInt(insertParts[0]);
                String song = insertParts[1];
                playlist.add(index, song);
            }
        }
        sc.close();

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    private static void problem2() throws Exception {
        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;
        Scanner sc = new Scanner(new File("participants.txt"));
        
        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();
            if (name.isEmpty()) continue;
            
            if (!participants.add(name)) {
                duplicates++;
            }
        }
        sc.close();

        System.out.println("Unique participants: " + participants.size());
        int i = 1;
        for (String participant : participants) {
            System.out.println(i + ". " + participant);
            i++;
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    private static void problem3() throws Exception {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner sc = new Scanner(new File("inventory.txt"));
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split(" ");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
            } else if (type.equals("SELL")) {
                int currentStock = inventory.getOrDefault(product, 0);
                if (currentStock >= quantity) {
                    inventory.put(product, currentStock - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        sc.close();

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}