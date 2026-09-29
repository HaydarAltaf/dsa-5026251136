import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class RestaurantOrderProcessing {
    public static void main(String[] args) throws Exception {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});

        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});

        // Membaca file langsung tanpa blok try-catch
        Scanner scanner = new Scanner(new File("orders.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                orders.add(line.split("\\s+"));
            }
        }
        scanner.close();

        for (String[] order : orders) {
            queue.add(order);
        }

        while (queue.peek() != null) {
            String[] currentOrder = queue.poll();
            String food = currentOrder[1];
            String drink = currentOrder[2];

            boolean isFoodAvailable = food.equals("-");
            boolean isDrinkAvailable = drink.equals("-");

            if (!isFoodAvailable) {
                for (String[] f : foodStock) {
                    if (f[0].equals(food) && Integer.parseInt(f[1]) > 0) {
                        isFoodAvailable = true;
                        break;
                    }
                }
            }

            if (!isDrinkAvailable) {
                for (String[] d : drinkStock) {
                    if (d[0].equals(drink) && Integer.parseInt(d[1]) > 0) {
                        isDrinkAvailable = true;
                        break;
                    }
                }
            }

            if (isFoodAvailable && isDrinkAvailable) {
                if (!food.equals("-")) {
                    for (String[] f : foodStock) {
                        if (f[0].equals(food)) {
                            f[1] = String.valueOf(Integer.parseInt(f[1]) - 1);
                            break;
                        }
                    }
                }
                if (!drink.equals("-")) {
                    for (String[] d : drinkStock) {
                        if (d[0].equals(drink)) {
                            d[1] = String.valueOf(Integer.parseInt(d[1]) - 1);
                            break;
                        }
                    }
                }
                successfulOrders.add(currentOrder);
            } else {
                failedOrders.push(currentOrder);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] o : successfulOrders) {
            System.out.println(o[0] + " " + o[1] + " " + o[2] + " " + o[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foodStock) {
            System.out.println(f[0] + ": " + f[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinkStock) {
            System.out.println(d[0] + ": " + d[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] o = failedOrders.pop();
            System.out.println(o[0] + " " + o[1] + " " + o[2] + " " + o[3]);
        }
    }
}