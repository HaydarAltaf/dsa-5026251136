import java.io.File;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Set<String> registeredStudents = new HashSet<>();
        Set<String> checkedInStudents = new HashSet<>();
        List<String> checkInResults = new ArrayList<>();
        int rejectedAttempts = 0;

        File regFile = new File("registrations.txt");
        Scanner regScanner = new Scanner(regFile);
        while (regScanner.hasNextLine()) {
            String id = regScanner.nextLine();
            registeredStudents.add(id);
        }
        regScanner.close();

        File checkinFile = new File("checkins.txt");
        Scanner checkinScanner = new Scanner(checkinFile);
        while (checkinScanner.hasNextLine()) {
            String id = checkinScanner.nextLine();

            if (!registeredStudents.contains(id)) {
                checkInResults.add(id + ": Rejected (not registered)");
                rejectedAttempts++;
             } else if (checkedInStudents.contains(id)) {
                checkInResults.add(id + ": Rejected (already checked in)");
                rejectedAttempts++;
            } else {
                checkedInStudents.add(id);
                checkInResults.add(id + ": Checked in");
            }
        }
        checkinScanner.close();

        System.out.println("===== Event Check-In Results =====");
        for (int i = 0; i < checkInResults.size(); i++) {
            System.out.println(checkInResults.get(i));
        }

        System.out.println("\n===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudents.size());
        System.out.println("Successful check-ins: " + checkedInStudents.size());
        System.out.println("Absent students: " + (registeredStudents.size() - checkedInStudents.size()));
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}