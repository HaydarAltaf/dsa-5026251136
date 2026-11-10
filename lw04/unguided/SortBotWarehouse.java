import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortBotWarehouse {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("parcels.txt"))) {
            int parcelCount = scanner.nextInt();
            int[] parcels = new int[parcelCount];

            for (int i = 0; i < parcelCount; i++) {
                parcels[i] = scanner.nextInt();
            }

            int targetCount = scanner.nextInt();
            int[] targets = new int[targetCount];

            for (int i = 0; i < targetCount; i++) {
                targets[i] = scanner.nextInt();
            }

            int[] after2Passes = new int[parcelCount];

            for (int i = 0; i < parcelCount - 1; i++) {
                for (int j = 0; j < parcelCount - 1 - i; j++) {
                    if (parcels[j] < parcels[j + 1]) {
                        int temp = parcels[j];
                        parcels[j] = parcels[j + 1];
                        parcels[j + 1] = temp;
                    }
                }
                
                if (i == 1) { 
                    for (int k = 0; k < parcelCount; k++) {
                        after2Passes[k] = parcels[k];
                    }
                }
            }

            System.out.println("==== After 2 Passes ====");
            printArray(after2Passes);

            System.out.println("==== Fully Sorted ====");
            printArray(parcels);

            System.out.println("==== Search Results ====");
            
            for (int i = 0; i < targetCount; i++) {
                int target = targets[i];
                int low = 0;
                int high = parcelCount - 1;
                int steps = 0;
                boolean found = false;
                int foundIndex = -1;

                while (low <= high) {
                    steps++;
                    int mid = (low + high) / 2;

                    if (parcels[mid] == target) {
                        found = true;
                        foundIndex = mid;
                        break;
                    } else if (target > parcels[mid]) {
                        high = mid - 1; 
                    } else {
                        low = mid + 1;  
                    }
                }

                if (found) {
                    System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
                } else {
                    System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File parcels.txt tidak ditemukan.");
        }
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}