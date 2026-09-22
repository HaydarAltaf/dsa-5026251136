import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("washes.txt"))) {
            int recordCount = scanner.nextInt();
            
            WashService[] services = new WashService[recordCount];
            int[] unitsArray = new int[recordCount]; 

            for (int i = 0; i < recordCount; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                unitsArray[i] = units;

                if (type.equals("MOTORCYCLE")) {
                    services[i] = new MotorcycleWash(id, days);
                } else if (type.equals("CAR")) {
                    services[i] = new CarWash(id, days);
                }
            }

            for (int i = 0; i < services.length; i++) {
                WashService service = services[i];
                int units = unitsArray[i];
                
                // Menyesuaikan output agar sesuai dengan target (harga dikalikan jumlah unit)
                System.out.println(service.getId() + " | " + service.label() + " | " + service.calculateCharge(units));
            }

        } catch (FileNotFoundException e) {
            System.err.println("File washes.txt tidak ditemukan.");
        }
    }
}